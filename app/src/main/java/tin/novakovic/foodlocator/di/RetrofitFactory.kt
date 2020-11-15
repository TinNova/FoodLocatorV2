package tin.novakovic.foodlocator.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tin.novakovic.foodlocator.data.JustEatApi

object RetrofitFactory {

    fun makeRetrofitService(): JustEatApi {
        val okHttpClient = makeOkHttpClient()
        val retrofit = makeRetrofit(okHttpClient)

        return retrofit.create(JustEatApi::class.java)
    }

    private fun makeRetrofit(okHttpClient: OkHttpClient.Builder): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://uk.api.just-eat.io/")
            .client(okHttpClient
                .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private fun makeOkHttpClient(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

}