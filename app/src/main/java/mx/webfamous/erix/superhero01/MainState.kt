package mx.webfamous.erix.superhero01

sealed class MainState {
    object Loading: MainState()
    data class Success(val data: Hero): MainState()
    data class Error(val message: String): MainState()
}

