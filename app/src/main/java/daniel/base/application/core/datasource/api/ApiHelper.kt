package daniel.base.application.core.datasource.api

import daniel.base.application.core.model.BaseResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<List<BaseResponse>>
}