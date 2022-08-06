package kg.geektech.rickandmorty.presentation.ui.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<S, T : BaseDiffModel<S>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}