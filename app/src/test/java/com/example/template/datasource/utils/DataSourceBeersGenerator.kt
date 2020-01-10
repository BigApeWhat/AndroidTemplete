package com.example.template.datasource.utils

import com.example.template.repository.model.entity.BeerEntity
import com.example.template.repository.model.entity.BeersEntity
import com.example.template.datasource.model.response.BeerResponse

object DataSourceBeersGenerator {

    fun getBeersResponse(): List<BeerResponse> {
        return listOf(
                BeerResponse(
                        id = 1,
                        name = "BeerNameOne",
                        tagline = "BeerTaglineOne",
                        image = "urlImageOne",
                        abv = 87.0
                ),
                BeerResponse(
                        id = 2,
                        name = "BeerNameTwo",
                        tagline = "BeerTaglineTwo",
                        image = "urlImageTwo",
                        abv = 5.0
                ),
                BeerResponse(
                        id = 3,
                        name = "BeerNameThree",
                        tagline = "BeerTaglineThree",
                        image = "urlImageThree",
                        abv = 2.0
                ),
                BeerResponse(
                        id = 4,
                        name = "BeerNameFour",
                        tagline = "BeerTaglineFour",
                        image = "urlImageFour",
                        abv = 63.0
                ),
                BeerResponse(
                        id = null,
                        name = null,
                        tagline = null,
                        image = null,
                        abv = null
                )
        )
    }

    fun getBeersApi(): BeersEntity {
        return BeersEntity(
                listOf(
                        BeerEntity(
                                id = 1,
                                name = "BeerNameOne",
                                tagline = "BeerTaglineOne",
                                image = "urlImageOne",
                                abv = 87.0
                        ),
                        BeerEntity(
                                id = 2,
                                name = "BeerNameTwo",
                                tagline = "BeerTaglineTwo",
                                image = "urlImageTwo",
                                abv = 5.0
                        ),
                        BeerEntity(
                                id = 3,
                                name = "BeerNameThree",
                                tagline = "BeerTaglineThree",
                                image = "urlImageThree",
                                abv = 2.0
                        ),
                        BeerEntity(
                                id = 4,
                                name = "BeerNameFour",
                                tagline = "BeerTaglineFour",
                                image = "urlImageFour",
                                abv = 63.0
                        ),
                        BeerEntity(
                                id = -1,
                                name = "",
                                tagline = "",
                                image = "",
                                abv = -1.0
                        )
                )
        )
    }
}