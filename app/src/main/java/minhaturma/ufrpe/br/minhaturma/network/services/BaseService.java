package minhaturma.ufrpe.br.minhaturma.network.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import minhaturma.ufrpe.br.minhaturma.network.interceptor.AuthInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tuliodesouza
 */
public class BaseService {

    private static final String API_URL = "http://3308816c.ngrok.io/";

    protected Retrofit mRetrofit;

    public BaseService() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(API_URL);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        AuthInterceptor authInterceptor = new AuthInterceptor();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(authInterceptor);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement jsonElement, Type type,
                                            JsonDeserializationContext context)
                            throws JsonParseException {

                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

                            Date date = (dateFormat).parse(
                                    jsonElement.getAsJsonPrimitive().getAsString());

                            return date;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        return new Date();
                    }
                })
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                .create();

        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.client(httpClient.build());

        mRetrofit = builder.build();
    }

}
