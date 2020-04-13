package com.nurlandroid.kotapp.di

import com.nurlandroid.kotapp.common.CustomFragmentFactory
import com.nurlandroid.kotapp.coroutine.DummyInteractor
import com.nurlandroid.kotapp.coroutine.DummyRepository
import com.nurlandroid.kotapp.coroutine.NetworkApi
import com.nurlandroid.kotapp.feature.MyViewModel
import com.nurlandroid.kotapp.networkbound.PostDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val diModule = module {
    single { CustomFragmentFactory() }
    viewModel { MyViewModel(get()) }

    single { DummyRepository(get()) }
    single { DummyInteractor(get()) }

    single<NetworkApi> {
        Retrofit
            .Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NetworkApi::class.java)
    }

    single { PostDatabase.getDatabase(get()) }
}