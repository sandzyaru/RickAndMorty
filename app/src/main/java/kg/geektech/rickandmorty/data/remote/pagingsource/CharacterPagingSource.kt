package kg.geektech.rickandmorty.data.remote.pagingsource

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kg.geektech.rickandmorty.data.apiservice.CharacterApi
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.data.models.toDomain
import kg.geektech.rickandmorty.data.remote.dao.CharacterDao
import kg.geektech.rickandmorty.domain.model.toData
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterPagingSource  @Inject constructor(private val characterApi: CharacterApi,
                                                 private val dao: CharacterDao): PagingSource<Int, Result>() {

    private var status:String?= null
    private var name:String?= null
    private var gender:String?= null

    fun setNameFilter(searchString: String?) {
        if (searchString != null) {
            name = searchString
            Log.e("PagingSource", name.toString())
        }
    }

    fun setGenderFilter(genderString: String?) {
        if (genderString != null) {
            gender = genderString
            Log.e("PagingSource", genderString.toString())

        }
    }

    fun setStatusFilter(statusString: String?) {
        if (statusString != null) {
            status = statusString
            Log.e("PagingSource", statusString.toString())

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val response =
                characterApi.getCharacters(
                    page= page,
                    status =status,
                    gender = gender,
                    name = name)
                response.body()?.results?.map { it.toDomain() }?.let { dao.insert(it) }


                val nextPageNumber: Int? = if(response.body()?.info?.next == null){
                    null
                }else{
                   Uri.parse(response.body()!!.info.next).getQueryParameter("page")?.toInt()
                }

                LoadResult.Page(data = response.body()!!.results, prevKey = null, nextKey = nextPageNumber)
        }catch (ioException: IOException) {
           LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            localData(params,httpException)
        } catch (exception: Exception) {
            localData(params,exception)
        }
    }

    private suspend fun localData(params: LoadParams<Int>, exception: Exception): LoadResult<Int, Result>{
        val pageNumberLocal = params.key ?: 0
        val responseRoom = dao.getAllCharacters(params.loadSize,status,name)
        return if(responseRoom.isEmpty()) {
            LoadResult.Page(
                responseRoom.map { it.toData() },
                prevKey = if (pageNumberLocal == 0) null else pageNumberLocal - 1,
                nextKey = if (responseRoom.isEmpty()) null else pageNumberLocal + 1
            )
        }else{
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }    }
}