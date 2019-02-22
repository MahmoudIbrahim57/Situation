package com.example.situations.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.situations.R
import com.example.situations.utils.*
import javax.xml.transform.Result

class NewSituationActivity : AppCompatActivity() {
    private lateinit var situation : EditText
    private lateinit var meaning : EditText
    private lateinit var  saveButton:Button
    private lateinit var  deleteButton: Button
    private lateinit var  mSitation: String
    private lateinit var  mMeaning: String
    private  var isNewSituation : Boolean =true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_situation)
        situation=findViewById(R.id.et_situation)
        meaning=findViewById(R.id.et_meaning)
        saveButton=findViewById(R.id.save)
        deleteButton=findViewById(R.id.delete)
        var extras = intent.extras
        extras?.let {
            mSitation = it.get(EXTRA_KEY_WORD) as String
            mMeaning = it.get(EXTRA_KEY_MEANINIG) as String
            situation.setText(mSitation)
            meaning.setText(mMeaning)
            situation.isEnabled = false
        }
        isNewSituation = false
        if(isNewSituation){
            deleteButton.visibility= View.GONE
        }
        else{
            deleteButton.visibility= View.VISIBLE
        }

        saveButton.setOnClickListener{
        val intent=Intent()
            if (TextUtils.isEmpty(situation.text)||TextUtils.isEmpty(meaning.text)){
            setResult(RESULT_ERROR,intent)
            }
            else{

                intent.putExtra(EXTRA_KEY_WORD,situation.text.toString())
                intent.putExtra(EXTRA_KEY_MEANINIG,meaning.text.toString())
                setResult(RESULT_SAVE,intent)

            }
            finish()
        }
        deleteButton.setOnClickListener{
            intent.putExtra(EXTRA_KEY_WORD,situation.text.toString())
            setResult(RESULT_DELETE,intent)
            finish()
        }
    }
}
