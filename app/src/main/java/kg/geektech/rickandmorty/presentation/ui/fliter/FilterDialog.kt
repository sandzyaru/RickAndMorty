package kg.geektech.rickandmorty.presentation.ui.fliter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kg.geektech.rickandmorty.databinding.DialogFilterBinding
import kg.geektech.rickandmorty.presentation.ui.character.CharactersViewModel
import kg.geektech.rickandmorty.presentation.ui.ext.directionsSafeNavigation


class FilterDialog  : DialogFragment() {

    private lateinit var binding:DialogFilterBinding
    private val viewModel: CharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()

    }

    private fun initListener() {
        binding.clear.setOnClickListener {

            binding.characterName.setText("")
            binding.rgStatus.clearCheck()
            binding.rgGender.clearCheck()
        }

        binding.btnApply.setOnClickListener{
            var name:String? = ""
            val status = when (binding.rgStatus.checkedRadioButtonId) {
                 binding.rbAlive.id -> "alive"
                 binding.rbDead.id -> "dead"
                 binding.rbUnknown.id -> "unknown"
                 else -> ""
            }
            val gender = when (binding.rgGender.checkedRadioButtonId) {
                    binding.rbMale.id  -> "male"
                    binding.rbFemale.id -> "female"
                    binding.rbGenderless.id -> "genderless"
                    binding.rbgUnknown.id -> "unknown"
                    else ->""
                }
            binding.characterName
            name = (binding.characterName as TextView).text.toString()
            Log.e("status", status)
            Log.e("gender", gender)
            Log.e("name", name)
            viewModel.initStatusAndGender(status, gender, name)

            findNavController().directionsSafeNavigation(FilterDialogDirections.actionFilterDialogToCharacters())
        }
    }
}
