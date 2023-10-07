package com.example.testtaskapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskapp.data.NewsRepository
import com.example.testtaskapp.entity.News
import com.example.testtaskapp.entity.Response
import com.example.testtaskapp.entity.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val newsData: LiveData<News>
        get() = _newsData
    private val _newsData = MutableLiveData<News>()

    val errorData: LiveData<String>
        get() = _errorData
    private val _errorData = MutableLiveData<String>()

    val loadingEvent: LiveData<Any?>
        get() = _loadingEvent
    private val _loadingEvent = SingleLiveEvent()

    val newsList: List<News>
        get() = _newsList
    private val _newsList = mutableListOf<News>()

    fun attach() {
        newsRepository.getNews()
        newsRepository.newsSource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { response ->
                when (response) {
                    is Response.Success -> onSuccess(response.news)
                    is Response.Failure -> onError(response.message)
                    Response.Loading -> onLoading()
                }
            }
            .doOnError { throwable ->
                onError(throwable.message)
            }.doOnComplete {
                // end on translation
            }.subscribe()
    }

    private fun onError(message: String?) {
        _errorData.postValue(message ?: "Something went wrong!")
    }

    private fun onSuccess(news: News) {
        _newsList.add(news)
        _newsData.postValue(news)
    }

    private fun onLoading() {
        _loadingEvent.postValue(null)
    }

}