class StreamingService(val idService:Int,val nameService:String,val countryService:String,val subscriptionPrice:Int,val numberUsers:Double) {

    override fun toString(): String {
        return "Id Сервиса:\t$idService\nНазвание сервиса:\t$nameService\nСтрана :\t\t$countryService\nЦена подписки:\t$subscriptionPrice\nЧисло пользователей:\t$numberUsers"
    }
}