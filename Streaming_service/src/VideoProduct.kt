class VideoProduct( val codeProduct: Int,val codeService:Int,val codeType:Int,val codeGenre:Int,
                   val codeCompany:Int, val codeRating:Int,val nameProduct:String,val ageRating:String,
                   val productionCost:Double? = null, val boxOffice:Double? = null) {

    override fun toString(): String {
        return "Название сервиса:\t$nameProduct\nВозрастной рейтинг :\t\t$ageRating\nСтоимость производства:\t$productionCost\nСборы:\t$boxOffice"
    }
}