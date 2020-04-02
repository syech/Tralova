package com.example.tralova.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R
import android.R.attr.*

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.util.*
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.tralova.ui.chat.ChatGroupActivity.MemberData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.TextView
import android.graphics.Color.parseColor
import android.graphics.drawable.GradientDrawable
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.*

import android.widget.BaseAdapter
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.scaledrone.lib.*
import android.widget.ListView
import com.bumptech.glide.Glide
import com.example.tralova.LoginActivity
import com.example.tralova.MainActivity
import com.example.tralova.Preferences
import com.example.tralova.Profil_Activity
import com.example.tralova.ui.share.Group
import com.example.tralova.ui.tools.Article
import com.example.tralova.ui.tools.DetailArticleActivity
import kotlinx.android.synthetic.main.activity_chat_group.*
import kotlinx.android.synthetic.main.activity_detail__article.*


class ChatGroupActivity : AppCompatActivity(), RoomListener {

    private val channelID = "PfFUcy1rsPGMCTwD"
    private val roomName = "observable-room"
    private lateinit var editText: EditText
    private lateinit var scaledrone: Scaledrone
    private lateinit var messageAdapter:MessageAdapter
    private lateinit var messagesView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tralova.R.layout.activity_chat_group)

        editText = findViewById(com.example.tralova.R.id.editText)
        messageAdapter = MessageAdapter(this@ChatGroupActivity)
//        messagesView = findViewById(com.example.tralova.R.id.messages_view)
//        messagesView.setAdapter(messageAdapter)


        val data = MemberData(getRandomName(), getRandomColor())
        scaledrone = Scaledrone(channelID, data)

        scaledrone.connect(object:Listener{
            override fun onOpen() {
                println("Scaledrone connection open")
                // Since the ChatGroupActivity itself already implement RoomListener we can pass it as a target
                scaledrone.subscribe(roomName,this@ChatGroupActivity)
            }
            override fun onOpenFailure(ex:Exception) {
                System.err.println(ex)
            }
            override fun onFailure(ex:Exception) {
                System.err.println(ex)
            }
            override fun onClosed(reason:String) {
                System.err.println(reason)
            }
        })

//        //mengambil data secara Parcelable
//        val dataGroup = intent.getParcelableExtra<Group>(ChatGroupActivity.DATA_GROUP)
//
//        //menampilkan data sesuai dataHealthCare
//        dataGroup?.let {
//            .text = it.GroupName
//
//        }
    }

    companion object {
        const val DATA_GROUP = "DATA_GROUP"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(com.example.tralova.R.menu.menu_infogroup, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.example.tralova.R.id.infogroup -> {
                val pindahkeProfile = Intent(this@ChatGroupActivity, InfoGroupActivity::class.java)
                startActivity(pindahkeProfile)
                true
            }
            R.id.home->{
                val pindahkeProfile = Intent(this@ChatGroupActivity, MainActivity::class.java)
                startActivity(pindahkeProfile)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun sendMessage(view: View) {
        chat1.text = editText.getText().toString()
        chat1.setVisibility(View.VISIBLE)
        user1.setVisibility(View.VISIBLE)

        chat2.text = editText.getText().toString()
        chat2.setVisibility(View.VISIBLE)
        chat3.setVisibility(View.VISIBLE)
//        val message = editText.getText().toString()
//        if (message.length > 0) {
//            scaledrone.publish(roomName, message)
//            editText.getText().clear()
//
//        }
    }


    override fun onOpen(room: Room?) {
        System.out.println("Conneted to room")
    }

    override fun onOpenFailure(room: Room, ex: Exception) {
        System.err.println(ex)
    }

    override fun onMessage(room: Room?, receivedMessage: com.scaledrone.lib.Message?) {
        // To transform the raw JsonNode into a POJO we can use an ObjectMapper
        val mapper = ObjectMapper()
        try
        {
            // member.clientData is a MemberData object, let's parse it as such
            val data = mapper.treeToValue(receivedMessage!!.getMember().getClientData(), MemberData::class.java)
            // if the clientID of the message sender is the same as our's it was sent by us
            val belongsToCurrentUser = receivedMessage!!.getClientID().equals(scaledrone.getClientID())
            // since the message body is a simple string in our case we can use json.asText() to parse it as such
            // if it was instead an object we could use a similar pattern to data parsing
            val message = Message(receivedMessage.getData().asText(), data, belongsToCurrentUser)
            runOnUiThread(object:Runnable {
                public override fun run() {
                    messageAdapter.add(message)
                    // scroll the ListView to the last added element
//                    messagesView.setSelection(messagesView.getCount() - 1)
                }
            })
        }
        catch (e:JsonProcessingException) {
            e.printStackTrace()
        }
    }

    private fun getRandomName(): String {
        val adjs = arrayOf("autumn", "hidden", "bitter", "misty", "silent", "empty", "dry", "dark", "summer", "icy", "delicate",
            "quiet", "white", "cool", "spring", "winter", "patient", "twilight", "dawn", "crimson", "wispy", "weathered", "blue",
            "billowing", "broken", "cold", "damp", "falling", "frosty", "green", "long", "late", "lingering", "bold", "little",
            "morning", "muddy", "old", "red", "rough", "still", "small", "sparkling", "throbbing", "shy", "wandering", "withered", "wild", "black",
            "young", "holy", "solitary", "fragrant", "aged", "snowy", "proud", "floral", "restless", "divine", "polished", "ancient",
            "purple", "lively", "nameless"
        )
        val nouns = arrayOf(
            "waterfall",
            "river",
            "breeze",
            "moon",
            "rain",
            "wind",
            "sea",
            "morning",
            "snow",
            "lake",
            "sunset",
            "pine",
            "shadow",
            "leaf",
            "dawn",
            "glitter",
            "forest",
            "hill",
            "cloud",
            "meadow",
            "sun",
            "glade",
            "bird",
            "brook",
            "butterfly",
            "bush",
            "dew",
            "dust",
            "field",
            "fire",
            "flower",
            "firefly",
            "feather",
            "grass",
            "haze",
            "mountain",
            "night",
            "pond",
            "darkness",
            "snowflake",
            "silence",
            "sound",
            "sky",
            "shape",
            "surf",
            "thunder",
            "violet",
            "water",
            "wildflower",
            "wave",
            "water",
            "resonance",
            "sun",
            "wood",
            "dream",
            "cherry",
            "tree",
            "fog",
            "frost",
            "voice",
            "paper",
            "frog",
            "smoke",
            "star"
        )
        return adjs[Math.floor(Math.random() * adjs.size).toInt()] +
                "_" +
                nouns[Math.floor(Math.random() * nouns.size).toInt()]
    }

    private fun getRandomColor(): String {
        val r = Random()
        val sb = StringBuffer("#")
        while (sb.length < 7) {
            sb.append(Integer.toHexString(r.nextInt()))
        }
        return sb.toString().substring(0, 7)
    }

    class MemberData {
        var name:String = ""
            get() {
                return name
            }
        var color:String = ""
            get() {
                return color
            }
        constructor(name:String, color:String) {
            this.name = name
            this.color = color
        }
        // Add an empty constructor so we can later parse JSON into MemberData using Jackson
        constructor() {}
    }

}
