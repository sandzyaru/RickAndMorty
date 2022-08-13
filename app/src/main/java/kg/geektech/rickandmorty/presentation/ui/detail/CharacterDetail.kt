package kg.geektech.rickandmorty.presentation.ui.detail

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentCharacterDetailBinding
import kg.geektech.rickandmorty.presentation.ui.base.BaseFragment
import kg.geektech.rickandmorty.presentation.ui.ext.directionsSafeNavigation
import kg.geektech.rickandmorty.presentation.ui.ext.loadWithGlide
import kg.geektech.rickandmorty.presentation.utils.NetworkStatus
import kg.geektech.rickandmorty.presentation.utils.NetworkStatusHelper
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetail :BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(R.layout.fragment_character_detail) {

    override val viewModel: CharacterDetailViewModel by activityViewModels()
    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private val args by navArgs<CharacterDetailArgs>()
    private var isNetwork: Boolean = false

    override fun initView() {
        super.initView()
        viewLifecycleOwner.lifecycleScope.launch {
         viewModel.getCharacter(args.id,isNetwork).collect{
             binding.image.loadWithGlide(it.image)
             binding.name.text = it.name
             binding.gender.text = it.gender
             binding.species.text = it.species
             binding.status.text = it.status }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.tvBack.setOnClickListener{
            findNavController().directionsSafeNavigation(CharacterDetailDirections.actionCharacterDetailToCharacters())
        }
    }

    override fun checkInternet() {
            super.checkInternet()
        NetworkStatusHelper(requireContext()).observe(viewLifecycleOwner) {
            if (it == NetworkStatus.Available) {
                isNetwork = true
            } else {
                isNetwork = false
                Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentCharacterDetailBinding {
            return FragmentCharacterDetailBinding.inflate(inflater)
        }
}