package daniel.base.application.core.datasource.api

import daniel.base.application.core.model.BaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<BaseResponse>>

}