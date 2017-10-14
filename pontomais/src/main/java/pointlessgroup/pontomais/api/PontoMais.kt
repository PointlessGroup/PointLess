package pointlessgroup.pontomais.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
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
            @Header("client") userId: String,
            @Body registerRequest: RegisterRequest): Call<RegisterRespoonse>
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
        @SerializedName("redirect_to_login") val redirectToLogin: Boolean = false,
        val meta: Meta? = null)

data class Meta(
        val now: Long = 0,
        val ip: String? = null
)

data class RegisterRequest(
        @SerializedName("time_card") val timeCard: TimeCard,
        @SerializedName("_path") val path: String = "/meu_ponto/registro_de_ponto"
)

data class TimeCard(
        val accuracy: Int,
        @SerializedName("accuracy_method") val accuracyMethod: Boolean = true,
        val address: String,
        val latitude: Double,
        @SerializedName("location_edited") val locationEdited: Boolean = false,
        val longitude: Double,
        @SerializedName("original_address") val originalAddress: String,
        @SerializedName("original_latitude") val originalLatitude: Double,
        @SerializedName("original_longitude") val originalLongitude: Double,
        @Expose @SerializedName("reference_id") val referenceId: Any? = null
)

data class RegisterRespoonse(
        @SerializedName("untreated_time_card") val timeCard: UntreatedTimeCard? = null)

data class UntreatedTimeCard(
        @SerializedName("created_at") val createdAt: String? = null
)
