package com.example.tralova.ui.share

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_addgroup.*


class AddGroupActivity : AppCompatActivity() {
    // Declare Data
    var image2: Uri? = null
    var groupData = arrayListOf<String>()
    private lateinit var confirmBuilder: MaterialAlertDialogBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tralova.R.layout.activity_addgroup)

        // Custom Toolbar
        var toolbar: androidx.appcompat.widget.Toolbar? =
            findViewById(com.example.tralova.R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(com.example.tralova.R.drawable.ic_action_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var toolbarText: TextView = findViewById(com.example.tralova.R.id.toolbar_text)
        if (toolbar != null) {
            toolbarText.text = title
            setSupportActionBar(toolbar);
        }

        // Alert
        confirmBuilder = MaterialAlertDialogBuilder(
            this,
            com.example.tralova.R.style.Theme_MaterialComponents_Dialog_Alert
        )
        confirmBuilder.setMessage("Discard your changes?")
            .setTitle("Cancel")
            .setPositiveButton("OK", DialogInterface.OnClickListener { DialogInterface, i ->
                finish()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { DialogInterface, i ->
                DialogInterface.dismiss()
            })

        //Untuk Ganti Profil ( Ambil dari Galeri )
        profil.setOnClickListener {
            // Untuk memeriksa Runtime Permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    // Permission Denied
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //Munculkan popup untuk merequest Runtime Permission
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    AksesGaleri()
                }
            } else {
                // Jika system OS lebih besar dari Lolipop
                AksesGaleri()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.tralova.R.menu.menu_add_group, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == com.example.tralova.R.id.saveGroup) saveGroup()
        else if (item.itemId == android.R.id.home) {
            if (findViewById<EditText>(com.example.tralova.R.id.groupName).text.toString() != "" ||
                image2 != null
            ) {
                val confirm = confirmBuilder.create()
                confirm.show()
                return true
            } else finish()

        }
        return super.onOptionsItemSelected(item)
    }

    //Akses Galeri ( Start )
    private fun AksesGaleri() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, 1)
        }
    }

    companion object {
        private val PERMISSION_CODE = 110
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AksesGaleri()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            profil.setImageURI(data?.data)
            image2 = data?.data
//            val imageUri : Uri? = data!!.data
//            image = getPath(this.applicationContext, imageUri ?: Uri.parse("")) ?: "Null"
        }
    }

    //     Get Path from Intent Gallery URI
    private fun getPath(context: Context, uri: Uri): String? {
        var result = ""
        var cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        var doc = cursor?.getString(0)
        doc = doc?.substring(doc?.lastIndexOf(":") + 1)
        cursor?.close()

        cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
            MediaStore.Images.Media._ID + " = ? ", arrayOf(doc), null
        )

        cursor?.moveToFirst()
        val path = cursor?.getString(cursor?.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor?.close()

        return path
    }

    fun saveGroup() {
        if (findViewById<EditText>(com.example.tralova.R.id.groupName).text.toString() != "") {
            groupData.add(findViewById<EditText>(com.example.tralova.R.id.groupName).text.toString())
//        val nameFile = contactData[0]+"_"+contactData[1]
            // Save image to app data
            groupData.add(image2.toString())
        }
//        saveImage(this.applicationContext,convertToBitmap(this.applicationContext,
//            image2?: Uri.parse("")),nameFile,"png")
//        contactData.add(nameFile)
        finish()
    }

    override fun finish() {
        var returnIntent = Intent()
        if (groupData.size == 2)
            returnIntent.putExtra("groupData", groupData)
        setResult(Activity.RESULT_OK, returnIntent)
        super.finish()
    }
}
