package au.com.neds.app.network.model

import com.google.gson.annotations.SerializedName

/**
 * Model class for the Race Data.
 */
data class Data(
    @SerializedName("next_to_go_ids") var nextToGoIds: ArrayList<String> = arrayListOf(),
    @SerializedName("race_summaries") var raceSummaries: Map<String, RaceSummary> = mutableMapOf()
)