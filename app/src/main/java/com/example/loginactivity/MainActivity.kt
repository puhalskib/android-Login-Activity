package com.example.loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //list of all users in application
    private val users = mutableMapOf<String, User>()

    private val REQ_CODE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hide or show password button toggle
        hidePassbtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                passwordField.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                passwordField.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        //set login button on click event
        loginButton.setOnClickListener {
            //get username and password
            val inputUser: String = usernameField.text.toString()
            val inputPass: String = passwordField.text.toString()

            //if inputed user in mutablemap
            if (inputUser in users) {
                val chosenUser: User = users[inputUser]!!
                if (chosenUser.password == inputPass) {
                    //create intent to userInstance activity
                    val intent = Intent(this, UserInstance::class.java)

                    //put user into intent
                    intent.putExtra("userS", chosenUser)

                    //launch second activity to login
                    startActivityForResult(intent, REQ_CODE)
                } else {
                    //incorrect password
                    Toast.makeText(this, getString(R.string.incorrectPass), Toast.LENGTH_LONG).show()
                }
            } else {
                //create a new user
                //send new user through UserInstance
                val intent2 = Intent(this, UserInstance::class.java)

                //add user to mutable map
                users.put(inputUser, User(inputUser, inputPass, getString(R.string.newUser)))

                //add user to intent
                intent2.putExtra("userS", users[inputUser])

                //start userInstance activity
                startActivityForResult(intent2, REQ_CODE)
            }
        }

    }

    //override onStartActivityForResult() return
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //make sure requestCode is ok
        if (requestCode == REQ_CODE && resultCode == resultCode) {
            if (data != null) {
                if(data.getParcelableExtra<User>("userR") != null) {
                    val userI = data.getParcelableExtra<User>("userR")!!

                    //find user in users
                    if (userI.username in users) {
                        //rewrite to mutable map user.data
                        users[userI.username]!!.data = userI.data
                    }
                }
            } else {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
        }
    }
}


