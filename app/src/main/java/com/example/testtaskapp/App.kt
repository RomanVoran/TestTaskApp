package com.example.testtaskapp

import android.app.Application
import com.example.testtaskapp.di.AppComponent
import com.example.testtaskapp.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()

}