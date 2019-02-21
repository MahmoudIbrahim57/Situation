package com.example.situations.view

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
import android.widget.Toast
import com.example.situations.R

import kotlinx.android.synthetic.main.activity_main.*
import com.example.situations.model.Situation
import com.example.situations.utils.*
import com.example.situations.viewModel.SituationViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: SituationViewModel
    private  lateinit var  mRecylerView : RecyclerView
    private  lateinit var  mAdapter: SituationListAdapter
    private var mSituation : List<Situation>
            = mutableListOf<Situation>(Situation("situation1"
        ,"hello sir"),Situation("situation2","hello sir"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mRecylerView=findViewById(R.id.recylerView)
        mAdapter= SituationListAdapter(this)
        mAdapter.setSituation(mSituation)
        mRecylerView.adapter=mAdapter
        mRecylerView.layoutManager=LinearLayoutManager(this)
        mViewModel= ViewModelProviders.of(this).get(SituationViewModel::class.java)
        mViewModel.getAllSituation().observe(this, Observer { situations ->
            situations?.let {
                mAdapter.setSituation(it)

        } })
        fab.setOnClickListener {
                view ->
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

                     if(resultCode==NEW_SITUATION_REQUES_CODE&&resultCode== RESULT_ERROR) {
                    Toast.makeText(this,"empty situation saved",Toast.LENGTH_SHORT).show()

                }



    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
