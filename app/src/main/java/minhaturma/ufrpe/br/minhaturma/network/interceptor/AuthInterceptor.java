package minhaturma.ufrpe.br.minhaturma.network.interceptor;

import java.io.IOException;

import minhaturma.ufrpe.br.minhaturma.auth.AuthService;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tuliodesouza on 10/06/17.
 */

public class AuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain)
            throws IOException {

        Request request = chain.request();

        AuthService authService = AuthService.getInstance();

        if (authService.isAuthenticated()) {
            request = request.newBuilder()
                    .addHeader("Authorization", authService.getAccessToken())
                    .build();
        }

        Response response = chain.proceed(request);

        return response;
    }
}