package kg.geektech.rickandmorty.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.rickandmorty.data.models.Result
import kg.geektech.rickandmorty.databinding.ItemCharacterBinding
import kg.geektech.rickandmorty.presentation.ui.base.BaseDiffUtil

class CharactersAdapter( private val onItemClick: ((id: Int) -> Unit)? = null) :
    PagingDataAdapter<Result,CharactersAdapter.CharactersViewHolder>(BaseDiffUtil()) {


    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }
    inner class CharactersViewHolder(private val binding: ItemCharacterBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result){
            binding.name.text = result.name
            binding.status.text = result.status
            binding.type.text = result.type

         /*   itemView.setOnClickListener{
                onItemClick?.invoke(resultUI.url.filter (Char::isDigit).toInt())
            }*/
        }
    }
}