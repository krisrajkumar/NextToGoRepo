package au.com.neds.app.logger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Logger Module for the registering the logs in the project.
 */
@Module
@InstallIn(SingletonComponent::class)
class LoggerModule {

    @Singleton
    @Provides
    fun provideLogger(): AppLogger = AppLogger()
}