package au.com.neds.app.network.model

import com.google.gson.annotations.SerializedName

/**
 * Model class for the AdvertisedStart.
 */
data class AdvertisedStart(
    @SerializedName("seconds") var seconds: Int? = null
)