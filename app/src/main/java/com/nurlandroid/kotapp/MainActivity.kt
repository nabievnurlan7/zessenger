package com.nurlandroid.kotapp

import android.os.Bundle
import android.widget.Toast
import com.nurlandroid.kotapp.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.btnNur
import kotlinx.android.synthetic.main.activity_main.txtNur
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity() {
    private val myViewModel: MyViewModel by viewModel()
    override var layout = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val msg: String = getString(R.string.someText)

        btnNur.setOnClickListener({
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
            txtNur.text = msg
        })

        val adapter = MyAdapter(
            listener = { item ->
                Timber.d(item.name)
            }
        )
    }
}