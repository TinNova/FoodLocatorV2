package tin.novakovic.foodlocator.di.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tin.novakovic.foodlocator.data.JustEatApi

@Module
class ApiModule {

    @Provides
    @Reusable
    fun providesRetrofit(okHttpClient: OkHttpClient.Builder): JustEatApi =
        Retrofit.Builder()
            .baseUrl("https://uk.api.just-eat.io/")
            .client(okHttpClient
                .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(JustEatApi::class.java)

    @Provides
    @Reusable
    fun providesOkHttpClient(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

}