package au.com.neds.app.repository

import au.com.neds.app.network.datasource.RemoteDataSource
import au.com.neds.app.network.BaseApiResponse
import au.com.neds.app.network.model.RaceResponse
import au.com.neds.app.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val dataSource: RemoteDataSource) : BaseApiResponse() {
    suspend fun getRaces(method: String, count: Int): Flow<NetworkResult<RaceResponse>> {
        return flow<NetworkResult<RaceResponse>> {
            emit(safeApiCall { dataSource.getRaceEvents(method, count) })
        }.flowOn(Dispatchers.IO)
    }
}
