package com.example.testtaskapp.data

import com.example.testtaskapp.entity.Response
import com.example.testtaskapp.entity.generateDelay
import com.example.testtaskapp.entity.getNewsList
import com.example.testtaskapp.entity.isErrorNow
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.Executors
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    override val newsSource: BehaviorSubject<Response> = BehaviorSubject.create()

    // Must run on background thread
    // Method can call ANR exception
    override fun getNews() {
        Executors.newCachedThreadPool().execute {
            getNewsList().forEach { news ->
                newsSource.onNext(Response.Loading)
                Thread.sleep(generateDelay())
                if (isErrorNow()) {
                    newsSource.onNext(Response.Failure(message = "Something went wrong!"))
                } else {
                    newsSource.onNext(Response.Success(news))
                }
            }
            newsSource.onComplete()
        }
    }
}