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

        callService(BuildConfig.BASE_URL, user)


    }
    private fun callService(url: String, u: User) {
        val urlFull = "http://www.full.com"
        if(url == urlFull) {
            userData.setText(u.data, TextView.BufferType.EDITABLE)

            //save button listener sends result back to main activity
            saveData.setOnClickListener {
                u.data = userData.text.toString()

                //create intent
                val rIntent = Intent()

                //put user into intent
                rIntent.putExtra("userR", u)
                setResult(Activity.RESULT_OK, rIntent)
                finish()
            }
        } else {
            saveData.text = getString(R.string.demoReturnBtn)
            saveData.setOnClickListener {
                //create intent
                val rIntent = Intent()

                //put user into intent
                rIntent.putExtra("userR", u)
                setResult(Activity.RESULT_OK, rIntent)
                finish()
            }
        }
    }
}
