package au.com.neds.app.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import au.com.neds.app.repository.Repository
import au.com.neds.app.logger.AppLogger
import au.com.neds.app.network.model.RaceResponse
import au.com.neds.app.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val logger: AppLogger,
    application: Application
) : AndroidViewModel(application) {

    private val racesLiveData: MutableLiveData<NetworkResult<RaceResponse>> = MutableLiveData()
    fun getRacesLiveData() = racesLiveData

    fun loadRaceList() = fetchRaces()

    private fun fetchRaces() = viewModelScope.launch {
        repository.getRaces(RACE_METHOD, RACE_COUNT).collect { response ->
            when (response) {
                is NetworkResult.Loading -> {
                    logger.logDebug("Loading")
                }

                is NetworkResult.Success -> {
                    logger.logDebug("Success - " + response.data?.data?.toString())
                }

                is NetworkResult.Error -> {
                    logger.logDebug("Error")
                }
            }
        }
    }

    companion object {
        private const val RACE_METHOD = "nextraces"
        private const val RACE_COUNT = 10
    }
}