package kg.geektech.rickandmorty.presentation.ui.character

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.databinding.FragmentCharactersBinding
import kg.geektech.rickandmorty.presentation.ui.adapter.CharactersAdapter
import kg.geektech.rickandmorty.presentation.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Characters : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(R.layout.fragment_characters) {

    override val viewModel: CharactersViewModel by viewModels()
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    private val adapter = CharactersAdapter(this::onItemClick)


    override fun initViewModel() {
        super.initViewModel()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getCharacters().apply {
                        collect(){
                            adapter.submitData(it)
                        }
                }
            }
        }
    }

    override fun initView() {
        super.initView()
        binding.recycler.adapter = adapter
    }


    override fun inflateViewBinding(inflater: LayoutInflater): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(inflater)
    }
    private fun onItemClick(id:Int) {
      // findNavController().directionsSafeNavigation(CharactersDirections)
    }

}