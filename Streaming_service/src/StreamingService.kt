class StreamingService(val nameService:String,val countryService:String,val subscriptionPrice:Double,val numberUsers:Double) {

    override fun toString(): String {
        return "Название сервиса:\t$nameService\nСтрана :\t\t$countryService\nЦена подписки:\t$subscriptionPrice\nЧисло пользователей:\t$numberUsers"
    }
}