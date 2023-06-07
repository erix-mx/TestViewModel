package mx.webfamous.erix.superhero01

class HeroRepositoryImpl: HeroRepository {
    override fun getListHeroes(): Result<Hero> = Result.success(Hero("Aris"))
}