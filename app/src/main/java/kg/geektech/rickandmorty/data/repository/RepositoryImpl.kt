package kg.geektech.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kg.geektech.rickandmorty.data.apiservice.CharacterApi
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.data.remote.pagingsource.CharacterPagingSource
import kg.geektech.rickandmorty.domain.common.Either
import kg.geektech.rickandmorty.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val characterApi: CharacterApi):Repository {

    override suspend fun getCharacters(): Flow<PagingData<Result>> {
        return Pager(PagingConfig(
            pageSize = 30,
            enablePlaceholders = true),
            pagingSourceFactory = {CharacterPagingSource(characterApi)}).flow
    }

    override suspend fun getCharacterId(id:Int): Flow<Either<String, Result>> {
        return flow {
            characterApi.getCharacterDetail(id)
        }
    }


}