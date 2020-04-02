package com.example.tralova.ui.tools

import android.Manifest
import android.app.Activity
import android.content.Context
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
import com.example.tralova.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_add_article.*

class AddArticleActivity : AppCompatActivity() {

    // Declare Data
    private var image3: Uri? = null
    private var articleData = arrayListOf<String>()

    private lateinit var confirmBuilder: MaterialAlertDialogBuilder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article)

        // Custom Toolbar
        val toolbar1: androidx.appcompat.widget.Toolbar? =
            findViewById(R.id.toolbarArt)
        setSupportActionBar(toolbar1)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toolbarText: TextView = findViewById(R.id.toolbar_text)
        if (toolbar1 != null) {
            toolbarText.text = title
            setSupportActionBar(toolbar1)
        }

        // Alert
        confirmBuilder = MaterialAlertDialogBuilder(
            this,
            R.style.Theme_MaterialComponents_Dialog_Alert
        )
        confirmBuilder.setMessage("Discard your changes?")
            .setTitle("Cancel")
            .setPositiveButton("OK") { _, _ ->
                finish()
            }
            .setNegativeButton("Cancel") { DialogInterface, _ ->
                DialogInterface.dismiss()
            }
        photoArt.setImageResource(R.drawable.ic_add_photo)
        //Untuk Ganti Profil ( Ambil dari Galeri )
        photoArt.setOnClickListener {
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
        menuInflater.inflate(R.menu.menu_add_article, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveArticle) saveArticle()
        else if (item.itemId == android.R.id.home) {
            if (findViewById<EditText>(R.id.JudulArt).text.toString() != "" ||
                findViewById<EditText>(R.id.SumberArt).text.toString() != "" ||
                findViewById<EditText>(R.id.IsiArt).text.toString() != "" ||
                image3 != null
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
        private const val PERMISSION_CODE = 110
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
            photoArt.setImageURI(data?.data)
            image3 = data?.data
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
        doc = doc?.substring(doc.lastIndexOf(":") + 1)
        cursor?.close()

        cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
            MediaStore.Images.Media._ID + " = ? ", arrayOf(doc), null
        )

        cursor?.moveToFirst()
        val path = cursor?.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor?.close()

        return path
    }

    fun saveArticle() {
        if (JudulArt.text.toString() != "" &&
            SumberArt.text.toString() != "" &&
            IsiArt.text.toString() != ""
        ) {
            articleData.add(JudulArt.text.toString())
            articleData.add(SumberArt.text.toString())
            articleData.add(IsiArt.text.toString())

//        val nameFile = contactData[0]+"_"+contactData[1]
            // Save image to app data
            articleData.add(image3.toString())
        }
//        saveImage(this.applicationContext,convertToBitmap(this.applicationContext,
//            image2?: Uri.parse("")),nameFile,"png")
//        contactData.add(nameFile)
        finish()
    }

    override fun finish() {
        val returnIntent = Intent()
        if (articleData.size == 4)
            returnIntent.putExtra("articleData", articleData)
        setResult(Activity.RESULT_OK, returnIntent)
        super.finish()
    }
}
