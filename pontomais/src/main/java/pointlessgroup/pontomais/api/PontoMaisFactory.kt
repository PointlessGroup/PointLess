package pointlessgroup.pontomais.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PontoMaisFactory {

    fun create(baseUrl: String): PontoMais {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PontoMais::class.java)
    }
}