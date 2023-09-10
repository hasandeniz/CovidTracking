package com.hasandeniz.covidtracking.di

import com.hasandeniz.covidtracking.data.remote.dto.CovidApi
import com.hasandeniz.covidtracking.data.repository.CovidRepositoryImpl
import com.hasandeniz.covidtracking.domain.repository.CovidRepository
import com.hasandeniz.covidtracking.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCovidApi(): CovidApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CovidApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCovidRepository(api: CovidApi): CovidRepository {
        return CovidRepositoryImpl(api = api)
    }
}