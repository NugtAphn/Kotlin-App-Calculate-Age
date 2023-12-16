package com.nugtaphn.kolinappcalculateage

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate = findViewById<Button>(R.id.btnSelectDate)

        btnSelectDate.setOnClickListener {
            clickSelectDate()
        }
    }

    fun clickSelectDate(){
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                Toast.makeText(this,"$day/$month/$year",Toast.LENGTH_LONG).show()
            },year,month,day).show()
    }
}