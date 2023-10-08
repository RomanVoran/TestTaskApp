package com.example.testtaskapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskapp.data.NewsRepository
import com.example.testtaskapp.entity.News
import com.example.testtaskapp.entity.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    var filterQuery: String = ""

    val searchNewsData: LiveData<List<News>>
        get() = _searchNewsData
    private val _searchNewsData = MutableLiveData<List<News>>()
    private val rawSearchNewsList = mutableListOf<News>()

    val mainNewsData: LiveData<List<News>>
        get() = _mainNewsData
    private val _mainNewsData = MutableLiveData<List<News>>()

    val errorData: LiveData<String>
        get() = _errorData
    private val _errorData = MutableLiveData<String>()

    val loadingEvent: LiveData<Boolean>
        get() = _loadingEvent
    private val _loadingEvent = MutableLiveData<Boolean>()

    fun fetchNews() {
        val newsList = mutableListOf<News>()
        newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { response ->
                when (response) {
                    is Response.Loading -> onLoading(response.isLoading)
                    is Response.Failure -> onError(response.message)
                    is Response.Success -> {
                        newsList.add(response.news)
                        _mainNewsData.postValue(newsList)
                    }
                }
            }
            .doOnError { throwable ->
                onError(throwable.message)
            }.doOnComplete {
                // end on translation
            }.subscribe()
    }

    fun fetchSearchNews() {
        rawSearchNewsList.clear()
        updateSearchData()
        newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { response ->
                when (response) {
                    is Response.Loading -> onLoading(response.isLoading)
                    is Response.Failure -> onError(response.message)
                    is Response.Success -> {
                        rawSearchNewsList.add(response.news)
                        updateSearchData()
                    }
                }
            }
            .doOnError { throwable ->
                onError(throwable.message)
            }.doOnComplete {
                // end on translation
            }.subscribe()
    }


    fun updateSearchData(
        rawNewsList: List<News> = rawSearchNewsList,
        query: String = filterQuery
    ) {
        filterQuery = query
        val filteredNewsList = filterNewsList(rawNewsList, query)
        _searchNewsData.postValue(filteredNewsList)
    }

    fun stopSearching() {
        filterQuery = ""
    }

    private fun onError(message: String?) {
        _errorData.postValue(message ?: "Something went wrong!")
    }

    private fun onLoading(isLoading: Boolean) {
        _loadingEvent.postValue(isLoading)
    }

    private fun filterNewsList(rawList: List<News>, query: String): List<News> =
        rawList.filter { news ->
            news.title.lowercase().contains(query.lowercase()) ||
                    query.lowercase().contains(news.title.lowercase())
        }

}