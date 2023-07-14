package au.com.neds.app.network.model

import com.google.gson.annotations.SerializedName

/**
 * Model class for the RaceForm.
 */
data class RaceForm(
    @SerializedName("distance") var distance: Int? = null,
    @SerializedName("distance_type") var distanceType: DistanceType? = DistanceType(),
    @SerializedName("distance_type_id") var distanceTypeId: String? = null,
    @SerializedName("track_condition") var trackCondition: TrackCondition? = TrackCondition(),
    @SerializedName("track_condition_id") var trackConditionId: String? = null,
    val weather: Weather? = null,
    @SerializedName("weather_id")
    val weatherId: Int? = null,
    @SerializedName("race_comment") var raceComment: String? = null,
    @SerializedName("additional_data") var additionalData: String? = null,
    @SerializedName("generated") var generated: Int? = null,
    @SerializedName("silk_base_url") var silkBaseUrl: String? = null,
    @SerializedName("race_comment_alternative") var raceCommentAlternative: String? = null
)