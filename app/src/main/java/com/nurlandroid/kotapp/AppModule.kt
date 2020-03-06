package com.nurlandroid.kotapp

import com.nurlandroid.kotapp.common.CustomFragmentFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diModule = module {
    single { CustomFragmentFactory() }
    viewModel { MyViewModel() }
}