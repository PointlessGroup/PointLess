package pointlessgroup.pontomais.api

import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(JUnit4::class) class PontoMaisTest {

    @Rule @JvmField
    var wiremock = ApiRobot(WireMockRule(options().port(8080).httpsPort(8081), true))

    lateinit var pontoMaisApi: PontoMais

    @Before fun buildApi() {
        val retrofit = Retrofit.Builder().baseUrl("http://localhost:8080").addConverterFactory(GsonConverterFactory.create()).build()
        pontoMaisApi = retrofit.create(PontoMais::class.java)
    }

    @Test fun whenSignIn_shouldCallApiCorrectly() {
        wiremock.stubForAuth()

        val response = pontoMaisApi.signIn(LoginRequest("bruno.lima@empresa.com", "myPassword")).execute()!!

        wiremock.verifyAuth()
        assertEquals("NJOF5OtacUilFHvk_gdNKA", response.body()!!.token)
        assertEquals("T2J-MmqrRXrhCnPJodm7wQ", response.body()!!.clientId)
    }

    @Test fun whenSign_withWrongCredentials_shouldReturnAnError() {
        wiremock.stubForAuthWithLoginError()

        val response = pontoMaisApi.signIn(LoginRequest("spider.man@marvel.com", "uncle ben ;(")).execute()!!

        wiremock.verifyAuth()
        val errorBody = Gson().fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
        assertEquals("Usuário e/ou senha inválidos", errorBody.error!!)
    }

    @Test fun whenRegister_shouldReturnUntreatedTimeCardWithCreatedAt() {
        wiremock.stubForRegister()

        val response = pontoMaisApi.registerTime(
                "bruno.lima@empresa.com",
                "myToken",
                "myId",
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

        wiremock.verifyRegister()
        assertEquals("1507935177", response.body()!!.timeCard!!.createdAt)
    }
}