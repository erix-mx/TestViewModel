package mx.webfamous.erix.superhero01

interface HeroRepository {
    //get
    fun getListHeroes(): Result<Hero>
}