package kg.geektech.rickandmorty.data.ext

import retrofit2.Retrofit

inline fun <reified T : Any> Retrofit.createAnApi(): T {
    return create(T::class.java)
}