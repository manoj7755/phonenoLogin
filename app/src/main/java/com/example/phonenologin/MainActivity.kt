package com.example.phonenologin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth= FirebaseAuth.getInstance()
        val currentUser=auth.currentUser

//        Reference
      val Database = Firebase.database
        SetData.setOnClickListener {
            val refDataPath =  Database.getReference("Message of User")
            val MessageData = message.text.toString()
            refDataPath.setValue(MessageData).addOnCompleteListener {
                Log.d("Successfullll" ,"${it.isSuccessful}")
            }.addOnFailureListener {
                Log.d("Failed" ,"${it.message}")
            }
        }


        if(currentUser==null){
            startActivity(Intent(this,SignUp::class.java))
            finish()
        }

        LogOut.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this,SignUp::class.java))
            finish()
        }


    }
}