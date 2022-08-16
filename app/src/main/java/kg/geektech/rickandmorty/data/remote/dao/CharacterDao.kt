package kg.geektech.rickandmorty.data.remote.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kg.geektech.rickandmorty.domain.models.ResultDomain

@Dao
interface CharacterDao {

    @Query("SELECT * FROM resultDomain WHERE (:nameFilter IS NULL OR name LIKE '%' || :nameFilter || '%') AND (:statusFilter IS NULL OR status LIKE '%' || :statusFilter || '%') AND (:genderFilter IS NULL OR gender LIKE '%' || :genderFilter || '%') AND id IN (SELECT id FROM resultDomain LIMIT :page) ")
    suspend fun getAllCharacters(
        page: Int,
        statusFilter: String?,
        genderFilter: String?,
        nameFilter: String?
    ) : List<ResultDomain>

    @Query("SELECT * FROM resultDomain WHERE id LIKE '%' || :ID || '%'")
    suspend fun getCharacterDetail(ID: Int): ResultDomain

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(resultDomainList:List<ResultDomain>)

}