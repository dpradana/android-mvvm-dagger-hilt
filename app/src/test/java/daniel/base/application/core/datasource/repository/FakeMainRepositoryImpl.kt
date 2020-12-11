package daniel.base.application.core.datasource.repository

import daniel.base.application.core.datasource.Result
import daniel.base.application.core.model.BaseResponse
import daniel.base.application.core.utils.Resource
import org.junit.Assert.*
import retrofit2.Response
import javax.inject.Inject

class FakeMainRepositoryImpl @Inject constructor() : MainRepository {
    private var shouldReturnError = false
    var dataUsers = ArrayList<BaseResponse>()

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getUsers(): Resource<List<BaseResponse>> {
        if(shouldReturnError){
            return Resource.error("Test exception", null)
        }
        return Resource.success(dataUsers)
    }

}