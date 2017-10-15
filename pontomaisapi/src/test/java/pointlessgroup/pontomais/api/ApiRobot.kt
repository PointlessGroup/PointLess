package pointlessgroup.pontomais.api

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ApiRobot(private val wiremock: WireMockRule) : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement =
            wiremock.apply(base, description)

    fun stubForAuth() {
        wiremock.stubFor(post("/api/auth/sign_in")
                .withHeader("User-Agent", equalTo(USER_AGENT))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("X-Requested-With", equalTo("br.com.pontomais.pontomais"))
                .withRequestBody(equalToJson(fromResouces("requests/login.json"), true, true))
                .willReturn(aResponse()
                        .withBody(fromResouces("responses/login_success.json"))
                )
        )
    }

    fun stubForAuthWithLoginError() {
        wiremock.stubFor(post("/api/auth/sign_in")
                .willReturn(aResponse()
                        .withStatus(401)
                        .withBody(fromResouces("responses/login_error.json"))
                )
        )
    }

    fun stubForRegister() {
        wiremock.stubFor(post("/api/time_cards/register")
                .withHeader("User-Agent", equalTo(USER_AGENT))
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("X-Requested-With", equalTo("br.com.pontomais.pontomais"))
                .withHeader("token-type", equalTo("Bearer"))
                .withHeader("uid", equalTo("bruno.lima@empresa.com"))
                .withHeader("access-token", equalTo("myToken"))
                .withHeader("client", equalTo("myId"))
                .withRequestBody(equalToJson(fromResouces("requests/register.json"), true, true))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(fromResouces("responses/register_success.json"))
                )
        )
    }

    fun stubForError() {
        wiremock.stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(401)
                        .withBody(fromResouces("responses/error_without_login.json"))
                )
        )
    }

    fun verifyAuth() = verify(exactly(1), postRequestedFor(urlEqualTo("/api/auth/sign_in")))

    fun verifyRegister() = verify(exactly(1), postRequestedFor(urlEqualTo("/api/time_cards/register")))

    private companion object {
        const val USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0.1; MotoG3 Build/MOB31K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.106 Mobile Safari/537.36"
    }

}