package kg.geektech.rickandmorty.presentation.ui.base

interface BaseDiffModel<T> {
    val name: T?
    override fun equals(other: Any?): Boolean
}