package com.tahirahearle.birthdaycard_assignment2

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var submitBtn: Button
    private lateinit var editTextName: EditText
    private lateinit var editTextDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        submitBtn = findViewById(R.id.submit_button)
        editTextName = findViewById(R.id.editTextName)
        editTextDate = findViewById(R.id.editTextDate)

        // adding a date pick in the edit text field for date
        editTextDate.setOnClickListener{
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    editTextDate.setText(dat)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        // when the submit button is click the data from the user will be sent to the ard activity
        submitBtn.setOnClickListener{
            startActivity(Intent(this, CardActivity::class.java)
                .putExtra("Name",editTextName.text.toString())
                .putExtra("DOB", editTextDate.text.toString())
            )
        }

    }
}
