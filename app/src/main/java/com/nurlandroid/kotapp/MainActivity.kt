package com.nurlandroid.kotapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val myViewModel: MyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val msg: String = getString(R.string.someText)

        btnNur.setOnClickListener({
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
            txtNur.text = msg
        })
    }
}