package kg.geektech.rickandmorty.presentation.ui.character
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentCharactersBinding
import kg.geektech.rickandmorty.presentation.ui.adapter.CharactersAdapter
import kg.geektech.rickandmorty.presentation.ui.base.BaseFragment
import kg.geektech.rickandmorty.presentation.ui.ext.directionsSafeNavigation
import kg.geektech.rickandmorty.presentation.utils.NetworkStatus
import kg.geektech.rickandmorty.presentation.utils.NetworkStatusHelper
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Characters : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(R.layout.fragment_characters) {

    override val viewModel: CharactersViewModel by activityViewModels()
    override val binding by viewBinding(FragmentCharactersBinding::bind)
    private val adapter = CharactersAdapter(this::onItemClick)
    private var status: String? = null
    private var gender: String? = null
    private var name: String? = null

    override fun initView() {
        super.initView()
        binding.recycler.adapter = adapter
        lifecycleScope.launch{
            viewModel.getCharacters(status, gender, name).collect(adapter::submitData)
        }

    }

    override fun initViewModel() {
        super.initViewModel()
                viewModel.name.observe(viewLifecycleOwner, Observer {
                    name = it.toString()
                    Log.e("CHARACTERS NAME", it)
                    lifecycleScope.launch {
                        if (it.toString().isNotEmpty()){
                            viewModel.getCharacters(status,gender, it.toString())
                                .collect(adapter::submitData)
                        }else{ viewModel.getCharacters(status,gender,"").collect(adapter::submitData) }
                    }
                })
                viewModel.gender.observe(viewLifecycleOwner, Observer {
                    gender = it.toString()
                    Log.e("CHARACTERS GENDER", it)
                    lifecycleScope.launch{
                        if (it.toString().isNotEmpty()){
                            viewModel.getCharacters(status,it.toString(), name)
                                .collect(adapter::submitData)
                        }else{ viewModel.getCharacters(status,"",name).collect(adapter::submitData) }
                    }
                })
                viewModel.status.observe(viewLifecycleOwner, Observer {
                    status = it.toString()
                    Log.e("CHARACTERS STATUS", it)
                    lifecycleScope.launch{
                        if (it.toString().isNotEmpty()){
                            viewModel.getCharacters(it.toString(),gender, name)
                                .collect(adapter::submitData)
                        }else{ viewModel.getCharacters("",gender,name).collect(adapter::submitData) }
                    }
                })
    }

    override fun initListener() {
        super.initListener()
        binding.imageButton.setOnClickListener{
            findNavController().directionsSafeNavigation(CharactersDirections.actionCharactersToFilterDialog())
        }
    }

    override fun initSearchView() {
        super.initSearchView()

    }

    override fun checkInternet() {
        super.checkInternet()
        checkConnection()
    }

    private fun checkConnection() {
        NetworkStatusHelper(requireContext()).observe(viewLifecycleOwner) {
            if (it == NetworkStatus.Available) {
                binding.recycler.visibility = View.VISIBLE
                initView()
            } else {
                Toast.
                makeText(requireContext(), "No internet connection", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun onItemClick(id: Int) {
       findNavController().directionsSafeNavigation(CharactersDirections.actionCharactersToCharacterDetail(id))
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(inflater)
    }
}
