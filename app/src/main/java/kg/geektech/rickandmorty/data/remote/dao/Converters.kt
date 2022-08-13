package kg.geektech.rickandmorty.data.remote.dao

import androidx.room.TypeConverter
import kg.geektech.rickandmorty.domain.model.ResultDomain
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONObject

class Converters {

    @TypeConverter
    fun fromList(value : List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)

    @TypeConverter
    fun fromLocation(dbLocation: ResultDomain.LocationDomain): String {
        return JSONObject().apply {
            put("name", dbLocation.name)
            put("url", dbLocation.url)
        }.toString()
    }

    @TypeConverter
    fun toLocation(dbLocation: String): ResultDomain.LocationDomain {
        val json = JSONObject(dbLocation)
        return ResultDomain.LocationDomain(json.getString("name"), json.getString("url"))
    }

    @TypeConverter
    fun fromOrigin(dbOrigin: ResultDomain.OriginDomain): String {
        return JSONObject().apply {
            put("name", dbOrigin.name)
            put("url", dbOrigin.url)
        }.toString()
    }

    @TypeConverter
    fun toOrigin(dbOrigin: String): ResultDomain.OriginDomain {
        val json = JSONObject(dbOrigin)
        return ResultDomain.OriginDomain(json.getString("name"), json.getString("url"))
    }

}