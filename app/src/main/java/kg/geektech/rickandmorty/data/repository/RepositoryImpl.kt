package kg.geektech.rickandmorty.data.repository


import androidx.paging.PagingSource
import kg.geektech.rickandmorty.data.apiservice.CharacterApi
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.data.remote.dao.CharacterDao
import kg.geektech.rickandmorty.data.remote.pagingsource.CharacterPagingSource
import kg.geektech.rickandmorty.domain.model.toData
import kg.geektech.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val characterApi: CharacterApi,
                                         private val characterPagingSource: CharacterPagingSource,
                                         private val dao: CharacterDao):Repository {

   override  fun getCharacters(status: String?, name: String?, gender:String?
   ): PagingSource<Int,Result> {
       characterPagingSource.setGenderFilter(gender)
       characterPagingSource.setStatusFilter(status)
       characterPagingSource.setNameFilter(name)
       return characterPagingSource
   }

    override suspend fun getCharacterId(id:Int,isNetwork:Boolean): Result {
        return if(isNetwork){
            characterApi.getCharacterDetail(id)
        }else{
            dao.getCharacterDetail(id).toData()
        }
    }
}