package au.com.neds.app.utils

/**
 * Provides the status for the API result.
 */
sealed class NetworkResult<T>(
    val data: T? = null, val message: String? = null
) {
    /**
     * Provides the Success state for the API result.
     */
    class Success<T>(data: T) : NetworkResult<T>(data)

    /**
     * Provides the Error state for the API result.
     */
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)

    /**
     * Provides the Loading state for the API result.
     */
    class Loading<T> : NetworkResult<T>()
}
