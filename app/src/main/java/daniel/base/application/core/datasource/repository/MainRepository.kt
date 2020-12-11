package daniel.base.application.core.datasource.repository

import daniel.base.application.core.datasource.api.ApiHelper
import daniel.base.application.core.model.BaseResponse
import daniel.base.application.core.utils.Resource
import retrofit2.Response

interface MainRepository {

    suspend fun getUsers(): Resource<List<BaseResponse>>

}