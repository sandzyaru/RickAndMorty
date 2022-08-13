package kg.geektech.rickandmorty.domain.usecase


import androidx.paging.PagingSource
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: Repository, ){
    fun invoke(status: String?,
                                gender: String?,
                                name: String?): PagingSource<Int,Result> {
        return repository.getCharacters(status,gender, name) }

}