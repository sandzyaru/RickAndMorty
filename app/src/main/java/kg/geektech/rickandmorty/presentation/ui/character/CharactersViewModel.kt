package kg.geektech.rickandmorty.presentation.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.domain.usecase.CharactersUseCase
import kg.geektech.rickandmorty.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase
):BaseViewModel() {

     var status = MutableLiveData<String>()
     var gender = MutableLiveData<String>()
     var name = MutableLiveData<String>()

    fun initStatusAndGender(status: String?,name: String?,gender: String?){
        this.status.value = status.toString()
        this.gender.value = gender.toString()
        this.name.value = name.toString()

    }

     fun getCharacters(status: String?, gender: String?, name: String?): Flow<PagingData<Result>> {
       val characters: StateFlow<PagingData<Result>> = Pager(PagingConfig(
            pageSize = 25,
            enablePlaceholders = false))
       {
           charactersUseCase.invoke(status,gender, name)
       }
           .flow
           .cachedIn(viewModelScope)
           .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
        return characters
    }
}