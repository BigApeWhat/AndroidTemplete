package com.example.template.repository.mapper

import com.example.template.commons.mappers.BaseMapper
import com.example.template.repository.model.entity.BeerEntity
import com.example.template.datasource.model.response.BeerResponse
import com.example.template.repository.model.entity.BeersEntity

object ResponseToEntityMapper : BaseMapper<List<BeerResponse>, BeersEntity> {
    override fun map(type: List<BeerResponse>?): BeersEntity {
        return BeersEntity(type?.map {
            BeerEntity(
                    id = it.id ?: -1,
                    name = it.name ?: "",
                    tagline = it.tagline ?: "",
                    image = it.image ?: "",
                    abv = it.abv ?: -1.0
            )
        } ?: listOf())
    }
}
