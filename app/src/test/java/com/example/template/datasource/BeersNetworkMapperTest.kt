package com.example.template.datasource

import com.example.template.commons.datasource.handleNetworkException
import com.example.template.commons.exceptions.BadRequestException
import com.example.template.commons.exceptions.GenericNetworkException
import com.example.template.commons.exceptions.NetworkConnectionException
import com.example.template.repository.mapper.ResponseToEntityMapper
import com.example.template.repository.model.entity.BeersEntity
import com.example.template.datasource.utils.DataSourceBeersGenerator
import com.nhaarman.mockitokotlin2.mock
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

class BeersNetworkMapperTest {

    @Test
    fun verifyMapperFromResponseModelToApiModel() {
        val givenBeersResponse = DataSourceBeersGenerator.getBeersResponse()

        val expectedResult: BeersEntity = DataSourceBeersGenerator.getBeersApi()
        val realResult: BeersEntity = ResponseToEntityMapper.map(givenBeersResponse)

        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun verifySystemExceptionToCustomExceptionMapperWhenExceptionIsIOMustBeNetworkConnectionException() {
        verifyMapperSystemExceptionToCustomExceptionWhenSystemExceptionIs(
                givenException = IOException(),
                expectedException = NetworkConnectionException()
        )
    }

    @Test
    fun verifySystemExceptionToCustomExceptionMapperWhenExceptionIsUnknownHostMustBeNetworkConnectionException() {
        verifyMapperSystemExceptionToCustomExceptionWhenSystemExceptionIs(
                givenException = UnknownHostException(),
                expectedException = NetworkConnectionException()
        )
    }

    @Test
    fun verifySystemExceptionToCustomExceptionMapperWhenExceptionIsNotContemplatedMustBeGenericNetworkException() {
        verifyMapperSystemExceptionToCustomExceptionWhenSystemExceptionIs(
                givenException = Exception(),
                expectedException = GenericNetworkException()
        )
    }

    @Test
    fun verifySystemExceptionToCustomExceptionMapperWhenExceptionIsHttpExceptionCode400MustBeBadRequestException() {
        val httpException: HttpException = getHttpException(400)

        verifyMapperSystemExceptionToCustomExceptionWhenSystemExceptionIs(
                givenException = httpException,
                expectedException = BadRequestException()
        )
    }

    private fun getHttpException(code: Int): HttpException {
        return HttpException(
                Response.error<Exception>(
                        code,
                        ResponseBody.create(null, "")
                )
        )
    }

    @Test
    fun verifySystemExceptionToCustomExceptionMapperWhenExceptionIsDifferent400MustBeGenericNetworkException() {
        val httpException: HttpException = mock()

        verifyMapperSystemExceptionToCustomExceptionWhenSystemExceptionIs(
                givenException = httpException,
                expectedException = GenericNetworkException()
        )
    }

    private fun verifyMapperSystemExceptionToCustomExceptionWhenSystemExceptionIs(
            givenException: Exception,
            expectedException: Exception
    ) {
        val expectedResult: Exception = expectedException
        val realResult: Exception = handleNetworkException(givenException)

        Assert.assertEquals(expectedResult::class, realResult::class)
    }
}