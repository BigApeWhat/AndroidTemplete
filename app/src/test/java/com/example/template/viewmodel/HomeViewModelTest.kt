package com.example.template.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.template.commons.datatype.Result
import com.example.template.domain.usecase.GetBeersUseCase
import com.example.template.domain.utils.DomainBeersGenerator
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception

private const val EXPECTED_IS_LOADING_TRUE = true
private const val EXPECTED_IS_LOADING_FALSE = false

/**
 *  https://codelabs.developers.google.com/codelabs/android-testing/#7
 * */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mainCoroutineRule = MainCoroutineRule()

    private var mockGetBeersUseCase: GetBeersUseCase = mock()
    private lateinit var viewModel: HomeViewModel

    @Test
    fun verifyBeersLiveDataIsNotEmptyWhenResultIsSuccess() {
        mainCoroutineRule.runBlockingTest {
            givenSuccessResult()
            whenViewModelHandleLoadBeers()
            thenAssertLiveData(beersExpected = true)
        }
    }

    @Test
    fun verifyAreEmptyBeersLiveDataIsTrueWhenResultIsSuccess() {
        mainCoroutineRule.runBlockingTest {
            givenSuccessResult(areNecessaryEmptyBeers = true)
            whenViewModelHandleLoadBeers()
            thenAssertLiveData(areEmptyBeersExpected = true)
        }
    }

    @Test
    fun verifyIsErrorLiveDataIsTrueWhenResultIsError() {
        mainCoroutineRule.runBlockingTest {
            givenErrorResult()

            mainCoroutineRule.pauseDispatcher()
            whenViewModelHandleLoadBeers()
            mainCoroutineRule.resumeDispatcher()

            thenAssertLiveData(isErrorExpected = true)
        }
    }

    @Test
    fun verifyIsLoadingLiveDataWhenResultIsSuccess() {
        mainCoroutineRule.runBlockingTest {
            givenSuccessResult()
            assertIsLoadingLiveDataTrueWhenViewModelFetchData()
            assertIsLoadingLiveData(EXPECTED_IS_LOADING_FALSE)
        }
    }

    private fun givenSuccessResult(areNecessaryEmptyBeers: Boolean = false) {
        runBlockingTest {
            val result: Result<BeersEntity> = if (areNecessaryEmptyBeers) {
                Result.success(BeersEntity(listOf()))
            } else {
                Result.success(DomainBeersGenerator.getSortedBeers())
            }

            given(mockGetBeersUseCase.execute()).willReturn(result)
        }
    }

    private fun whenViewModelHandleLoadBeers() {
        viewModel = HomeViewModel(mockGetBeersUseCase)
    }

    private fun thenAssertLiveData(
            beersExpected: Boolean? = null,
            isErrorExpected: Boolean? = null,
            areEmptyBeersExpected: Boolean? = null
    ) {
        Assert.assertEquals(beersExpected, viewModel.beers.value?.isNotEmpty())
        Assert.assertEquals(isErrorExpected, viewModel.isError.value)
        Assert.assertEquals(areEmptyBeersExpected, viewModel.areEmptyBeers.value)
        Assert.assertEquals(EXPECTED_IS_LOADING_FALSE, viewModel.isLoading.value)
    }

    @Test
    fun verifyIsLoadingLiveDataWhenResultIsError() {
        mainCoroutineRule.runBlockingTest {
            givenErrorResult()
            assertIsLoadingLiveDataTrueWhenViewModelFetchData()
            assertIsLoadingLiveData(EXPECTED_IS_LOADING_FALSE)
        }
    }

    private fun givenErrorResult() {
        runBlockingTest {
            val result: Result<BeersEntity> = Result.error(Exception())
            given(mockGetBeersUseCase.execute()).willReturn(result)
        }
    }

    private fun assertIsLoadingLiveDataTrueWhenViewModelFetchData() {
        mainCoroutineRule.pauseDispatcher()
        whenViewModelHandleLoadBeers()
        assertIsLoadingLiveData(EXPECTED_IS_LOADING_TRUE)
        mainCoroutineRule.resumeDispatcher()
    }

    private fun assertIsLoadingLiveData(expectedResult: Boolean) {
        val realResult = viewModel.isLoading.value
        Assert.assertEquals(expectedResult, realResult)
    }
}
