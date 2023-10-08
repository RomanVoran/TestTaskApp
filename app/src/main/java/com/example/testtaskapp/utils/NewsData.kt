package com.example.testtaskapp.utils

import com.example.testtaskapp.entity.News

fun getNewsList(): List<News> = listOf(
    News("Заголовок 1", "Новость1"),
    News("Заголовок 2", "Новость2"),
    News("Заголовок 3", "Новость3"),
    News("Инфляция", "Caption3"),
    News("Дефляция", "Caption3"),
    News("Победа в выборах", "Caption3"),
    News("Победитель лотереи", "Caption3"),
    News("победитель получил приз", "Caption3"),
    News("Призёр эстафеты", "Caption3"),
    News("Призы для детей", "Caption3"),
    News("Президент издал указ", "Caption3"),
    News("Верховный президиум постановил", "Caption3"),
    News("Осенний листопад", "Caption3"),
    News("Учебный курс осени", "Caption3"),
    News("Осень и болезни, как лечиться", "Caption3"),
    News("Лечение остеопароза", "Caption3"),
    News("Лечебные травы", "Caption3"),
    News("Ночные гонки закончились аварией", "Caption3"),
    News("Победитель продал машину из-за инфляции", "Caption3"),
)


fun generateDelay(): Long = when ((0..100).random()) {
    in 0..20 -> 0
    else -> (0..1000).random() + 800
}.toLong()

fun isErrorNow(): Boolean = (0..100).random() < 2
