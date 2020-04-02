package com.example.tralova

import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {
    private lateinit var mViewUser: EditText
    private lateinit var mViewPassword: EditText
    private lateinit var mViewRepassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tralova.R.layout.activity_register)

        /* Menginisialisasi variable dengan Form User, Form Password, dan Form Repassword
        dari Layout RegisterActivity */
        mViewUser = findViewById(com.example.tralova.R.id.et_emailSignup)
        mViewPassword = findViewById(com.example.tralova.R.id.et_passwordSignup)
        mViewRepassword = findViewById(com.example.tralova.R.id.et_passwordSignup2)

        /* Menjalankan Method razia() jika merasakan tombol SignUp di keyboard disentuh */
        mViewRepassword.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia()
                    return true
                }
                return false
            }
        })

        /* Menjalankan Method razia() jika merasakan tombol SignUp disentuh */
        button_signupSignup.setOnClickListener(View.OnClickListener { razia() })
    }

    /** Men-check inputan User dan Password dan Memberikan akses ke MainActivity  */
    private fun razia() {
        /* Mereset semua Error dan fokus menjadi default */
        mViewUser.error = null
        mViewPassword.error = null
        mViewRepassword.error = null
        var fokus: View? = null
        var cancel = false

        /* Mengambil text dari Form User, Password, Repassword dengan variable baru bertipe String*/
        val repassword = mViewRepassword.text.toString()
        val user = mViewUser.text.toString()
        val password = mViewPassword.text.toString()

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekUser() maka, Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(user)) {
            mViewUser.error = "This field is required"
            fokus = mViewUser
            cancel = true
        } else if (cekUser(user)) {
            mViewUser.error = "This Username is already exist"
            fokus = mViewUser
            cancel = true
        }

        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)) {
            mViewPassword.error = "This field is required"
            fokus = mViewPassword
            cancel = true
        } else if (!cekPassword(password, repassword)) {
            mViewRepassword.error = "This password is incorrect"
            fokus = mViewRepassword
            cancel = true
        }

        /** Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         * Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar  */
        if (cancel) {
            fokus!!.requestFocus()
        } else {
            Preferences().setRegisteredUser(baseContext, user)
            Preferences().setRegisteredPass(baseContext, password)
            finish()
        }
    }

    /** True jika parameter password sama dengan parameter repassword  */
    private fun cekPassword(password: String, repassword: String): Boolean {
        return password == repassword
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences  */
    private fun cekUser(user: String): Boolean {
        return user == Preferences().getRegisteredUser(baseContext)
    }
}
