package com.example.testtaskapp.data

import com.example.testtaskapp.entity.Response
import io.reactivex.subjects.BehaviorSubject


interface NewsRepository {

    val newsSource: BehaviorSubject<Response>

    fun getNews()

}