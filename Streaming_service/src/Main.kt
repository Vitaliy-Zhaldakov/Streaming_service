import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import java.io.FileReader
import java.io.FileWriter
import java.io.File
import java.io.FileNotFoundException

// сериализует объект
fun<T> serializeObject(someObject: T): String = Gson().toJson(someObject)

// сериализует список объектов и записывает в json файл
fun<T> writeToJson(fileName: String, jsonObjects: T) {
    if (!fileName.endsWith(".json"))
        throw IllegalArgumentException("$fileName isn't .json file")

    FileWriter(fileName).use { writer ->
        Gson().toJson(jsonObjects, writer) }
}

// десериализует объект
inline fun<reified T> deserializeObject(jsonObject: String): T =
    try {
        Gson().fromJson(jsonObject, T::class.java)
    }
    catch(e: com.google.gson.JsonSyntaxException) {
        throw ClassCastException("Invalid type for deserialization")
    }

// читает список объектов из json файла и десериализует
inline fun<reified T> readFromJsonFile(fileName: String): List<T> {
    if (!fileName.endsWith(".json"))
        throw IllegalArgumentException("$fileName isn't .json file")

    if (!File(fileName).exists())
        throw FileNotFoundException("$fileName doesn't exist")

    val list: MutableList<T> = mutableListOf()
    FileReader(fileName).use { reader ->
        val gson = Gson()
        val array: JsonArray = JsonParser().parse(reader).asJsonArray
        for (jsonElement in array) {
            list.add(gson.fromJson(jsonElement, T::class.java))
        }
    }

    return list
}

fun main(){

}