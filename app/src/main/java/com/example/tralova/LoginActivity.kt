package com.example.tralova

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var mViewUser: EditText
    private lateinit var mViewPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /* Menginisialisasi variable dengan Form User dan Form Password dari Layout LoginActivity */
        mViewUser = findViewById(R.id.et_emailSignin)
        mViewPassword = findViewById(R.id.et_passwordSignin)
        /* Menjalankan Method razia() Jika tombol SignIn di keyboard di sentuh */
        mViewPassword.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia()
                    return true
                }
                return false
            }
        })

        /* Menjalankan Method razia() jika merasakan tombol SignIn disentuh */
        button_signinSignin.setOnClickListener(View.OnClickListener { razia() })

        /* Ke RegisterActivity jika merasakan tombol SignUp disentuh */
        button_signupSignin.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(baseContext, RegisterActivity::class.java)
            )
        })


    }

    override fun onStart() {
        super.onStart()
        if (Preferences().getLoggedInStatus(baseContext)) {
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }
    }

    /** Men-check inputan User dan Password dan Memberikan akses ke MainActivity  */
    private fun razia() {
        /* Mereset semua Error dan fokus menjadi default */
        mViewUser.error = null
        mViewPassword.error = null
        var fokus: View? = null
        var cancel = false

        /* Mengambil text dari form User dan form Password dengan variable baru bertipe String*/
        val user = mViewUser.text.toString()
        val password = mViewPassword.text.toString()

        /* Jika form user kosong atau TIDAK memenuhi kriteria di Method cekUser() maka, Set error
         *  di Form User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(user)) {
            mViewUser.error = "This field is required"
            fokus = mViewUser
            cancel = true
        } else if (!cekUser(user)) {
            mViewUser.error = "This Username is not found"
            fokus = mViewUser
            cancel = true
        }

        /* Sama syarat percabangannya dengan User seperti di atas. Bedanya ini untuk Form Password*/
        if (TextUtils.isEmpty(password)) {
            mViewPassword.error = "This field is required"
            fokus = mViewPassword
            cancel = true
        } else if (!cekPassword(password)) {
            mViewPassword.error = "This password is incorrect"
            fokus = mViewPassword
            cancel = true
        }

        /* Jika cancel true, variable fokus mendapatkan fokus */
        if (cancel)
            fokus!!.requestFocus()
        else
            masuk()
    }

    /** Menuju ke MainActivity dan Set User dan Status sedang login, di Preferences */
    private fun masuk() {
        Preferences().setLoggedInUser(baseContext, Preferences().getRegisteredUser(baseContext)!!)
        Preferences().setLoggedInStatus(baseContext, true)
        startActivity(Intent(baseContext, MainActivity::class.java))
        finish()
    }

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences  */
    private fun cekPassword(password: String): Boolean {
        return password == Preferences().getRegisteredPass(baseContext)
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences  */
    private fun cekUser(user: String): Boolean {
        return user == Preferences().getRegisteredUser(baseContext)
    }

}
