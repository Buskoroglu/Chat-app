package com.blogspot.devofandroid.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatHolder>() {

    class ChatHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }

    private val diffUtil = object  : DiffUtil.ItemCallback<Chats>(){
        override fun areItemsTheSame(oldItem: Chats, newItem: Chats): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Chats, newItem: Chats): Boolean {
            return  oldItem == newItem
        }

    }
    private  val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var chats :List<Chats>
    get() =recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ChatHolder(view)
    }


    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val textView1 = holder.itemView.findViewById<TextView>(R.id.chatrecyclertextview)
        textView1.text = "${chats.get(position).user} : ${chats.get(position).text}"
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}