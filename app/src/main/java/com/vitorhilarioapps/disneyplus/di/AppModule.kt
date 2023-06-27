package com.vitorhilarioapps.disneyplus.di

import com.vitorhilarioapps.disneyplus.data.api.MovieApi
import com.vitorhilarioapps.disneyplus.data.api.MovieApiImpl
import com.vitorhilarioapps.disneyplus.data.api.MovieService
import com.vitorhilarioapps.disneyplus.data.repository.MovieRepository
import com.vitorhilarioapps.disneyplus.data.repository.MovieRepositoryImpl
import com.vitorhilarioapps.disneyplus.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieService::class.java)
    }

    single<MovieApi> {
        MovieApiImpl(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    viewModel<MainViewModel> {
        MainViewModel(get())
    }
}