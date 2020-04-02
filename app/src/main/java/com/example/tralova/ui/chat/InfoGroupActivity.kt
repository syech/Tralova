package com.example.tralova.ui.chat

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tralova.MainActivity
import com.example.tralova.R
import com.example.tralova.ui.gallery.HealthCare
import com.example.tralova.ui.gallery.HealtyCareAdapter
import com.example.tralova.ui.gallery.TouristData
import kotlinx.android.synthetic.main.activity_info_group.*
import kotlinx.android.synthetic.main.dialog_invite.*

class InfoGroupActivity : AppCompatActivity() {
    private lateinit var rvAng: RecyclerView
    private var list: ArrayList<HealthCare> = arrayListOf()

    lateinit var builder: AlertDialog.Builder
    lateinit var todoAlert : AlertDialog
    lateinit var addToDoLayout : LayoutInflater
    lateinit var myLayout : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_group)

        namaGroup.setOnClickListener{
            editGroup.setVisibility(View.VISIBLE)
            namaGroup.setVisibility(View.INVISIBLE)
        }
        DeskGroup.setOnClickListener{
            editDeskGroup.setVisibility(View.VISIBLE)
            DeskGroup.setVisibility(View.INVISIBLE)
        }

        rvAng = findViewById(R.id.rvAnggota)
        rvAng.setHasFixedSize(true)

        list.addAll(TouristData.listDataWisatawan)
        showRecycleList()

        addToDoLayout = layoutInflater
        myLayout = addToDoLayout.inflate(R.layout.dialog_invite,null)

    }

    private fun showRecycleList() {
        rvAng.layoutManager = LinearLayoutManager(this)
        val List_Ang = HealtyCareAdapter(list)
        rvAng.adapter = List_Ang
    }

    fun DialogAdd(view: View){
        builder = AlertDialog.Builder(this)
        val scr = myLayout.findViewById<EditText>(R.id.searchInvite)
        val contact = myLayout.findViewById<TextView>(R.id.tv_contact)
        val cb1 = myLayout.findViewById<CheckBox>(R.id.cb_1)
        val cb2 = myLayout.findViewById<CheckBox>(R.id.cb_2)
        val cb3 = myLayout.findViewById<CheckBox>(R.id.cb_3)
        val cb4 = myLayout.findViewById<CheckBox>(R.id.cb_4)
        val pp1 = myLayout.findViewById<ImageView>(R.id.pp_1)
        val pp2 = myLayout.findViewById<ImageView>(R.id.pp_2)
        val pp3 = myLayout.findViewById<ImageView>(R.id.pp_3)
        val pp4 = myLayout.findViewById<ImageView>(R.id.pp_4)
        val btncancel = myLayout.findViewById<Button>(R.id.btn_cancel)
        val btninvite = myLayout.findViewById<Button>(R.id.btn_invite)

        todoAlert = builder.create()

        todoAlert.setView(myLayout)
        todoAlert.setTitle("Tambah Anggota")
        todoAlert.show()

    }

    fun cancelTodo(view: View){
        todoAlert.dismiss()
    }
    fun Invite(view: View){
        showNotif()
    }
    private fun showNotif() {
        val NOTIFICATION_CHANNEL_ID = "channel_androidnotif"
        val context = this.applicationContext
        var notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Android Notif Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

        val mIntent = Intent(this, ChatGroupActivity::class.java)
        val bundle = Bundle()
        bundle.putString("fromnotif", "notif")
        mIntent.putExtras(bundle)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(com.example.tralova.R.drawable.ic_notif)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    com.example.tralova.R.drawable.ic_notif
                )
            )
            .setTicker("notif starting")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("User Admin Mengundang anda dalam Group")
            .setContentText("Ketuk untuk obrolan")

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())
    }

}
