package com.example.tralova.ui.slideshow

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.tralova.R
import kotlinx.android.synthetic.main.activity_add_remind.*
import java.text.DateFormatSymbols
import java.util.*

class AddRemindActivity : AppCompatActivity() {
    var remindData = arrayListOf<String>()
    val now = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remind)

        val repeatAdapter = ArrayAdapter.createFromResource(this, R.array.repeat_condition,
            android.R.layout.simple_spinner_dropdown_item)
        findViewById<Spinner>(R.id.spRepeat).adapter = repeatAdapter
    }

    fun setDate(view: View) {
        val tahun = now.get(Calendar.YEAR)
        val bulan = now.get(Calendar.MONTH)
        val hari = now.get(Calendar.DAY_OF_MONTH)
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                datePicker, year, monthOfYear, dayOfMonth ->
            tvDate.setText("$dayOfMonth - " + DateFormatSymbols.getInstance().months[monthOfYear] +  " - $year")},
            tahun, bulan, hari ).show()
    }

    fun setTime(view: View) {
        val jam = now.get(Calendar.HOUR)
        val menit = now.get(Calendar.MINUTE)
        val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener {
                TimePicker, hour, minutes ->
            tvTime.setText("$hour:$minutes")
        }, jam, menit, true).show()
    }
    fun cancelTodo(view: View){
        finish()
    }

    fun saveTodo(view: View){
            remindData.add(remind.text.toString())
            remindData.add(summary.text.toString())
            remindData.add(tvDate.text.toString())
            remindData.add(tvTime.text.toString())
//        val nameFile = contactData[0]+"_"+contactData[1]
            // Save image to app data
//        saveImage(this.applicationContext,convertToBitmap(this.applicationContext,
//            image2?: Uri.parse("")),nameFile,"png")
//        contactData.add(nameFile)
        finish()
    }

    override fun finish() {
        val returnIntent = Intent()
        if (remindData.size == 4)
            returnIntent.putExtra("remindData", remindData)
        setResult(Activity.RESULT_OK, returnIntent)
        super.finish()
    }
}
