class StreamingService(val idService:Int,val nameService:String,val countryService:String,val subscriptionPrice:Double,val numberUsers:Double) {

    override fun toString(): String {
        return "Id Сервиса : $idService\nНазвание сервиса : $nameService \nСтрана : $countryService\nЦена подписки : $subscriptionPrice\nЧисло пользователей : $numberUsers\n\n"
    }
}