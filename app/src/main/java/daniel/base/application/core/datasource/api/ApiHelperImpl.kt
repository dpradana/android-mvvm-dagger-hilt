package daniel.base.application.core.datasource.api

import daniel.base.application.core.model.BaseResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<BaseResponse>> = apiService.getUsers()

}