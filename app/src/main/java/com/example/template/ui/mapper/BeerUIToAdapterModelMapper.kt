package com.example.template.ui.mapper

import com.example.template.R
import com.example.template.commons.mappers.BaseMapper
import com.example.template.ui.adapterlist.model.BeerAdapterModel
import com.example.template.viewmodel.model.AbvColorType
import com.example.template.viewmodel.model.BeerUI

object BeerUIToAdapterModelMapper : BaseMapper<List<BeerUI>, List<BeerAdapterModel>> {

    override fun map(type: List<BeerUI>?): List<BeerAdapterModel> {
        return type?.map {
            BeerAdapterModel(
                    id = it.id,
                    name = it.name,
                    tagline = it.tagline,
                    image = it.image,
                    abv = it.abv,
                    abvColor = getColor(it.abvColorType)
            )
        } ?: listOf()
    }

    private fun getColor(abvType: AbvColorType): Int {
        return when (abvType) {
            AbvColorType.GREEN -> R.color.green
            AbvColorType.ORANGE -> R.color.orange
            else -> R.color.red
        }
    }
}
