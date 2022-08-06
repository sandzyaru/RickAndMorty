package kg.geektech.rickandmorty.presentation.ui.detail

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentCharacterDetailBinding
import kg.geektech.rickandmorty.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class CharacterDetail(override val binding: FragmentCharacterDetailBinding) :BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(R.layout.fragment_character_detail) {

    override val viewModel: CharacterDetailViewModel by viewModels()

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentCharacterDetailBinding {
        return FragmentCharacterDetailBinding.inflate(inflater)
    }

}