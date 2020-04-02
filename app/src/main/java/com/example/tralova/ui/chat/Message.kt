package com.example.tralova.ui.chat

// Message.java
class Message(text:String, memberData: ChatGroupActivity.MemberData, belongsToCurrentUser:Boolean) {
    val text:String // message body
    val memberData: ChatGroupActivity.MemberData // data of the user that sent this message
    var isBelongsToCurrentUser:Boolean = false // is this message sent by us?
    init{
        this.text = text
        this.memberData = memberData
        this.isBelongsToCurrentUser = belongsToCurrentUser
    }
}