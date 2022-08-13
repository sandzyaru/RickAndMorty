package kg.geektech.rickandmorty.data.models


import com.google.gson.annotations.SerializedName

data class CharacterResult(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)

