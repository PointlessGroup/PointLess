package pointlessgroup.pontomais.cli

import pointlessgroup.pontomais.api.*

fun main(args: Array<String>) {
    register(args[0], args[1])
}

fun register(email: String, password: String) {
    val pontoMais = PontoMaisFactory.create("https://api.pontomaisweb.com.br")
    val response = pontoMais.signIn(LoginRequest(email, password))
            .execute()

    if (!response.isSuccessful) {
        println("Deu merda")
        return
    }
    val loginResponse = response.body()!!
    println("Login with success " + loginResponse)

    registerRequest(email, pontoMais, loginResponse)
}

private fun registerRequest(email: String, pontoMais: PontoMais, loginResponse: LoginResponse) {
    val response = pontoMais.registerTime(email, loginResponse.token!!, loginResponse.clientId!!,
            RegisterRequest(TimeCard(
                    600,
                    true,
                    "Av. das Nações Unidas, 11541 - Cidade Monções, São Paulo - SP, Brasil",
                    -23.6015797,
                    false,
                    -46.694767,
                    "Av. das Nações Unidas, 11541 - Cidade Monções, São Paulo - SP, Brasil",
                    -23.6015797,
                    -46.694767
            ))).execute()

    println("status " + response.code())
    val registerRespoonse = response.body()!!
    println("Funcionou " + registerRespoonse)
//    println("Funcionou " + registerRespoonse.timeCard!!.createdAt)
}
