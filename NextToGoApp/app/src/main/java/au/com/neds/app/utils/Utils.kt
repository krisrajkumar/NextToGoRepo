package au.com.neds.app.utils

/**
 * Utility class for various helper functions in the app.
 */
object Utils {

    /**
     * Retrieves the Category type based on the Category Id.
     */
    fun getCategoryType(id: String?): String = when (id) {
        Constants.GREYHOUND_RACING_ID -> CATEGORY.GREYHOUND_RACING.categoryType
        Constants.HARNESS_RACING_ID -> CATEGORY.HARNESS_RACING.categoryType
        Constants.HORSE_RACING_ID -> CATEGORY.HORSE_RACING.categoryType
        else -> CATEGORY.ALL.categoryType
    }

    /**
     * Retrieves the Category type logo based on the Category Id.
     */
    fun getDrawableResource(id: String?): Int = when (id) {
        Constants.GREYHOUND_RACING_ID -> CATEGORY.GREYHOUND_RACING.imageResource
        Constants.HARNESS_RACING_ID -> CATEGORY.HARNESS_RACING.imageResource
        Constants.HORSE_RACING_ID -> CATEGORY.HORSE_RACING.imageResource
        else -> CATEGORY.ALL.imageResource
    }
}