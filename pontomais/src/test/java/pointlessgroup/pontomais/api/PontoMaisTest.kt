package pointlessgroup.pontomais.api

import com.github.tomakehurst.wiremock.client.WireMock.*
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
    var wireMock = ApiRobot(WireMockRule(options().port(8080).httpsPort(8081), true))

    lateinit var pontoMaisApi: PontoMais

    @Before fun buildApi() {
        val retrofit = Retrofit.Builder().baseUrl("http://localhost:8080").addConverterFactory(GsonConverterFactory.create()).build()
        pontoMaisApi = retrofit.create(PontoMais::class.java)
    }

    @Test fun whenSignIn_shouldCallApiCorrectly() {
        wireMock.stubForAuth()

        val response = pontoMaisApi.signIn(LoginRequest("bruno.lima@empresa.com", "myPassword")).execute()!!

        assertEquals("NJOF5OtacUilFHvk_gdNKA", response.body()!!.token)
        assertEquals("T2J-MmqrRXrhCnPJodm7wQ", response.body()!!.clientId)
        verify(exactly(1), postRequestedFor(urlEqualTo("/api/auth/sign_in")))
    }

    @Test fun whenSign_withWrongCredentials_shouldReturnAnError() {
        wireMock.stubForAuthWithLoginError()

        val response = pontoMaisApi.signIn(LoginRequest("spider.man@marvel.com", "uncle ben ;(")).execute()!!

        val errorBody = Gson().fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
        assertEquals("Usuário e/ou senha inválidos", errorBody.error!!)
        verify(exactly(1), postRequestedFor(urlEqualTo("/api/auth/sign_in")))
    }

    @Test fun whenRegister_withoutLogin_shouldReturnAnError() {
        
    }
}