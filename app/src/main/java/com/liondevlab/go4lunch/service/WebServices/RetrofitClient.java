package com.liondevlab.go4lunch.service.WebServices;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liondevlab.go4lunch.R;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 18/11/2021
 */
public class RetrofitClient {

	public static final String BASE_URL = String.valueOf(R.string.url_googleapis);
	public static final String PHOTOS_URL = String.valueOf(R.string.url_places_photos);

	public static Retrofit getRetrofitClient() {

			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
			Gson gson = gsonBuilder.create();

			Retrofit.Builder builder = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create(gson));

			HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
					.setLevel(HttpLoggingInterceptor.Level.BODY);

			OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
					.addInterceptor(loggingInterceptor);

			return builder.client(okHttpClient.build()).build();
	}

}
