package com.example.testtaskapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.SearchView.OnQueryTextListener
import android.widget.SearchView.VISIBLE
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtaskapp.App
import com.example.testtaskapp.R
import com.example.testtaskapp.databinding.ActivityMainBinding
import com.example.testtaskapp.entity.News
import com.example.testtaskapp.presentation.adapters.MainListAdapter
import com.google.android.material.snackbar.Snackbar
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
        initObservers()
        initListeners()
    }

    private fun initView() = with(binding) {
        mainListView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(false)
        }

        searchListView.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(false)
        }

    }

    private fun initListeners() = with(binding) {
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.updateSearchData(query = query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchData(query = newText ?: "")
                return false
            }

        })
        searchView.setOnSearchClickListener {
            showSearchList()
        }
        searchView.setOnCloseListener {
            hideSearchList()
            false
        }
    }

    private fun initObservers() {
        viewModel.mainNewsData.observe(this) { newsList ->
            mainAdapter.submitList(newsList.toList())
        }
        viewModel.searchNewsData.observe(this) { newsList ->
            searchAdapter.submitList(newsList.toList())
        }
        viewModel.errorData.observe(this) { message ->
            showError(message)
        }
        viewModel.loadingEvent.observe(this) { isLoading ->
            handleLoading(isLoading)
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, "Some error: $message", Snackbar.LENGTH_LONG).show()
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {

        } else {

        }
    }

    private fun showSearchList() {
        binding.searchListView.visibility = VISIBLE
        viewModel.fetchSearchNews()
    }

    private fun hideSearchList() {
        binding.searchListView.visibility = GONE
        viewModel.stopSearching()
    }

}