package com.example.situations.view

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.situations.R

import kotlinx.android.synthetic.main.activity_main.*
import com.example.situations.model.Situation
import com.example.situations.utils.*
import com.example.situations.viewModel.SituationViewModel

class MainActivity : AppCompatActivity() , SituationListAdapter.ItemClickListener {

    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, NewSituationActivity::class.java)
        intent.putExtra(EXTRA_KEY_WORD, mAdapter.getSiuation()[position].name)
        intent.putExtra(EXTRA_KEY_MEANINIG, mAdapter.getSiuation()[position].meaning)
        startActivityForResult(intent, NEW_SITUATION_REQUES_CODE)
    }

    private lateinit var mViewModel: SituationViewModel
    private  lateinit var  mRecylerView : RecyclerView
    private  lateinit var  mAdapter: SituationListAdapter
    private var mSituation : List<Situation> = mutableListOf<Situation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mRecylerView=findViewById(R.id.recylerView)
        mAdapter= SituationListAdapter(this,this)
        mAdapter.setSituation(mSituation)
        mRecylerView.adapter=mAdapter
        mRecylerView.layoutManager=LinearLayoutManager(this)
        mViewModel= ViewModelProviders.of(this).get(SituationViewModel::class.java)
        mViewModel.getAllSituation().observe(this, Observer {
                situations ->
            situations?.let {
                mAdapter.setSituation(it)
        } })
        fab.setOnClickListener {
            val  intent =Intent(this,NewSituationActivity::class.java)
            startActivityForResult(intent,NEW_SITUATION_REQUES_CODE)

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if(requestCode==NEW_SITUATION_REQUES_CODE&&resultCode== RESULT_SAVE){

                data?.let {
                    val situation=
                        Situation(it.getStringExtra(EXTRA_KEY_WORD)
                        ,it.getStringExtra(EXTRA_KEY_MEANINIG))

                    mViewModel.insertSituation(situation)
                }}
            else
                if(requestCode==NEW_SITUATION_REQUES_CODE&&resultCode== RESULT_DELETE){
                data?.let {
                    val situation = mViewModel.getSituationByName(it.getStringExtra(EXTRA_KEY_WORD))
                    situation?.let {
                        mViewModel.deleteSituation(situation)
                    }
                    Toast.makeText(this,"Situation deleted",Toast.LENGTH_SHORT).show()
                }}
            else
                if(requestCode==NEW_SITUATION_REQUES_CODE&&resultCode== RESULT_ERROR) {
                    Toast.makeText(this,"empty situation saved",Toast.LENGTH_SHORT).show()
                }



    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear_list ->
            {
                clearListAction()
                return true
        }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearListAction() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage("Are you sure you wish to clear all Situations.")
        builder.setPositiveButton("Yes")
        {
            _ , _ ->
            mViewModel.deleteAllSituation()
            Toast.makeText(this, "all situations deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No")
        {
            dialog , _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()


    }
}
