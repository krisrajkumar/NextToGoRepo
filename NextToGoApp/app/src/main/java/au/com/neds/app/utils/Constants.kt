package au.com.neds.app.utils

/**
 * Helper class to define the constants for the app.
 */
class Constants {
    companion object {
        /**
         * Base URL for the neds API.
         */
        const val BASE_URL = "https://api.neds.com.au/rest/"

        // Category Id's for Race events.
        const val GREYHOUND_RACING_ID = "9daef0d7-bf3c-4f50-921d-8e818c60fe61"
        const val HARNESS_RACING_ID = "161d9be2-e909-4326-8c2c-35ed71fb460b"
        const val HORSE_RACING_ID = "4a2788f8-e825-4d36-9894-efd4baf1cfae"
    }
}
