package com.nurlandroid.kotapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val msg : String = getString(R.string.someText)


        btnNur.setOnClickListener(View.OnClickListener
        {
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
            txtNur.setText(msg)
        }
        )
    }
}
