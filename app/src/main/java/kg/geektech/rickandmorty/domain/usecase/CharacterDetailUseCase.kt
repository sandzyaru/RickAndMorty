package kg.geektech.rickandmorty.domain.usecase

import kg.geektech.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(id:Int) = repository.getCharacterId(id)
}