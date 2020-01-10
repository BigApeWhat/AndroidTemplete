package com.example.template.repository.model.entity

/**
 *  This data model is to avoid the Repository layer knows the DataSource layer data model to
 *  fetch data from Api.
 * */
data class BeersEntity(
        val beers: List<BeerEntity>
)