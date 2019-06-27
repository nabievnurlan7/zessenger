package com.nurlandroid.kotapp.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by nurik on 28.06.2019.
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract var layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }
}