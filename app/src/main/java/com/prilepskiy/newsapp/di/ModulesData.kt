package com.prilepskiy.newsapp.di

import com.prilepskiy.newsapp.data.apiservice.NewsApiService
import com.prilepskiy.newsapp.data.repository.NewsRepositoryImpl
import com.prilepskiy.newsapp.data.util.HeaderInterceptor
import com.prilepskiy.newsapp.domain.interactors.GetNewsUseCase
import com.prilepskiy.newsapp.domain.interactors.GetTopNewsUseCase
import com.prilepskiy.newsapp.domain.repository.NewsRepository
import com.prilepskiy.newsapp.domain.usecase.GetNewsUseCaseImpl
import com.prilepskiy.newsapp.domain.usecase.GetTopNewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitMovie() : Retrofit =
        Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .apply {
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .readTimeout(1, TimeUnit.MINUTES)
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .build()
                )
            }
            .build()

}
@Module
@InstallIn(ViewModelComponent::class)
class  NewsApiModule(){
    @Provides
    fun provideApiModule(retrofit: Retrofit): NewsApiService =retrofit.create(NewsApiService::class.java)
}

@Module
@InstallIn(ViewModelComponent::class)
class RepModule(){
    @Provides
    fun provideNewsRepositoryModule(data:NewsApiService): NewsRepository=NewsRepositoryImpl(data)
}

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule(){
    @Provides
    fun provideGetNewsUseCaseModule(data:NewsRepository): GetNewsUseCase = GetNewsUseCaseImpl(data)
    @Provides
    fun provideGetTopNewsUseCaseModule(data:NewsRepository): GetTopNewsUseCase = GetTopNewsUseCaseImpl(data)
}
