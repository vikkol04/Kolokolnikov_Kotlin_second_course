package user
class User10(val name: String) {
    var email = ""
    var isOnline: Boolean? = null

    constructor(name: String, email: String) : this(name) {
        this.email = email
    }

    constructor(name: String, email: String, isOnline: Boolean) : this(name, email) {
        this.isOnline = isOnline
    }
}

object Const{
    val countryCode = "+38"
    val baseEndpoint = "https://www"
}

fun sayHello(){
    println("newUser")
}