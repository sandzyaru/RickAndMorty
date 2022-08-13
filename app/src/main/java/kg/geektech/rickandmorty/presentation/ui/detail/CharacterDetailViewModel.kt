package kg.geektech.rickandmorty.presentation.ui.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import kg.geektech.rickandmorty.domain.usecase.CharacterDetailUseCase
import kg.geektech.rickandmorty.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val useCase: CharacterDetailUseCase,
):BaseViewModel() {

    suspend fun getCharacter(id: Int,isNetwork:Boolean) =flow{ emit(useCase.invoke(id,isNetwork)) }

}

