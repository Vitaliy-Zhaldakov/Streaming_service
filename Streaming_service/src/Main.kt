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
//inline - повышает производительность
//Reified - это ключевое слово, которое может быть использовано только в inline-функциях.
// Его цель - получение доступа к информации о типе класс

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

    val streamingServices = listOf(
        StreamingService(1, "Netflix", "USA", 599.0, 148.9),
        StreamingService(2, "КиноПоиск","Russia", 269.0, 6.0),
        StreamingService(3, "Disney+", "USA", 512.0, 95.0),
        StreamingService(4, "Амедиатека", "Russia", 599.0, 4.0),
        StreamingService(5, "HBO Max", "USA", 1099.0, 17.2))

    val videoProduct = listOf(
        VideoProduct(1,2,1,3,1,1,"Побег из шоушенка ",16,25000000.0,58300000.0),
        VideoProduct(2,2,1,7,2,2,"Властелин колец: Возвращение короля",12,93000000.0,897700000.0),
        VideoProduct(3,3,3,8,3,3,"Король лев",6,260000000.0,1656943394.0),
        VideoProduct(4,2,1,1,4,4,"Бойцовский клуб",18,63000000.0,101200000.0),
        VideoProduct(5,2,1,1,5,5,"Матрица",16, 63000000.0,465300000.0),
        VideoProduct(6,1,1,3,6,6,"Брат",16,100000.0,40000.0),
        VideoProduct(7,1,2,6,7,7,"Ведьмак",18,80000000.0,800000000.0),
        VideoProduct(8,5,2,3,8,8,"Чернобыль",18,250000000.0,1000000000.0),
        VideoProduct(9,5,2,6,8,9,"Игра престолов",18,500000000.0,6000000000.0),
        VideoProduct(10,4,2,5,9,10,"Кремниевая долина",18,350000000.0,900000000.0))

    val company = listOf(
        Company(1,"Castle Rock Entertainment","USA"),
        Company(2,"New Line Cinema","USA"),
        Company(3,"The Walt Disney Company","USA"),
        Company(4,"20th Centure Studios","USA"),
        Company(5,"Warner Bros. Entertainment","USA"))

    val genre = listOf(
        Genre(1,"Боевик"),
        Genre(2,"Детектив"),
        Genre(3,"Драма"),
        Genre(4,"Мелодрама"),
        Genre(5,"Комедия"),
        Genre(6,"Фэнтези"),
        Genre(7,"Приключения"),
        Genre(8,"Анимация"))

    val rate = listOf(
        Rate(1, 9.1, 9.0, 9.1),
        Rate(2, 8.6, 9.1, 9.3),
        Rate(3, 8.8, 8.8, 6.2),
        Rate(4, 8.6, 9.0,7.9 ),
        Rate(5, 8.5, 9.0, 8.8),
        Rate(6, 8.2, 8.0, 8.3),//Brat
        Rate(7, 7.4, 7.3, 9.1),//Witcher
        Rate(8,8.9, 9.1, 9.8),//Chernobyl
        Rate(9, 9.0, 8.0,8.5 ),//GameOfThrones
        Rate(10, 8.3, 8.8, 9.2))//SiliconValley


    val typeProduct = listOf(
        TypeProduct(1, "Фильм"),
        TypeProduct(2, "Сериал"),
        TypeProduct(3, "Мультфильм"),
        TypeProduct(4, "Мультсериал"),
        TypeProduct(5, "TV-Shows"))

    
    writeToJson("json/streamingServices.json", streamingServices)
    writeToJson("json/videoProduct.json", videoProduct)
    writeToJson("json/company.json", company)
    writeToJson("json/genre.json", genre)
    writeToJson("json/rate.json", rate)
    writeToJson("json/typeProduct.json", typeProduct)



    readFromJsonFile<StreamingService>("json/streamingServices.json").map { println(it) }
    readFromJsonFile<VideoProduct>("json/videoProduct.json").map { println(it) }
    readFromJsonFile<Company>("json/company.json").map { println(it) }
    readFromJsonFile<Genre>("json/genre.json").map { println(it) }
    readFromJsonFile<Rate>("json/rate.json").map { println(it) }
    readFromJsonFile<TypeProduct>("json/typeProduct.json").map { println(it) }
    

    //Запросы-------------------------------------------------------------------
    println("Самый высоко оценённый фильм на кинопоиске: \n${videoProduct.find{it.codeProduct == rate.maxByOrNull{it.kinopoiskRate}!!.codeRating}}")
    println("Самый популярный жанр: ${genre[videoProduct.groupingBy { it.codeGenre }.eachCount().maxByOrNull { it.value }!!.key - 1].nameGenre}")
    println("\nСамый прибыльный фильм:\n${videoProduct.filter { it.codeType == (typeProduct.filter { it.nameType == "Фильм" }.map { it.codeType }.first()) }.maxByOrNull { it.boxOffice - it.productionCost }}")
    println("Возрастной рейтинг с самой большой оценкой на Metacritic: ${videoProduct[rate.maxByOrNull { it.metacriticRate}!!.codeRating - 1].ageRating}")
    println("\nСамый дешевый иностранный сервис:\n${streamingServices.filter { it.countryService != "Russia" }.minByOrNull { it.subscriptionPrice }}")
}
