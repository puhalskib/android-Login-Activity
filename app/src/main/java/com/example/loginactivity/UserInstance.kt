package com.example.loginactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_instance.*

class UserInstance : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_instance)

        //get intent parcelable
        val user = intent.getParcelableExtra<User>("userS")

        //print username and user data to text fields
        userNameText.text = user.username
        userData.setText(user.data, TextView.BufferType.EDITABLE)

        //save button listener sends result back to main activity
        saveData.setOnClickListener{
            user.data = userData.text.toString()

            //create intent
            val rIntent = Intent()

            //put user into intent
            rIntent.putExtra("userR", user)
            setResult(Activity.RESULT_OK,rIntent)
            finish()
        }

    }
}
