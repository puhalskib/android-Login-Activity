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
        var strFile: String = intent.getStringExtra("fileNa")

        //read from file
        val filename = strFile
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
        } else {
            Toast.makeText(applicationContext, "file name cannot be blank", Toast.LENGTH_LONG)
                .show()
        }



        saveData.setOnClickListener(View.OnClickListener {
            val file:String = strFile
            val data:String = userData.text.toString()
            val fileOutputStream: FileOutputStream
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
        })

    }

}
