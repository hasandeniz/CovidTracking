package com.hasandeniz.covidtracking.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasandeniz.covidtracking.data.remote.dto.history.HistoryDto
import com.hasandeniz.covidtracking.data.remote.dto.statistics.StatisticDto
import com.hasandeniz.covidtracking.domain.usecase.getHistory.GetHistoryUseCase
import com.hasandeniz.covidtracking.domain.usecase.getStatistics.GetStatisticsUseCase
import com.hasandeniz.covidtracking.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getStatisticsUseCase: GetStatisticsUseCase,
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {

    private var _countryDetailsResponse: MutableLiveData<Resource<StatisticDto>> = MutableLiveData()
    val countryDetailsResponse: LiveData<Resource<StatisticDto>> = _countryDetailsResponse

    private var _historyResponse: MutableLiveData<Resource<HistoryDto>> = MutableLiveData()
    val historyResponse: LiveData<Resource<HistoryDto>> = _historyResponse

    fun getCountryDetails(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _countryDetailsResponse.postValue(getStatisticsUseCase.execute(name))
        }
    }

    fun getHistory(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _historyResponse.postValue(getHistoryUseCase.execute(country))
        }
    }


}