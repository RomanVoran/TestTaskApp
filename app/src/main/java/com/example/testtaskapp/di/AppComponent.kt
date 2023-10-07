package com.example.testtaskapp.di

import com.example.testtaskapp.data.NewsRepository
import com.example.testtaskapp.data.NewsRepositoryImpl
import com.example.testtaskapp.presentation.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

}

@Module
class AppModule {

    @Provides
    fun provideNewsSource(): NewsRepository {
        return NewsRepositoryImpl()
    }

}

