package com.example.template.datasource.retrofit

import com.example.template.datasource.model.response.BeerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersApiService {

    @GET("beers?")
    suspend fun getAllBeers(
            @Query("page") page: String,
            @Query("per_page") perPage: String
    ): List<BeerResponse>?
}