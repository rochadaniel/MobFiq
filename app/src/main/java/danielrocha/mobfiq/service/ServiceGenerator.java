package danielrocha.mobfiq.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ServiceGenerator {
    public static final String API_BASE_URL = "https://desafio.mobfiq.com.br/";

    private static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass) {
        retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
