package com.example.tralova.ui.share

import android.net.Uri

object DataGroup {
    var GroupName: ArrayList<String> = arrayListOf()
    var GroupImage: ArrayList<String> = arrayListOf()
    var listData: ArrayList<Group>
        get() {
            val list = arrayListOf<Group>()
            for (i in GroupName.indices) {
                val Contact = Group()
                Contact.GroupName = GroupName[i]
                Contact.GroupPhoto = Uri.parse(GroupImage[i])
                list.add(Contact)
            }
            return list
        }
        set(data) {
            for (i in data) {
                GroupName.add(i.GroupName.toString())
                GroupImage.add(i.GroupPhoto.toString())
            }
        }
}