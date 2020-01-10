package com.example.template.domain.usecase

import com.example.template.commons.datatype.Result
import com.example.template.commons.datatype.ResultType
import com.example.template.domain.BeersRepository
import com.example.template.repository.model.entity.BeersEntity

class GetBeersUseCase(private val beersRepository: BeersRepository) {

    suspend fun execute(): Result<BeersEntity> {
        beersRepository.getAllBeers().let { beersEntity ->
            return if (beersEntity.resultType == ResultType.SUCCESS) {
                beersEntity.data.let {
                    val sortedBeers = getSortedAscendingBeers(it!!)
                    Result.success(sortedBeers)
                }
            } else {
                beersEntity
            }
        }
    }

    private fun getSortedAscendingBeers(beersEntity: BeersEntity): BeersEntity { // TODO: Implement by adding in integrators in class creation
        return BeersEntity(beersEntity.beers.sortedBy { it.abv })
    }
}