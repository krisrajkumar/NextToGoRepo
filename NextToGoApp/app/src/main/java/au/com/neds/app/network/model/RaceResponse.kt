package au.com.neds.app.network.model

import com.google.gson.annotations.SerializedName

/**
 * Model class for the RaceResponse.
 */
data class RaceResponse(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("message") var message: String? = null
)