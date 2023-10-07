package com.example.testtaskapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.testtaskapp.App
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.ActivityMainBinding
import com.example.testtaskapp.entity.News
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels(factoryProducer = { viewModelFactory })

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (applicationContext as App).appComponent.inject(this)
        viewModel.attach()
        initObservables()
        initListeners()
    }

    private fun initListeners() {
        binding.textView.setOnClickListener {

        }
        binding.textView.text = "Gogadfkjdkjkjhkjh"
    }

    private fun initView() {

    }

    private fun initObservables() {
        viewModel.newsData.observe(this) { news ->
            notifyNews(news)
            hideLoading()
        }
        viewModel.errorData.observe(this) { message ->
            showError(message)
            hideLoading()
        }
        viewModel.loadingEvent.observe(this) {
            showLoading()
        }
    }

    private fun notifyNews(news: News) {
        Log.d("TEST_TAG", "news notify ${news.title}")
    }

    private fun showError(message: String) {

    }

    private fun showLoading() {

    }

    private fun hideLoading() {

    }


}