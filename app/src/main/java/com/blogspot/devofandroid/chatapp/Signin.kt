package com.blogspot.devofandroid.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.blogspot.devofandroid.chatapp.databinding.ActivityMainBinding
import com.blogspot.devofandroid.chatapp.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signin.*

class Signin : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth
        setContentView(R.layout.activity_signin)
        signupbuton2.setOnClickListener {
            auth.createUserWithEmailAndPassword(mail.text.toString(),sifre.text.toString()).addOnSuccessListener {

            }.addOnFailureListener { exception ->
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show()
            }
        }
    }
}