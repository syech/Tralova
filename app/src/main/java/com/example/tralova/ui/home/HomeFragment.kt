package com.example.tralova.ui.home

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tralova.MainActivity
import com.example.tralova.R
import com.example.tralova.ui.chat.ChatGroupActivity
import com.example.tralova.ui.recomended.RecomendedActivity
import com.example.tralova.ui.tools.AddArticleActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService

import android.graphics.BitmapFactory

import android.app.*
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat


class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var map: GoogleMap

    companion object {
        var mapFragment : SupportMapFragment?=null
        var mapRoute : SupportMapFragment?=null
        val TAG: String = MapFragment::class.java.simpleName
        // ini variabel untuk izin track auto location, tapi ini nanti nanti aja, masih error.
//        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
        //ini untuk perintah track cek permissionnya, masih error
//    private fun setUpMap() {
//        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
//                HomeFragment.LOCATION_PERMISSION_REQUEST_CODE
//            )
//            return
//        }
//    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val medan = LatLng(3.6422756, 98.5294067)
            val INITIAL_STROKE_WIDTH_PX = 5
            val binjai = LatLng(3.6223238,98.4661843)
            map.addMarker(MarkerOptions().position(medan).title("Medan"))
            map.addMarker(MarkerOptions().position(binjai).title("Binjai"))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(medan, 13f))
            with(googleMap){
                addPolyline(PolylineOptions().apply {
                    add(medan, binjai)
                    width(INITIAL_STROKE_WIDTH_PX.toFloat())
                    color(Color.BLUE)
                    geodesic(false)
                    clickable(true)
                })
            }
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(medan, 13f))

            // Add a listener for polyline clicks that changes the clicked polyline's color.
            map.setOnPolylineClickListener { polyline ->
                // Flip the values of the red, green and blue components of the polyline's color.
                polyline.color = polyline.color xor 0x00ffffff
            }
//        setUpMap()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(com.example.tralova.R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer { textView.text = it })
        // Inflate the layout for this fragment

        mapFragment = childFragmentManager.findFragmentById(com.example.tralova.R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        //auto complete fragment gabsia
//        val autocompleteFragment = fragmentManager.findFragmentById(R.id.autocomplete_fragment)
//                as PlaceAutocompleteFragment
//        autocompleteFragment.setOnPlaceSelectedListener(this)
        root.btn_toRecom.setOnClickListener{
            gotoRecom()
        }
        root.btn_chat.setOnClickListener {
            gotoChat()
        }
        root.btn_notif.setOnClickListener {
            showNotif()
        }
        return root
    }

    fun gotoRecom(){
        val Recom = Intent(requireContext(), RecomendedActivity::class.java)
        startActivity(Recom)
    }

    fun gotoChat(){
        val Chat = Intent(requireContext(), ChatGroupActivity::class.java)
        startActivity(Chat)
    }

    private fun showNotif() {
        val NOTIFICATION_CHANNEL_ID = "channel_androidnotif"
        val context = this.requireContext()
        var notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Android Notif Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

        val mIntent = Intent(requireContext(), MainActivity::class.java)
        val bundle = Bundle()
        bundle.putString("fromnotif", "notif")
        mIntent.putExtras(bundle)
        val pendingIntent =
            PendingIntent.getActivity(requireContext(), 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(requireContext(), NOTIFICATION_CHANNEL_ID)
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
            .setContentTitle("Wisatawan membutuhkan Bantuan")
            .setContentText("Jarak 100 M")

        notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())
    }

}