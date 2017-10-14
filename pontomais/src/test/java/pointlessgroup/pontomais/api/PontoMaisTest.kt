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


@RunWith(JUnit4::class)
class PontoMaisTest {

    @Rule
    @JvmField
    var wireMock = WireMockRule(
            options().port(8080).httpsPort(8081), true)

    lateinit var pontoMaisApi: PontoMais

    @Before
    fun buildApi() {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        pontoMaisApi = retrofit.create(PontoMais::class.java)
    }

    @Test
    fun whenSignIn_shouldCallApiCorrectly() {
        wireMock.stubFor(post("/api/auth/sign_in")
                .withHeader("User-Agent", equalTo("Mozilla/5.0 (Linux; Android 6.0.1; MotoG3 Build/MOB31K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.106 Mobile Safari/537.36"))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("X-Requested-With", equalTo("br.com.pontomais.pontomais"))
                .withRequestBody(equalToJson(fromResouces("requests/login.json"), true, true))
                .willReturn(aResponse()
                        .withBody(fromResouces("responses/login_success.json"))
                )
        )

        val response = pontoMaisApi.signIn(LoginRequest("bruno.lima@empresa.com", "myPassword"))
                .execute()!!

        assertEquals("NJOF5OtacUilFHvk_gdNKA", response.body()!!.token)
        assertEquals("T2J-MmqrRXrhCnPJodm7wQ", response.body()!!.clientId)
        verify(exactly(1), postRequestedFor(urlEqualTo("/api/auth/sign_in")))
    }

    @Test
    fun whenSign_withWrongCredentials_should() {
        wireMock.stubFor(post("/api/auth/sign_in")
                .willReturn(aResponse()
                        .withStatus(401)
                        .withBody(fromResouces("responses/login_error.json"))
                )
        )

        val response = pontoMaisApi.signIn(LoginRequest("spider.man@marvel.com", "uncle ben ;("))
                .execute()!!

        val errorBody = Gson().fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
        assertEquals("Usuário e/ou senha inválidos", errorBody.error!!)
        verify(exactly(1), postRequestedFor(urlEqualTo("/api/auth/sign_in")))
    }
}