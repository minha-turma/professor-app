package minhaturma.ufrpe.br.minhaturma.commons;

import android.app.Application;
import android.content.Context;

/**
 * Created by tuliodesouza
 */

public class MinhaTurma extends Application {

    private static MinhaTurma instance;

    public static MinhaTurma getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
