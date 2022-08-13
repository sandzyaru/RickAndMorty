package kg.geektech.rickandmorty.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import kg.geektech.rickandmorty.data.models.Result

class BaseDiffUtil : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result) =
        oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Result, newItem: Result) = oldItem == newItem
}