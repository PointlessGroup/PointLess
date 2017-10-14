package pointlessgroup.pontomais.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

interface PontoMais {

    private companion object {
        const val USER_AGENT = "User-Agent: Mozilla/5.0 (Linux; Android 6.0.1; MotoG3 Build/MOB31K; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.106 Mobile Safari/537.36"
        const val REQUEST_WITH = "X-Requested-With: br.com.pontomais.pontomais"
    }

    @Headers(USER_AGENT, REQUEST_WITH)
    @POST("api/auth/sign_in")
    fun signIn(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @Headers(USER_AGENT, REQUEST_WITH)
    @POST("api/time_cards/register")
    fun registerTime(@Field("email") email: String, @Field("password") password: String): Call<RegisterRespoonse>
}

data class LoginRequest(
        val email: String,
        val password: String
)

data class LoginResponse(
        val success: String? = null,
        val token: String? = null,
        @SerializedName("client_id") val clientId: String? = null,
        val meta: Meta? = null
)

data class ErrorResponse(
        val error: String? = null,
        val redirectToLogin: Boolean = false,
        val meta: Meta? = null)

data class Meta(
        val now: Long = 0,
        val ip: String? = null
)

data class RegisterRespoonse(
        val untreated_time_card: TimeCard? = null)

data class TimeCard(
        val created_at: String? = null
)
