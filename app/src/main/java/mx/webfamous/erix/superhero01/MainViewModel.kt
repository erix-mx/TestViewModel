package mx.webfamous.erix.superhero01

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: HeroRepository = HeroRepositoryImpl()
) : ViewModel() {
    var state by mutableStateOf<MainState>(MainState.Loading)
        private set

    fun getHeroes() =
        viewModelScope.launch {
            state = MainState.Loading
            val response = repository.getListHeroes()
            state = if (response.isSuccess) {
                MainState.Success(response.getOrNull()!!)
            } else {
                MainState.Error(response.exceptionOrNull()?.message ?: "")
            }
        }
}