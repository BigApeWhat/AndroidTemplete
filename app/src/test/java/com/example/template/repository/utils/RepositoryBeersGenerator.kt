package com.example.template.repository.utils

import com.example.template.repository.model.entity.BeerEntity

object RepositoryBeersGenerator {

    fun getBeersApi(): com.example.template.repository.model.entity.BeersEntity {
        return com.example.template.repository.model.entity.BeersEntity(
                listOf(
                        BeerEntity(
                                id = 1,
                                name = "",
                                tagline = "",
                                image = "",
                                abv = -1.0
                        ),
                        BeerEntity(
                                id = 2,
                                name = "Beer two",
                                tagline = "Tagline two",
                                image = "Image two",
                                abv = 2.0
                        ),
                        BeerEntity(
                                id = 3,
                                name = "Beer three",
                                tagline = "Tagline three",
                                image = "Image three",
                                abv = 2.0
                        ),
                        BeerEntity(
                                id = 4,
                                name = "Beer four",
                                tagline = "Tagline four",
                                image = "Image four",
                                abv = -1.0
                        ),
                        BeerEntity(
                                id = 5,
                                name = null,
                                tagline = null,
                                image = null,
                                abv = null
                        )
                )
        )
    }

    fun getBeersEntity(): BeersEntity {
        return BeersEntity(
                listOf(
                        BeerEntity(
                                id = 1,
                                name = "",
                                tagline = "",
                                image = "",
                                abv = -1.0
                        ),
                        BeerEntity(
                                id = 2,
                                name = "Beer two",
                                tagline = "Tagline two",
                                image = "Image two",
                                abv = 2.0
                        ),
                        BeerEntity(
                                id = 3,
                                name = "Beer three",
                                tagline = "Tagline three",
                                image = "Image three",
                                abv = 2.0
                        ),
                        BeerEntity(
                                id = 4,
                                name = "Beer four",
                                tagline = "Tagline four",
                                image = "Image four",
                                abv = -1.0
                        ),
                        BeerEntity(
                                id = 5,
                                name = "",
                                tagline = "",
                                image = "",
                                abv = -1.0
                        )
                )
        )
    }
}