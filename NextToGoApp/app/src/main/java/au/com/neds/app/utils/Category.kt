package au.com.neds.app.utils

import au.com.neds.app.R

/**
 * Util class for Race Categories.
 */
enum class CATEGORY(
    val categoryType: String,
    val imageResource: Int
) {
    /**
     * Represents all the Race Category.
     */
    ALL("All", R.drawable.ic_clear_all),

    /**
     * Represents the Greyhound Racing Category.
     */
    GREYHOUND_RACING("Greyhound Racing", R.drawable.ic_greyhound),

    /**
     * Represents the Harness Racing Category.
     */
    HARNESS_RACING("Harness Racing", R.drawable.ic_harness),

    /**
     * Represents the Horse Racing Category.
     */
    HORSE_RACING("Horse Racing", R.drawable.ic_horse_racing);
}