package daniel.base.application.feature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import daniel.base.application.MainCoroutineRule
import daniel.base.application.core.datasource.repository.FakeMainRepositoryImpl
import daniel.base.application.core.datasource.repository.MainRepositoryImpl
import daniel.base.application.core.model.BaseResponse
import com.google.common.truth.Truth.assertThat
import daniel.base.application.getOrAwaitValue
import daniel.base.application.observeForTesting
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

@ExperimentalCoroutinesApi
class MainViewModelTest {

    // Subject under test
    private lateinit var mainViewModel: MainViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var mainRepositoryImpl: FakeMainRepositoryImpl

    val user = BaseResponse(
        1,
        "Daniel",
        "daniel@gmail.com",
        ""
    )
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainRepositoryImpl = FakeMainRepositoryImpl()
        mainRepositoryImpl.dataUsers.add(user)
        mainViewModel = MainViewModel(mainRepositoryImpl)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun fetchUsers_repositorySuccess() {
        mainViewModel.fetchUsers()
        mainViewModel.users.observeForTesting {
            assertThat(mainViewModel.isUsersAvailable.getOrAwaitValue()).isTrue()
        }
    }

    @Test
    fun fetchUsers_repositoryError(){
        mainRepositoryImpl.setReturnError(true)
        mainViewModel.fetchUsers()
        mainViewModel.users.observeForTesting {
            assertThat(mainViewModel.isUsersAvailable.getOrAwaitValue()).isFalse()
        }
    }
}