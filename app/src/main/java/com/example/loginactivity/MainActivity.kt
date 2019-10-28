package com.example.loginactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //list of all users in application
    private val users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                        intent.putExtra("userS", item)

                        //launch second activity to login
                        startActivityForResult(intent, R.integer.userCode)

                    } else {
                        //incorrect password
                        Toast.makeText(this, "Incorrect Password", Toast.LENGTH_LONG).show()
                    }
                }
            }
            //send new user through UserInstance
            val intent2 = Intent(this, UserInstance::class.java)
            users.add(User(inputUser, inputPass))
            intent2.putExtra("userS", users.last())
            startActivityForResult(intent2, R.integer.userCode)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == R.integer.userCode) {
            if (data != null) {
                val userI = data.getParcelableExtra<User>("userR")
                for(item in users) {
                    if(item == userI) {
                        //rewrite to users
                        users[users.indexOf(item)].data = userI.data
                        Toast.makeText(this, "Successful write", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
        else {
            Toast.makeText(this, "Error on return", Toast.LENGTH_LONG).show()
        }
    }

}
