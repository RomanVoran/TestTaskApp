package com.example.testtaskapp.data

import com.example.testtaskapp.entity.Response
import com.example.testtaskapp.utils.generateDelay
import com.example.testtaskapp.utils.getNewsList
import com.example.testtaskapp.utils.isErrorNow
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.Executors
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    override val newsSource: BehaviorSubject<Response> = BehaviorSubject.create()

    override fun getNews(): Subject<Response> {
        val subject = BehaviorSubject.create<Response>()
        Executors.newCachedThreadPool().execute {
            getNewsList().forEach { news ->
                subject.onNext(Response.Loading(true))
                Thread.sleep(generateDelay())
                if (isErrorNow()) {
                    subject.onNext(Response.Failure(message = "Something went wrong!"))
                } else {
                    subject.onNext(Response.Success(news))
                }
                subject.onNext(Response.Loading(false))
            }
            subject.onComplete()
        }
        return subject
    }
}