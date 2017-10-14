package pointlessgroup.pontomais.api

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ApiRobot(private val wiremock: WireMockRule) : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement =
            wiremock.apply(base, description)

    fun stubForAuth() {
        wiremock.stubFor(WireMock.post("/api/auth/sign_in")
                .withHeader("User-Agent", WireMock.equalTo("Mozilla/5.0 (Linux; Android 6.0.1; MotoG3 Build/MOB31K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.106 Mobile Safari/537.36"))
                .withHeader("Content-Type", WireMock.containing("application/json"))
                .withHeader("X-Requested-With", WireMock.equalTo("br.com.pontomais.pontomais"))
                .withRequestBody(WireMock.equalToJson(fromResouces("requests/login.json"), true, true))
                .willReturn(WireMock.aResponse()
                        .withBody(fromResouces("responses/login_success.json"))
                )
        )
    }

    fun stubForAuthWithLoginError() {
        wiremock.stubFor(WireMock.post("/api/auth/sign_in")
                .willReturn(WireMock.aResponse()
                        .withStatus(401)
                        .withBody(fromResouces("responses/login_error.json"))
                )
        )
    }


}