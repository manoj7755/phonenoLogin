package com.example.phonenologin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otp.*

class OtpActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        auth = FirebaseAuth.getInstance()
        val verificationId = intent.getStringExtra("VerificationId")
        Varify_Btn.setOnClickListener {
            val otps = otp.text.toString()
            val credential = PhoneAuthProvider.getCredential(verificationId!!, otps)

            auth.signInWithCredential(credential).addOnCompleteListener {
                Log.d("Successful", "${it.isSuccessful}")
                startActivity(Intent(this@OtpActivity, MainActivity::class.java))
                finish()

            }.addOnFailureListener {
                Log.d("Fail", "${it.message}")
            }

        }
    }

}
