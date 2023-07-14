package au.com.neds.app.network.model

import com.google.gson.annotations.SerializedName

/**
 * Model class for the RaceSummary.
 */
data class RaceSummary(
    @SerializedName("race_id") var raceId: String? = null,
    @SerializedName("race_name") var raceName: String? = null,
    @SerializedName("race_number") var raceNumber: Int? = null,
    @SerializedName("meeting_id") var meetingId: String? = null,
    @SerializedName("meeting_name") var meetingName: String? = null,
    @SerializedName("category_id") var categoryId: String? = null,
    @SerializedName("advertised_start") var advertisedStart: AdvertisedStart? = AdvertisedStart(),
    @SerializedName("race_form") var raceForm: RaceForm? = RaceForm(),
    @SerializedName("venue_id") var venueId: String? = null,
    @SerializedName("venue_name") var venueName: String? = null,
    @SerializedName("venue_state") var venueState: String? = null,
    @SerializedName("venue_country") var venueCountry: String? = null
)