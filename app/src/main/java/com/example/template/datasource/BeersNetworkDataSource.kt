package com.example.template.datasource

import com.example.template.commons.datasource.handleNetworkException
import com.example.template.datasource.retrofit.BeersApiService
import kotlinx.coroutines.*
import java.lang.Exception

import com.example.template.commons.datatype.Result
import com.example.template.repository.mapper.ResponseToEntityMapper
import com.example.template.repository.model.entity.BeersEntity

const val MAX_RESULTS_PER_PAGE: Int = 80

class BeersNetworkDataSource(private val beersApiService: BeersApiService) {

    suspend fun getAllBeers(page: String): Result<BeersEntity> {
        var result: Result<BeersEntity> = Result.success(BeersEntity(listOf()))

        withContext(Dispatchers.IO) {
            try {
                val beers = beersApiService.getAllBeers(page, MAX_RESULTS_PER_PAGE.toString())

                withContext(Dispatchers.Main) {
                    result = Result.success(ResponseToEntityMapper.map(beers))
                }
            } catch (ex: Exception) {
                result = Result.error(handleNetworkException(ex))
            }
        }

        return result
    }
}
