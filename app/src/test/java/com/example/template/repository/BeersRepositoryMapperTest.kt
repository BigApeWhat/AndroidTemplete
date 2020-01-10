package com.example.template.repository

import com.example.template.repository.mapper.ApiToEntityMapper
import com.example.template.repository.utils.RepositoryBeersGenerator
import org.junit.Assert
import org.junit.Test

class BeersRepositoryMapperTest {

    @Test
    fun verifyMapperFromApiModelToEntityModelMustReturnSameValues() {
        val givenBeersApi = RepositoryBeersGenerator.getBeersApi()

        val expectedResult: BeersEntity = RepositoryBeersGenerator.getBeersEntity()
        val realResult: BeersEntity = ApiToEntityMapper.map(givenBeersApi)


        expectedResult.beers.forEachIndexed { index, expectedBeer ->
            Assert.assertEquals(expectedBeer.id, realResult.beers[index].id)
            Assert.assertEquals(expectedBeer.name, realResult.beers[index].name)
            Assert.assertEquals(expectedBeer.tagline, realResult.beers[index].tagline)
            Assert.assertEquals(expectedBeer.image, realResult.beers[index].image)
            Assert.assertEquals(expectedBeer.abv, realResult.beers[index].abv, 0.0)
        }

        Assert.assertEquals(expectedResult::class.java, realResult::class.java)
    }

    @Test
    fun verifyMapperFromApiModelToEntityModelMustReturnSameObjectType() {
        val givenBeersApi = RepositoryBeersGenerator.getBeersApi()

        val expectedResult: BeersEntity = RepositoryBeersGenerator.getBeersEntity()
        val realResult: BeersEntity = ApiToEntityMapper.map(givenBeersApi)

        Assert.assertEquals(expectedResult::class.java, realResult::class.java)
    }


    @Test
    fun verifyMapperFromApiModelToEntityModelMustReturnSameObjectsType() {
        val givenBeersApi = RepositoryBeersGenerator.getBeersApi()

        val expectedResult: BeersEntity = RepositoryBeersGenerator.getBeersEntity()
        val realResult: BeersEntity = ApiToEntityMapper.map(givenBeersApi)

        expectedResult.beers.forEachIndexed { index, expectedBeer ->
            Assert.assertEquals(expectedBeer::class.java, realResult.beers[index]::class.java)
        }
    }
}