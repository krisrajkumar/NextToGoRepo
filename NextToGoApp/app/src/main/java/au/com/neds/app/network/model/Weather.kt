package au.com.neds.app.network.model

import com.google.gson.annotations.SerializedName

/**
 * Model class for the Weather.
 */
data class Weather(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("short_name") var shortName: String? = null,
    @SerializedName("icon_uri") var iconUri: String? = null
)