package kg.geektech.rickandmorty.presentation.ui.character

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.domain.usecase.CharactersUseCase
import kg.geektech.rickandmorty.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val useCase: CharactersUseCase
):BaseViewModel() {
    suspend fun getCharacters(): Flow<PagingData<Result>> {
        return useCase().cachedIn(viewModelScope)
    }
}