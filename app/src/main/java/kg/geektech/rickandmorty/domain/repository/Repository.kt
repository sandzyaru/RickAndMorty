package kg.geektech.rickandmorty.domain.repository

import androidx.paging.PagingSource
import kg.geektech.rickandmorty.data.models.Result


interface Repository {
    fun getCharacters(
        status:String?,
        name:String?,
        gender: String?): PagingSource<Int, Result>
    suspend fun getCharacterId(id:Int,isNetwork:Boolean): Result
}