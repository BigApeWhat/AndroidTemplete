package com.example.template.viewmodel.mapper

import com.example.template.commons.mappers.BaseMapper
import com.example.template.viewmodel.model.AbvColorType
import com.example.template.domain.model.AbvRangeType
import com.example.template.repository.model.entity.BeerEntity
import com.example.template.viewmodel.model.BeerUI

object EntityToUIMapper : BaseMapper<List<BeerEntity>, List<BeerUI>> {
    override fun map(type: List<BeerEntity>?): List<BeerUI> {
        return type?.map {
            BeerUI(
                    id = it.id,
                    name = it.name,
                    tagline = it.tagline,
                    image = it.image,
                    abv = it.abv,
                    abvColorType = mapAbvType(getAbvRange(it.abv))
            )
        } ?: listOf()
    }

    fun getAbvRange(abv: Double): AbvRangeType {
        return when {
            abv < 5 -> AbvRangeType.LOW
            abv >= 5 && abv < 8 -> AbvRangeType.NORMAL
            else -> AbvRangeType.HIGH
        }
    }

    private fun mapAbvType(abvRangeType: AbvRangeType): AbvColorType {
        return when (abvRangeType) {
            AbvRangeType.LOW -> AbvColorType.GREEN
            AbvRangeType.NORMAL -> AbvColorType.ORANGE
            AbvRangeType.HIGH -> AbvColorType.RED
        }
    }
}
