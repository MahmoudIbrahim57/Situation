package com.example.situations.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.situations.R
import com.example.situations.utils.EXTRA_KEY_MEANINIG
import com.example.situations.utils.EXTRA_KEY_WORD
import com.example.situations.utils.RESULT_ERROR
import com.example.situations.utils.RESULT_SAVE
import javax.xml.transform.Result

class NewSituationActivity : AppCompatActivity() {
    private lateinit var situation : EditText
    private lateinit var meaning : EditText
    private lateinit var  saveButton:Button
    private lateinit var  deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_situation)
        situation=findViewById(R.id.et_situation)
        meaning=findViewById(R.id.et_meaning)
        saveButton=findViewById(R.id.save)
        deleteButton=findViewById(R.id.delete)
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


        }
    }
}
