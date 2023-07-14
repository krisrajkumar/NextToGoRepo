package au.com.neds.app.network

import au.com.neds.app.network.model.RaceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API Service class to define the endpoints.
 */
interface APIService {

    @GET("v1/racing/")
    suspend fun getRacingEvents(
        @Query("method") method: String,
        @Query("count") count: Int
    ): Response<RaceResponse>

}