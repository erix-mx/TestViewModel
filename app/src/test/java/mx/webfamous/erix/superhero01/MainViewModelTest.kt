package mx.webfamous.erix.superhero01

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class MainViewModelTest{

    private lateinit var viewModel: MainViewModel
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = TestCoroutineDispatcher()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestCoroutineScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `when getHeroes is called then state is Loading`(){
        viewModel = MainViewModel(FakeRepositoryImpl())
        assertTrue(viewModel.state is MainState.Loading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when getHeroes is called then state is Success`()  = testScope.runBlockingTest  {
        viewModel = MainViewModel(FakeRepositoryImpl())
        viewModel.getHeroes()
        assertTrue(viewModel.state is MainState.Success)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when getHeroes is called then state is Error`() = runTest {
        viewModel = MainViewModel(FakeRepositoryErrorImpl())
        viewModel.getHeroes()
        assertTrue(viewModel.state is MainState.Error)
    }
}

class FakeRepositoryImpl: HeroRepository {
    override fun getListHeroes(): Result<Hero> = Result.success(Hero("Aris"))
}

class FakeRepositoryErrorImpl: HeroRepository {
    override fun getListHeroes(): Result<Hero> = Result.failure(Exception("Error"))
}