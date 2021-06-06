class StreamingService(val idService:Int,val nameService:String,val countryService:String,val subscriptionPrice:Int,val numberUsers:Double) {

    override fun toString(): String {
        return "Id Сервиса: $idService\nНазвание сервиса: $nameService\nСтрана: $countryService\n" +
                "Цена подписки:$subscriptionPrice\nЧисло пользователей:$numberUsers\n\n"
    }
}