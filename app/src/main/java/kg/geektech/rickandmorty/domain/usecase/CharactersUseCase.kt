package kg.geektech.rickandmorty.domain.usecase

import androidx.paging.PagingData
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(): Flow<PagingData<Result>> = repository.getCharacters()
}