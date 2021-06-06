class Rate(val codeRating:Int, val kinopoiskRate:Double, val metacriticRate:Double, val rtRate: Double) {
    override fun toString(): String {
        return "Рейтинг на Кинопоиске: $kinopoiskRate\nРейтинг на Metactritic: $metacriticRate\n" +
                "Рейтинг на Rotten Tomatoes: $rtRate\n\n"
    }
}