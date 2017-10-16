package pointlessgroup.pontomais.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PontoMais {

    private companion object {
        const val USER_AGENT = "User-Agent: Mozilla/5.0 (Linux; Android 6.0.1; MotoG3 Build/MOB31K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.106 Mobile Safari/537.36"
        const val REQUEST_WITH = "X-Requested-With: br.com.pontomais.pontomais"
        const val TOKEN_TYPE = "token-type: Bearer"
    }

    @Headers(USER_AGENT, REQUEST_WITH)
    @POST("api/auth/sign_in")
    fun signIn(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @Headers(USER_AGENT, REQUEST_WITH, TOKEN_TYPE)
    @POST("api/time_cards/register")
    fun registerTime(
            @Header("uid") email: String,
            @Header("access-token") accessToken: String,
            @Header("client") clientId: String,
            @Body registerRequest: RegisterRequest): Call<RegisterRespoonse>
}