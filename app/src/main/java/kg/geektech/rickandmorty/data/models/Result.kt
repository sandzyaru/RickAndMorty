package kg.geektech.rickandmorty.data.models


import com.google.gson.annotations.SerializedName
import kg.geektech.rickandmorty.domain.models.ResultDomain

data class Result(
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
){
    data class Location(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )

    data class Origin(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}

fun Result.toDomain(): ResultDomain {
    return ResultDomain(
        id = id,
        name = name,
        status = status,
        gender = gender,
        image = image,
        species = species,
        type = type,
        created = created,
        url = url,
        episode = episode,
        location = ResultDomain.LocationDomain(location.name,location.url),
        origin = ResultDomain.OriginDomain(origin.name,origin.url)
    )
}





