package pointlessgroup.pontomenos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import pointlessgroup.pontomais.api.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun register(view: View) {
        val pontoMais = PontoMaisFactory.create()
        val response = pontoMais.signIn(LoginRequest(BuildConfig.USER_EMAIL, BuildConfig.USER_PASSWORD))
                .execute()

        if (!response.isSuccessful) {
            Toast.makeText(this, "Eita, deu merda.", Toast.LENGTH_SHORT).show()
            return
        }
        val loginResponse = response.body()!!

        registerRequest(pontoMais, loginResponse)
    }

    private fun registerRequest(pontoMais: PontoMais, loginResponse: LoginResponse) {
        val response = pontoMais.registerTime(BuildConfig.USER_EMAIL, loginResponse.token!!, loginResponse.clientId!!,
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

        val registerRespoonse = response.body()!!
        Toast.makeText(this, "Funcionou " + registerRespoonse.timeCard!!.createdAt, Toast.LENGTH_LONG).show()
    }
}
