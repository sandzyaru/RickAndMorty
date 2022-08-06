package kg.geektech.rickandmorty.presentation.ui.detail

import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.domain.common.Either
import kg.geektech.rickandmorty.domain.usecase.CharacterDetailUseCase
import kg.geektech.rickandmorty.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val useCase: CharacterDetailUseCase):BaseViewModel() {

  /*  private val _charactersState = mutableUiStateFlow<ResultUI>()
    val charactersState = _charactersState.asStateFlow()

        suspend fun getCharacterDetail(id:Int) = useCase(id).gatherRequest(_charactersState){
            it.toUI()
        }*/
}