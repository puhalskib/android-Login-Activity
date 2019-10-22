package com.example.loginactivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_instance.*
import java.io.*

class UserInstance : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_instance)

        //get intent
        val user = intent.getParcelableExtra<User>("user")
        



        saveData.setOnClickListener{
            val data:String = userData.text.toString()

            Toast.makeText(applicationContext,user.username + " Data Saved",Toast.LENGTH_LONG).show()
        }

    }

}
