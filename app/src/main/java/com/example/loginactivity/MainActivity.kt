package com.example.loginactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val users = (this.application as UserList).users

        loginButton.setOnClickListener {
            //create intent
            val intent = Intent(this, UserInstance::class.java)

            //get username and password
            val inputUser:String = usernameField.text.toString()
            val inputPass:String = passwordField.text.toString()

            //search for username in arrayList
            for(item in users) {
                if(item.username == inputUser) {
                    if(item.password == inputPass) {
                        //create intent to userInstance activity
                        val intent = Intent(this, UserInstance::class.java)

                        //put user into intent
                        intent.putExtra("user", item)

                        //launch second activity to login
                        startActivity(intent)

                    } else {
                        //incorrect password
                        Toast.makeText(this, "Incorrect Password", Toast.LENGTH_LONG).show()
                    }
                }
            }
            //send new user through UserInstance
            val intent2 = Intent(this, UserInstance::class.java)
            users.add(User(inputUser, inputPass))
            intent2.putExtra("user", users.last())
            startActivity(intent2)


        }
    }
}
