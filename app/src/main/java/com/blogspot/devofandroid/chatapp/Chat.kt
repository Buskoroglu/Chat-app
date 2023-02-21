package com.blogspot.devofandroid.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager.LayoutParams.*
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.devofandroid.chatapp.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chat.*



class Chat : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: ChatAdapter
    private var chats = arrayListOf<Chats>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = Firebase.firestore
        auth=Firebase.auth
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        adapter = ChatAdapter()
        binding.chatRecycler.adapter = adapter
        binding.chatRecycler.layoutManager = LinearLayoutManager(baseContext)
        setContentView(R.layout.activity_chat)
        getWindow().setSoftInputMode(SOFT_INPUT_ADJUST_PAN)
        sendbuton.setOnClickListener {

            auth.currentUser?.let {
                val user = it.email
                val chatText = chatText.text.toString()
                val date = FieldValue.serverTimestamp()
                val datamap = HashMap<String,Any>()
                datamap.put("text",chatText)
                datamap.put("user", user!!)
                datamap.put("date",date)
                firestore.collection("Chats").add(datamap).addOnSuccessListener{
                    chatText.toSet()
                }.addOnFailureListener {
                    Toast.makeText(this,"HATA",Toast.LENGTH_LONG).show()
                    chatText.toSet()
                }
            }
            }

        firestore.collection("Chats").orderBy("date",Query.Direction.ASCENDING).addSnapshotListener{ value, error ->
            if(error != null){

                Toast.makeText(applicationContext, error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            else{
                if(value !=null)
                {
                    if(value.isEmpty)
                    {
                        Toast.makeText(applicationContext,"EMPTY", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        val documents = value!!.documents
                        chats.clear()
                        for(document in documents){
                            val text = document.get("text") as String
                            val user = document.get("user") as String
                            val chat =Chats(user,text)
                            chats.add(chat)
                            adapter.chats = chats
                    }

                }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}