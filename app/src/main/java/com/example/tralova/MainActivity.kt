@file:Suppress("DEPRECATION")

package com.example.tralova

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
//const private val EXTRA_STATUS= "STATUS_STATE"


class MainActivity : AppCompatActivity(), OnMapReadyCallback{
    private lateinit var map: GoogleMap


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val medan = LatLng(3.6422756, 98.5294067)
        map.addMarker(MarkerOptions().position(medan).title("Your Location"))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(medan, 10f))
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        fun isConnectingToInternet(context: Context): Boolean {
            val connectivity = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.allNetworkInfo
                if (info != null)
                    for (i in info)
                        if (i.state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
            }
            return false
        }
        if (isConnectingToInternet(this@MainActivity)) {
            Toast.makeText(applicationContext,"Ada Koneksi Internet", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext,"Tidak Ada Koneksi Internet", Toast.LENGTH_LONG).show()
        }

//        val fab: FloatingActionButton = findViewById(com.example.tralova.R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_tools,
                R.id.nav_share,
                R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        //login
//        val  nama : TextView = findViewById(com.example.tralova.R.id.tv_namaMain)
//        nama.setText(Preferences().getLoggedInUser(getBaseContext()))
        //logout
//        button_logoutMain.setOnClickListener(View.OnClickListener {
//            //Menghapus Status login dan kembali ke Login Activity
//            Preferences().clearLoggedInUser(baseContext)
//            startActivity(Intent(baseContext, LoginActivity::class.java))
//            finish()
//        })
        //click menu drawer
//        navView.setNavigationItemSelectedListener(
//            object : NavigationView
//            .onNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
//                override fun onNavigationItemSelected(p0: MenuItem): Boolean {
//                    when(p0.itemId){}
//                    return true
//                }
//
//
//            }
//        )

    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString(EXTRA_STATUS,tv_group_name.text.toString())
//    }
//    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//        super.onRestoreInstanceState(savedInstanceState)
//        tv_group_name.text = savedInstanceState?.
//            getString(EXTRA_STATUS) ?: "Kosong"
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        //login
        val nama: TextView = findViewById(R.id.tv_namaMain)
        nama.setText(Preferences().getLoggedInUser(getBaseContext()))
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Preferences().clearLoggedInUser(baseContext)
                startActivity(Intent(baseContext, LoginActivity::class.java))
                finish()
                true
            }
            R.id.akun -> {
                val pindahkeProfile = Intent(this@MainActivity, Profil_Activity::class.java)
                startActivity(pindahkeProfile)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
