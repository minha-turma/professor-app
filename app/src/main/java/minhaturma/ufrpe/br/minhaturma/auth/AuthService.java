package minhaturma.ufrpe.br.minhaturma.auth;

import android.content.Context;
import android.content.SharedPreferences;

import minhaturma.ufrpe.br.minhaturma.commons.MinhaTurma;
import minhaturma.ufrpe.br.minhaturma.students.User;

/**
 * Created by tuliodesouza on 14/05/17.
 */

public class AuthService {

  private static AuthService instance;
  private SharedPreferences mSharedPreferences;

  private AuthService() {
    Context context = MinhaTurma.getContext();
    mSharedPreferences = context.getSharedPreferences(
        "minhaturmakey", context.MODE_PRIVATE);
  }

  public static AuthService getInstance() {
    if (instance == null) {
      instance = new AuthService();
    }
    return instance;
  }

  public boolean isAuthenticated() {
    return mSharedPreferences.getString("access_token", null) != null;
  }

  public String getAccessToken() {
    return mSharedPreferences.getString("access_token", null);
  }

  public void authenticate(User user) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString("access_token", user.getAccess_token());
    editor.commit();
  }

  public void logout() {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.remove("access_token");
    editor.commit();
  }
}
