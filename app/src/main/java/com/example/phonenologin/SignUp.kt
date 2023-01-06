package com.example.phonenologin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.concurrent.TimeUnit

class SignUp : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
//    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit private var options: PhoneAuthOptions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        auth = FirebaseAuth.getInstance()

        login_btn.setOnClickListener {

            if (phoneNo.text.isNotEmpty() && phoneNo.text.length ==10) {

                val phoneNumber = "+91${phoneNo.text.toString()}"
                options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(phoneNumber)       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(this)                 // Activity (for callback binding)
                    .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                        }

                        override fun onCodeSent(
                            VerificationId: String,
                            Token: PhoneAuthProvider.ForceResendingToken
                        ) {
                            Log.d("Verification", VerificationId)
//                        super.onCodeSent(VerificationId, Token)
                            val intent = Intent(this@SignUp, OtpActivity::class.java)
                            intent.putExtra("VerificationId", VerificationId)
                            startActivity(intent)
                            finish()
                        }

                        override fun onVerificationFailed(p0: FirebaseException) {
                            Log.d("Failed", p0.toString())
                            Toast.makeText(this@SignUp, "Failed", Toast.LENGTH_SHORT).show()
                        }

                    })
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)

            }else{
                Toast.makeText(this, "Please Enter the Correct Number", Toast.LENGTH_SHORT).show()
            }

        }


        }

    }
