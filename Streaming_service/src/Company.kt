class Company(val codeCompany:Int,val nameCompany:String,val companyCountry:String) {
    override fun toString(): String {
        return "Название компании: $nameCompany\nСтрана производителя: $companyCountry\n\n"
    }
}