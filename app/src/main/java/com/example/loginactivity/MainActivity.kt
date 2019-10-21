package com.example.loginactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_instance.*
import java.io.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            //create intent
            val intent = Intent(this, UserInstance::class.java)

            //get username and password
            val file:String = usernameField.text.toString() + ".txt"
            val data:String = passwordField.text.toString()

            //read from file
            //todo check if file exists or not!!!!
            val filename = file
            if (filename.toString() != null && filename.toString().trim() != "") {
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                //Displaying data
                userData.setText(stringBuilder.toString()).toString()
                if(stringBuilder.toString() == data) {
                    login(file, data)
                } else {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "file name cannot be blank", Toast.LENGTH_LONG)
                    .show()
            }

        }
    }
    private fun login(file: String, data: String) {
        val fileOutputStream:FileOutputStream
        try {
            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
        } catch (e: FileNotFoundException){
            e.printStackTrace()
        }catch (e: NumberFormatException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }catch (e: Exception){
            e.printStackTrace()
        }
        Toast.makeText(applicationContext,"data save",Toast.LENGTH_LONG).show()

        //pass data to intent
        intent.putExtra("fileNa", file)

        // start your next activity with intent
        startActivity(intent)
    }
}
