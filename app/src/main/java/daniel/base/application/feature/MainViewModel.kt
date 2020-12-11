package daniel.base.application.feature

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import daniel.base.application.core.datasource.repository.MainRepository
import daniel.base.application.core.datasource.repository.MainRepositoryImpl
import daniel.base.application.core.model.BaseResponse
import daniel.base.application.core.utils.NetworkHelper
import daniel.base.application.core.utils.Resource
import daniel.base.application.core.utils.Status
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<BaseResponse>>>()
    val users: LiveData<Resource<List<BaseResponse>>>
        get() = _users
    val isUsersAvailable: LiveData<Boolean> = _users.map { it.status != Status.ERROR }

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            try {
                mainRepository.getUsers().let {
                    _users.postValue(it)
                }
            }catch (e: Exception){
                _users.postValue(Resource.error("No internet connection", null))
            }
        }
    }

}