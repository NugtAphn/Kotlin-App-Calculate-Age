package com.nugtaphn.kolinappcalculateage

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    var txtSelectedDate : TextView? = null
    var txtMinutes : TextView? = null
    var txtHours : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate = findViewById<Button>(R.id.btnSelectDate)
        txtSelectedDate = findViewById(R.id.txtSelectedDate)
        txtMinutes = findViewById(R.id.txtMinutes)
        txtHours = findViewById(R.id.txtHours)

        btnSelectDate.setOnClickListener {
            clickSelectDate()
        }
    }

    fun clickSelectDate(){
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val dpd = DatePickerDialog(this,{ _, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this,"$selectedDay/${selectedMonth+1}/$selectedYear",Toast.LENGTH_LONG).show()
            val selectedDate  = "$selectedDay/${selectedMonth+1}/$selectedYear"
            txtSelectedDate?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            theDate?.let {
                val selectedDateInMinutes = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 60000
                    val differentDateInMinutes = currentDateInMinutes - selectedDateInMinutes
                    txtMinutes?.text = differentDateInMinutes.toString()
                }
            }
        },year,month,day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}