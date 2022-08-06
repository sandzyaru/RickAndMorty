package kg.geektech.rickandmorty.data.remote.pagingsource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kg.geektech.rickandmorty.data.apiservice.CharacterApi
import kg.geektech.rickandmorty.data.models.Result
import retrofit2.HttpException
import java.io.IOException

class CharacterPagingSource(private val characterApi: CharacterApi): PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val response = characterApi.getCharacters(page)

            val nextPageNumber = if (response.info?.next == null) {
                null
            } else
                Uri.parse(response.info.next).getQueryParameter("page")?.toInt()
            LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)

        }catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }    }
}