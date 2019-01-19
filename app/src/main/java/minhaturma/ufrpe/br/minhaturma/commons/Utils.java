package minhaturma.ufrpe.br.minhaturma.commons;

import android.content.Context;
import android.content.SharedPreferences;

import minhaturma.ufrpe.br.minhaturma.R;

/**
 * Created by tuliodesouza
 */

public class Utils {

    public static void savePreference(Context context, String key, String value){
        SharedPreferences sharedPref = context.getSharedPreferences(
                "minhaturmakey", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreference(Context context, String key, String defaults){
        SharedPreferences sharedPref = context.getSharedPreferences(
                "minhaturmakey", Context.MODE_PRIVATE);

        return sharedPref.getString(key, defaults);
    }

}
