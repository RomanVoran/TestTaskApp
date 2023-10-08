package com.example.testtaskapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtaskapp.App
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.ActivityMainBinding
import com.example.testtaskapp.entity.News
import com.example.testtaskapp.presentation.adapters.MainListAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels(factoryProducer = { viewModelFactory })

    private lateinit var binding: ActivityMainBinding

    private val mainAdapter = MainListAdapter()
    private val searchAdapter = MainListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (applicationContext as App).appComponent.inject(this)
        viewModel.fetchNews()
        initView()
        initObservables()
        initListeners()
    }

    private fun initView() = with(binding) {
        mainListView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(false)
        }

        searchListView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(false)
        }

        mainAdapter.submitList(viewModel.newsList)
        searchAdapter.submitList(viewModel.newsList)

    }

    private fun initListeners() = with(binding) {
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })
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
        Log.d("TEST_TAG", "new news: ${news.title}, list size = ${viewModel.newsList.size}")
//        mainAdapter.submitList(null)
        mainAdapter.submitList(viewModel.newsList.toList())
    }

    private fun showError(message: String) {

    }

    private fun showLoading() {

    }

    private fun hideLoading() {

    }


}