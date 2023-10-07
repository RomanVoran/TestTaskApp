package com.example.testtaskapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskapp.data.NewsRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val newsRepo: NewsRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM = MainViewModel(newsRepo) as VM
}