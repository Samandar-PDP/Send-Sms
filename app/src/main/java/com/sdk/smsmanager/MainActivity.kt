package com.sdk.smsmanager

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editMessage: EditText = findViewById(R.id.editMessage)
        val editPhone: EditText = findViewById(R.id.editNumber)
        val btnSend: Button = findViewById(R.id.btnSend)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 100)
        } else {
            btnSend.setOnClickListener {
                val phoneNumber = editPhone.text.toString().trim()
                val message = editMessage.text.toString().trim()
                val smsManager = SmsManager.getDefault()
                try {
                    smsManager.sendTextMessage("+998$phoneNumber", null, message, null, null)
                    Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, e.stackTraceToString(), Toast.LENGTH_SHORT).show()
                    Log.d("@@@", e.stackTraceToString())
                }

            }
        }
    }
}