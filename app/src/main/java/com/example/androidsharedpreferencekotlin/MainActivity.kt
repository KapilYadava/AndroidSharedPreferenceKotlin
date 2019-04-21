package com.example.androidsharedpreferencekotlin

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , OnClickListener{

    private var preference: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnKill.setOnClickListener(this)
        btnSave.setOnClickListener(this)
        preference = getSharedPreferences("mydata", Context.MODE_PRIVATE)
        if (preference !=null){
            preference?.apply {
                val email = getString("EMAIL", null)
                val name = getString("NAME", null)
                val mobile1 = getString("MOBILE1", null)
                val mobile2 = getString("MOBILE2", null)
                //val city = getString("CITY", null)

                if (!email.isNullOrEmpty())
                    this@MainActivity.email.text = Editable.Factory.getInstance().newEditable(email)
                if (!mobile1.isNullOrEmpty())
                    this@MainActivity.mobile1.text = Editable.Factory.getInstance().newEditable(mobile1)
                if (!mobile2.isNullOrEmpty())
                    this@MainActivity.alternateMobie.text = Editable.Factory.getInstance().newEditable(mobile2)
                if (!name.isNullOrEmpty())
                    this@MainActivity.name.text = Editable.Factory.getInstance().newEditable(name)
            }

        }
    }

    override fun onClick(view: View?) {
        when(view){
            btnSave ->{

                val name = name.text.toString()
                val email = email.text.toString()
                val mobile1 = mobile1.text.toString()
                val mobile2 = alternateMobie.text.toString()

                if (name.isEmpty() || email.isEmpty() || mobile1.isEmpty() || mobile2.isEmpty()) {
                    Toast.makeText(this, "All fields are mandatory!", Toast.LENGTH_LONG).show()
                    return
                }

                preference!!.edit().apply{
                    putString("NAME", name)
                    putString("EMAIL", email)
                    putString("MOBILE1", mobile1)
                    putString("MOBILE2", mobile2)
                    commit()
                }
            }
            btnKill -> {
                finish()
            }
        }
    }
}
