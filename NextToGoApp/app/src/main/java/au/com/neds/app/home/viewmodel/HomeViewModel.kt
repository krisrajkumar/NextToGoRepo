package au.com.neds.app.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import au.com.neds.app.home.model.RaceData
import au.com.neds.app.repository.Repository
import au.com.neds.app.logger.AppLogger
import au.com.neds.app.utils.CATEGORY
import au.com.neds.app.utils.NetworkResult
import au.com.neds.app.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for the Home Screen.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val logger: AppLogger,
    application: Application
) : AndroidViewModel(application) {

    private val homeUILivedata: MutableLiveData<List<RaceData>> = MutableLiveData()
    private val toggleProgressLivedata: MutableLiveData<Boolean> = MutableLiveData(true)
    fun getRacesLiveData() = homeUILivedata
    fun getToggleProgressLiveData() = toggleProgressLivedata

    fun loadRaceListApi() = fetchRaces()

    private var raceArrayList: List<RaceData> = ArrayList<RaceData>()

    /**
     * Retrieves all the races events.
     */
    private fun fetchRaces() = viewModelScope.launch {
        repository.getRaces(RACE_METHOD, RACE_COUNT).collect { response ->
            when (response) {
                is NetworkResult.Loading -> {
                    logger.logDebug("Loading")
                    getToggleProgressLiveData().postValue(true)
                }

                is NetworkResult.Success -> {
                    getToggleProgressLiveData().postValue(false)
                    logger.logDebug("Success - " + response.data?.data?.toString())
                    val raceList = ArrayList<RaceData>()
                    response.data?.data?.raceSummaries?.entries?.forEach {
                        raceList.add(
                            RaceData(
                                title = it.value.raceName,
                                time = it.value.advertisedStart?.seconds?.toLong(),
                                category = Utils.getCategoryType(it.value.categoryId),
                                logo = Utils.getDrawableResource(it.value.categoryId)
                            )
                        )
                    }
                    // Store the race list locally
                    raceArrayList = raceList
                    homeUILivedata.postValue(sortRaceEvent(raceList))
                }

                is NetworkResult.Error -> {
                    logger.logDebug("Error")
                }
            }
        }
    }

    /**
     * Sorts the race events based on
     */
    private fun sortRaceEvent(raceList: List<RaceData>): List<RaceData> {
        return raceList.sortedWith(compareBy { it.time }).take(SORT_LIMIT)
    }

    /**
     * Retrieves the race events based on the Category.
     */
    fun getRaceByCategory(category: CATEGORY) {
        when (category) {
            CATEGORY.ALL -> {
                homeUILivedata.postValue(sortRaceEvent(raceArrayList))
            }

            else -> {
                val filteredRaceList =
                    raceArrayList.filter { it.category.equals(category.categoryType) }
                homeUILivedata.postValue(filteredRaceList)
            }
        }
    }

    companion object {
        private const val RACE_METHOD = "nextraces"
        private const val RACE_COUNT = 10
        private const val SORT_LIMIT = 5
    }
}