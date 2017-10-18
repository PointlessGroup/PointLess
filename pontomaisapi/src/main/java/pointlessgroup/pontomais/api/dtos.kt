package pointlessgroup.pontomais.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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

data class RegisterResponse(
        @SerializedName("untreated_time_card") val timeCard: UntreatedTimeCard? = null)

data class UntreatedTimeCard(
        @SerializedName("created_at") val createdAt: String? = null
)
