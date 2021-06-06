class StreamingService(val idService:String,val nameService:String,val countryService:String,val subscriptionPrice:Double,val numberUsers:Double) {

    override fun toString(): String {
        return "Id Сервиса:\t$idService\nНазвание сервиса:\t$nameService\nСтрана :\t\t$countryService\nЦена подписки:\t$subscriptionPrice\nЧисло пользователей:\t$numberUsers"
    }
}