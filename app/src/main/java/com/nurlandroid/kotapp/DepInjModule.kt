package com.nurlandroid.kotapp

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val diModule = module {
    viewModel { MyViewModel() }
}