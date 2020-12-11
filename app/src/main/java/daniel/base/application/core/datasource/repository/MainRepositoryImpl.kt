package daniel.base.application.core.datasource.repository

import daniel.base.application.core.datasource.api.ApiHelper
import daniel.base.application.core.model.BaseResponse
import daniel.base.application.core.utils.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiHelper: ApiHelper): MainRepository {

    override suspend fun getUsers(): Resource<List<BaseResponse>> {
        apiHelper.getUsers().let {
            if(it.isSuccessful){
                return Resource.success(it.body())
            }else{
                return Resource.error(it.message(), null)
            }
        }
    }
}