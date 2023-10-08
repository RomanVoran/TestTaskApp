package com.example.testtaskapp.data

import com.example.testtaskapp.entity.Response
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject


interface NewsRepository {

    val newsSource: BehaviorSubject<Response>

    fun getNews(): Subject<Response>

}