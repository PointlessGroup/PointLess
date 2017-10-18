package pointlessgroup.pontomenos

import android.os.AsyncTask
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
        RegisterTask(this::showMessage).execute()
    }

    fun showMessage(exception: Exception?) {
        val text = if (exception == null) "Registered with success" else exception.message
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}

private open class RegisterTask(val callback: (Exception?) -> Unit) :
        AsyncTask<Void, Void, Exception?>() {

    val pontoMais = PontoMaisFactory.create()

    override fun doInBackground(vararg params: Void?): Exception? {
        try {
            val loginResponse = registerOnApi()
            val registerRequest = registerRequest(loginResponse)
            return null
        } catch (e: Exception) {
            return e
        }
    }

    override fun onPostExecute(result: Exception?) {
        callback.invoke(result)
    }

    private fun registerOnApi(): LoginResponse {
        val response = pontoMais.signIn(LoginRequest(BuildConfig.USER_EMAIL, BuildConfig.USER_PASSWORD))
                .execute()
        if (!response.isSuccessful)
            throw RuntimeException("Failed to execute login " + response.code())
        return response.body()!!
    }

    private fun registerRequest(loginResponse: LoginResponse): RegisterResponse {
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

        if (!response.isSuccessful)
            throw RuntimeException("Failed to register the point " + response.code())
        return response.body()!!
    }
}
