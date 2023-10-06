package com.example.testtaskapp.entity

import java.lang.Exception

sealed interface Response {
    class Success(val news: List<News>) : Response
    object Loading : Response
    class Failure(val exception: Exception) : Response
}