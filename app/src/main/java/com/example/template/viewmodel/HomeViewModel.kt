package com.example.template.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.commons.datatype.Result
import com.example.template.commons.datatype.ResultType
import com.example.template.domain.usecase.GetBeersUseCase
import com.example.template.repository.model.entity.BeersEntity
import com.example.template.viewmodel.mapper.EntityToUIMapper
import com.example.template.viewmodel.model.BeerUI
import kotlinx.coroutines.*

class HomeViewModel(private val getMealsByBeersUseCase: GetBeersUseCase) : ViewModel() {

    private val beersLiveData: MutableLiveData<List<BeerUI>> = MutableLiveData()
    private val areEmptyBeersLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val isErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val beers: LiveData<List<BeerUI>>
        get() = beersLiveData

    val areEmptyBeers: LiveData<Boolean>
        get() = areEmptyBeersLiveData

    val isError: LiveData<Boolean>
        get() = isErrorLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    init {
        handleBeersLoad()
    }

    fun handleBeersLoad() {
        isLoadingLiveData(true)
        viewModelScope.launch {
            updateAppropriateLiveData(getMealsByBeersUseCase.execute())
        }
    }

    private fun updateAppropriateLiveData(result: Result<BeersEntity>) {
        if (isResultSuccess(result.resultType)) {
            onResultSuccess(result.data)
        } else {
            onResultError() //TODO: but what error, how to handle differently for each one?
        }
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultSuccess(beersEntity: BeersEntity?) {
        val beers = EntityToUIMapper.map(beersEntity?.beers)

        if (beers.isEmpty()) {
            areEmptyBeersLiveData.postValue(true)
        } else {
            beersLiveData.postValue(beers)
        }

        isLoadingLiveData(false)
    }

    /**
     *  The delay is to avoid the screen flash between the transition from AlertDialog to ProgressBar
     * */
    private fun onResultError() {
        viewModelScope.launch {
            delay(300)
            isLoadingLiveData(false)
        }.invokeOnCompletion {
            isErrorLiveData.postValue(true)
        }
    }

    private fun isLoadingLiveData(isLoading: Boolean) {
        this.isLoadingLiveData.postValue(isLoading)
    }
}
