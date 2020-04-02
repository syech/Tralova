package com.example.tralova.ui.slideshow

import android.net.Uri
import com.example.tralova.ui.share.DataGroup
import com.example.tralova.ui.share.Group

object DataRemind {
    var RemindName: ArrayList<String> = arrayListOf()
    var RemindDesk: ArrayList<String> = arrayListOf()
    var RemindDate: ArrayList<String> = arrayListOf()
    var RemindTime: ArrayList<String> = arrayListOf()

    var listDataRemind: ArrayList<Reminder>
        get() {
            val list = arrayListOf<Reminder>()
            for (i in DataRemind.RemindName.indices) {
                val Contact = Reminder()
                Contact.remind = DataRemind.RemindName[i]
                Contact.deskRemind = DataRemind.RemindDesk[i]
                Contact.date = DataRemind.RemindDate[i]
                Contact.time = DataRemind.RemindTime[i]
                list.add(Contact)
            }
            return list
        }
        set(data) {
            for (i in data) {
                DataRemind.RemindName.add(i.remind.toString())
                DataRemind.RemindDesk.add(i.deskRemind.toString())
                DataRemind.RemindDate.add(i.date.toString())
                DataRemind.RemindTime.add(i.time.toString())
            }
        }

}