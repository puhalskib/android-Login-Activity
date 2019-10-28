package com.example.loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //list of all users in application
    private val users = ArrayList<User>()
    private val REQ_CODE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            //get username and password
            val inputUser:String = usernameField.text.toString()
            val inputPass:String = passwordField.text.toString()

            var loggedIn = false
            //search for username in arrayList
            for(item in users) {
                if(item.username == inputUser) {
                    loggedIn = true
                    if(item.password == inputPass) {
                        //create intent to userInstance activity
                        val intent = Intent(this, UserInstance::class.java)

                        //put user into intent
                        intent.putExtra("userS", item)

                        //launch second activity to login
                        startActivityForResult(intent, REQ_CODE)

                    } else {
                        //incorrect password
                        Toast.makeText(this, "Incorrect Password", Toast.LENGTH_LONG).show()
                    }
                }
            }
            //create a new user
            if(loggedIn == false) {
                //send new user through UserInstance
                val intent2 = Intent(this, UserInstance::class.java)
                users.add(User(inputUser, inputPass, getString(R.string.newUser)))
                intent2.putExtra("userS", users.last())
                startActivityForResult(intent2, REQ_CODE)
            }

        }

    }
    //TODO show and hide password

    //override onStartActivityForResult() return
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE && resultCode == resultCode) {
            if (data != null) {
                val userI = data.getParcelableExtra<User>("userR")
                //find user in users
                for(item in users.indices) {
                    if(users[item].username == userI.username) {
                        //rewrite to users
                        users[item].data = userI.data
                    }
                }
            }

        }
        else {
            //error message
            Toast.makeText(this, "Error on return", Toast.LENGTH_LONG).show()
        }
    }
}


