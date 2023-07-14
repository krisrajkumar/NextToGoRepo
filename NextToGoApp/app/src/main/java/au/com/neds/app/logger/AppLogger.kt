package au.com.neds.app.logger

import timber.log.Timber

/**
 * Utility class to log the events for the app.
 */
class AppLogger {
    /**
     * Logs the debug events.
     */
    fun logDebug(message: String) {
        Timber.d(message)
    }

    /**
     * Logs the error events.
     */
    fun logError(message: String) {
        Timber.e(message)
    }

    /**
     * Logs the verbose events.
     */
    fun logVerbose(message: String) {
        Timber.v(message)
    }

    /**
     * Logs the info events.
     */
    fun logInfo(message: String) {
        Timber.i(message)
    }
}