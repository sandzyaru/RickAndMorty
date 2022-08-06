package kg.geektech.rickandmorty.domain.repository

import androidx.paging.PagingData
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.domain.common.Either
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getCharacters():Flow<PagingData<Result>>
    suspend fun getCharacterId(id:Int):Flow<Either<String, Result>>
}