package com.example.template.repository

import com.example.template.commons.datatype.Result
import com.example.template.domain.BeersRepository
import com.example.template.datasource.BeersNetworkDataSource
import com.example.template.datasource.MAX_RESULTS_PER_PAGE
import com.example.template.repository.model.entity.BeerEntity
import com.example.template.repository.model.entity.BeersEntity

class BeersRepositoryImpl(
        private val beersNetworkDataSource: BeersNetworkDataSource
) : BeersRepository {

    private val beers = mutableListOf<BeerEntity>()

    override suspend fun getAllBeers(): Result<BeersEntity> {
        var page = -1
        var result: Result<BeersEntity>

        do {
            page = getPageToCheckBeers(page)

            beersNetworkDataSource.getAllBeers(page.toString()).let { resultListBeerResponse ->
                addAllBeersUntilLastPage(resultListBeerResponse)
                result = resultListBeerResponse
            }
        } while (result.resultType != Result.error<Error>().resultType && beers.size == 0)

        return result
    }

    private fun getPageToCheckBeers(currentPage: Int): Int {
        var page: Int = currentPage

        if (hasBeers()) {
            if (isNecessaryFetchMoreBeers(currentPage)) page++ else page = -1
        } else {
            page = 1
        }

        return page
    }

    private fun hasBeers() = beers.size > 0

    private fun isNecessaryFetchMoreBeers(page: Int): Boolean {
        return (beers.size / page) == MAX_RESULTS_PER_PAGE
    }

    private fun addAllBeersUntilLastPage(beersApiResult: Result<BeersEntity>) {
        (beersApiResult.data).let { beersEntity ->
            beersEntity?.beers?.toCollection(beers)
        }
    }

}
