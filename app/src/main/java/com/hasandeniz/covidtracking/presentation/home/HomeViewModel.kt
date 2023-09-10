package com.hasandeniz.covidtracking.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasandeniz.covidtracking.data.remote.dto.statistics.StatisticDto
import com.hasandeniz.covidtracking.domain.usecase.getStatistics.GetStatisticsUseCase
import com.hasandeniz.covidtracking.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStatisticsUseCase: GetStatisticsUseCase
) : ViewModel() {


    private var _response: MutableLiveData<Resource<StatisticDto>> = MutableLiveData()
    val response: LiveData<Resource<StatisticDto>> = _response


    init {
        fetchCountries(null)
    }

    fun fetchCountries(searchQuery: String?) {
        var query = searchQuery
        if (query == "")
            query = null
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(Resource.Loading())
            _response.postValue(getStatisticsUseCase.execute(query))
        }
    }


}