package pointlessgroup.pontomais.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PontoMaisFactory {

    fun create(baseUrl: String="http://api.pontomaisweb.com.br"): PontoMais {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PontoMais::class.java)
    }
}