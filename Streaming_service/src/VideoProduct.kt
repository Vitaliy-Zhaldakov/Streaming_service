class VideoProduct( val codeProduct: Int,val codeService:Int,val codeType:Int,val codeGenre:Int,
                    val codeCompany:Int, val codeRating:Int,val nameProduct:String,val ageRating:Int,
                    val productionCost:Double? = null, val boxOffice:Double? = null) {

    override fun toString(): String {
        return "Название сервиса:$nameProduct\nВозрастной рейтинг:$ageRating$\nСтоимость производства:$productionCost\nСборы:$boxOffice\n\n"
    }
}