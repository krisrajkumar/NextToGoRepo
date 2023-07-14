package au.com.neds.app.network.datasource

import au.com.neds.app.network.APIService
import javax.inject.Inject

/**
 * Retrieves the data from remote server.
 */
class RemoteDataSource @Inject constructor(private val apiService: APIService) {
    suspend fun getRaceEvents(method: String, count: Int) =
        apiService.getRacingEvents(method, count)
}