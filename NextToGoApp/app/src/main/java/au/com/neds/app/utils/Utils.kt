package au.com.neds.app.utils

import au.com.neds.app.network.model.CATEGORY

object Utils {
    fun getCategoryType(id: String?): String = when (id) {
        Constants.GREYHOUND_RACING_ID -> CATEGORY.GREYHOUND_RACING.categoryType
        Constants.HARNESS_RACING_ID -> CATEGORY.HARNESS_RACING.categoryType
        Constants.HORSE_RACING_ID -> CATEGORY.HORSE_RACING.categoryType
        else -> ""
    }
}