package com.example.testtaskapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}