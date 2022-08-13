package kg.geektech.rickandmorty.data.apiservice

import kg.geektech.rickandmorty.data.models.CharacterResult
import kg.geektech.rickandmorty.data.models.Result
import retrofit2.Response


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("status") status: String? = null,
        @Query("gender") gender: String? = null,
        @Query("name") name: String? = null,
        @Query("page") page: Int
    ): Response<CharacterResult>

    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int):Result

}