package com.blogspot.devofandroid.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.blogspot.devofandroid.chatapp.databinding.ActivityChatBinding
import com.blogspot.devofandroid.chatapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth= Firebase.auth


       loginbuton1.setOnClickListener {
           auth.signInWithEmailAndPassword(editTextTextPersonName2.text.toString(),editTextTextPassword.text.toString()).addOnSuccessListener {
               val i = Intent(this@MainActivity,Chat::class.java)
               startActivity(i)
           }.addOnFailureListener {
               Toast.makeText(this, it.localizedMessage,Toast.LENGTH_LONG).show()
           }
        }

        signupbuton1.setOnClickListener {

            val intentb = Intent(this@MainActivity,Signin::class.java)
            startActivity(intentb)
        }
    }

}