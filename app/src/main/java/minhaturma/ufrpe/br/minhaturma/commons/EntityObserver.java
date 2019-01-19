package minhaturma.ufrpe.br.minhaturma.commons;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.net.HttpURLConnection;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;


/**
 * Created by tuliodesouza
 */

public abstract class EntityObserver<T> implements Observer<T> {

    private final String GENERAL_ERROR_MESSAGE = "Erro de conexão. Tente novamente mais tarde.";

    IRequest mRequestInterface;

    public EntityObserver(IRequest requestInterface){
        mRequestInterface = requestInterface;
    }

    public EntityObserver(){
        mRequestInterface = null;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mRequestInterface != null) {
            mRequestInterface.onStartRequest();
        }
    }

    @Override
    public void onError(Throwable e) {

        if (mRequestInterface != null) {
            if (e instanceof HttpException) {
                try {
                    ResponseBody responseBody = ((HttpException) e).response().errorBody();
                    String message = responseBody.string();
                    mRequestInterface.onRequestError(message);
                } catch (IOException e1) {
                    mRequestInterface.onRequestError(GENERAL_ERROR_MESSAGE);
                    Log.e("API", "Error WHILE fetching", e);
                }

            } else {
                e.printStackTrace();
                mRequestInterface.onRequestError(GENERAL_ERROR_MESSAGE);
            }
        }

    }

    @Override
    public void onComplete() {
        if (mRequestInterface != null) {
            mRequestInterface.onCompleteRequest();
        }
    }

    @Override
    public abstract void onNext(T value);
}
