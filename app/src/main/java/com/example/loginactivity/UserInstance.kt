package com.example.loginactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_instance.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class UserInstance : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_instance)

        //get intent
        var strFile: String = intent.getStringExtra("name1")
        //userData.text = strFile





        val filename = strFile
        if(filename.toString()!=null && filename.toString().trim()!=""){
            var fileInputStream: FileInputStream? = null
            fileInputStream = openFileInput(filename)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }
            //Displaying data on EditText
            //fileData.setText(stringBuilder.toString()).toString()
            userData.text = stringBuilder.toString()
        } else {
            Toast.makeText(applicationContext,"file name cannot be blank",Toast.LENGTH_LONG).show()
        }

    }

}
