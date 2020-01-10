package com.example.template.di

import com.example.template.datasource.BeersNetworkDataSource
import com.example.template.datasource.retrofit.BeersApiService
import com.example.template.domain.BeersRepository
import com.example.template.domain.usecase.GetBeersUseCase
import com.example.template.repository.BeersRepositoryImpl
import com.example.template.ui.adapterlist.BeersAdapter
import com.example.template.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule {

    val mainModule = module {
        factory { provideBeersApiService(get()) }
        factory { BeersNetworkDataSource(beersApiService = get()) }
        factory { BeersRepositoryImpl(beersNetworkDataSource = get()) as BeersRepository }
        factory { GetBeersUseCase(beersRepository = get()) }
        viewModel { HomeViewModel(getMealsByBeersUseCase = get()) }
        factory { BeersAdapter(context = get()) }
    }

    private fun provideBeersApiService(retrofit: Retrofit): BeersApiService {
        return retrofit.create(BeersApiService::class.java)
    }
}