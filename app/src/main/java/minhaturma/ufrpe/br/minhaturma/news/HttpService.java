package minhaturma.ufrpe.br.minhaturma.news;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, List<News>> {

    private String content = "haha";
    private List<News> listNews;
    private String url;

    //"https://da-recomendacao.herokuapp.com/recomendacao?assunto=ecologia"

    public HttpService(String URL){
        listNews = new ArrayList<>();
        this.url = URL;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected List<News> doInBackground(Void... voids) {

        try {
            String ur = url;
            URL url = new URL(ur);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);

            connection.connect();

            StringBuilder resposta = new StringBuilder();

            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNext()){

                String aux = scanner.next();
                aux = aux + " ";

                //Log.d("Ake","Aux: " + aux);

                resposta.append(aux);

            }

            JSONArray jsonArray = new JSONArray(resposta.toString());
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                News n = new Gson().fromJson(String.valueOf(jsonObj), News.class);

                listNews.add(n);

                Log.d("AKE","JSON: "+n.toString());
            }

            Log.d("Ake","Resposta: " + resposta);
            content = String.valueOf(resposta);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listNews;
    }

}
