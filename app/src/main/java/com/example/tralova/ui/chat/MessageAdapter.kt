package com.example.tralova.ui.chat

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList
import java.util.List

// MessageAdapter.java
class MessageAdapter(internal var context: Context) : BaseAdapter() {

    var messages: ArrayList<Message> = ArrayList()

    fun add(message: Message) {
        this.messages.add(message)
        notifyDataSetChanged() // to render the list we need to notify
    }

    override fun getCount(): Int {
        return messages.size
    }

    override fun getItem(i: Int): Any {
        return messages[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    // This is the backbone of the class, it handles the creation of single ListView row (chat bubble)
    override fun getView(i:Int, convertView: View, viewGroup: ViewGroup): View {
        var convertView:View
        val holder = MessageViewHolder()
        val messageInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val message = messages.get(i)
        if (message.isBelongsToCurrentUser)
        { // this message was sent by us so let's create a basic chat bubble on the right
            convertView = messageInflater.inflate(com.example.tralova.R.layout.my_message, null)
            holder.messageBody = convertView.findViewById(com.example.tralova.R.id.message_body) as TextView
            convertView.setTag(holder)
            holder.messageBody.setText(message.text)
        }
        else
        { // this message was sent by someone else so let's create an advanced chat bubble on the left
            convertView = messageInflater.inflate(com.example.tralova.R.layout.their_message, null)
            holder.avatar = convertView.findViewById(com.example.tralova.R.id.avatar) as View
            holder.name = convertView.findViewById(com.example.tralova.R.id.name) as TextView
            holder.messageBody = convertView.findViewById(com.example.tralova.R.id.message_body) as TextView
            convertView.setTag(holder)
            holder.name.setText(message.memberData.name)
            holder.messageBody.setText(message.text)
            val drawable = holder.avatar.getBackground() as GradientDrawable
            drawable.setColor(Color.parseColor(message.memberData.color))
        }
        return convertView
    }
    internal class MessageViewHolder {
        lateinit var avatar: View
        lateinit var name: TextView
        lateinit var messageBody: TextView
    }
}
