package com.example.template.domain.utils

object DomainBeersGenerator {

    fun getUnsortedBeers(): BeersEntity {
        return BeersEntity(
                listOf(
                        BeerEntity(
                                1,
                                "BeerNameOne",
                                "BeerTaglineOne",
                                "urlImageOne",
                                87.0
                        ),
                        BeerEntity(
                                2,
                                "BeerNameTwo",
                                "BeerTaglineTwo",
                                "urlImageTwo",
                                5.0
                        ),
                        BeerEntity(
                                3,
                                "BeerNameThree",
                                "BeerTaglineThree",
                                "urlImageThree",
                                2.0
                        ),
                        BeerEntity(
                                4,
                                "BeerNameFour",
                                "BeerTaglineFour",
                                "urlImageFour",
                                63.0
                        ),
                        BeerEntity(
                                5,
                                "BeerNameFive",
                                "BeerTaglineFive",
                                "urlImageFive",
                                18.0
                        )
                )
        )
    }

    fun getSortedBeers(): BeersEntity {
        return BeersEntity(
                listOf(
                        BeerEntity(
                                3,
                                "BeerNameThree",
                                "BeerTaglineThree",
                                "urlImageThree",
                                2.0
                        ),
                        BeerEntity(
                                2,
                                "BeerNameTwo",
                                "BeerTaglineTwo",
                                "urlImageTwo",
                                5.0
                        ),
                        BeerEntity(
                                5,
                                "BeerNameFive",
                                "BeerTaglineFive",
                                "urlImageFive",
                                18.0
                        ),
                        BeerEntity(
                                4,
                                "BeerNameFour",
                                "BeerTaglineFour",
                                "urlImageFour",
                                63.0
                        ),
                        BeerEntity(
                                1,
                                "BeerNameOne",
                                "BeerTaglineOne",
                                "urlImageOne",
                                87.0
                        )
                )
        )
    }
}