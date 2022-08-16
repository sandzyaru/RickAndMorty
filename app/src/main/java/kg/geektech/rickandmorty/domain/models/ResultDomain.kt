package kg.geektech.rickandmorty.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kg.geektech.rickandmorty.data.models.Result


@Entity
data class ResultDomain(
    @ColumnInfo(name = "created")
    var created: String,
    @ColumnInfo(name = "episode")
    var episode: List<String>,
    @ColumnInfo(name = "gender")
    var gender: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "location")
    var location: LocationDomain,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "origin")
    var origin: OriginDomain,
    @ColumnInfo(name = "species")
    var species: String,
    @ColumnInfo(name = "status")
    var status: String,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "url")
    var url: String
){
    data class LocationDomain(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )

    data class OriginDomain(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}

fun ResultDomain.toData(): Result {
    return Result(
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
        location = Result.Location(location.name,location.url),
        origin = Result.Origin(origin.name,origin.url)
    )
}