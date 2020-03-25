package com.example.components.views

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.components.R
import kotlinx.android.synthetic.main.activity_date.*
import java.text.SimpleDateFormat
import java.util.*

class DateActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePicker.OnTimeChangedListener {

    private val mSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        init()
    }

    private fun init() {
        buttonDatePicker.setOnClickListener { datePicker() }
        buttonSetTime.setOnClickListener { setTime() }
        buttonGetTime.setOnClickListener { getTime() }
        timePicker.setOnTimeChangedListener(this)
    }

    private fun datePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, this, year, month, day).show()
    }

    private fun setTime(){
        if (Build.VERSION.SDK_INT >=23) {
            timePicker.hour = 20
            timePicker.minute = 30
        }else {
            timePicker.currentHour = 20
            timePicker.currentMinute = 30
        }
    }
    private fun getTime(){
        if (Build.VERSION.SDK_INT >=23) {
            val value = "${timePicker.hour} ${timePicker.minute}"
            Toast.makeText(this,value, Toast.LENGTH_LONG).show()
        }else {
            val value = "${timePicker.currentHour}:${timePicker.currentHour}"
            Toast.makeText(this,value, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val value = mSimpleDateFormat.format(calendar.time)
        buttonDatePicker.text = value
    }

    companion object {
        const val REQ_CODE = 100
        fun newIntent(context: Context) = Intent(context, DateActivity::class.java)
    }

    override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Toast.makeText(this,"$hourOfDay:$minute", Toast.LENGTH_LONG).show()
    }
}
