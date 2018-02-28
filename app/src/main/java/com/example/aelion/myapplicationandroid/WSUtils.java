package com.example.aelion.myapplicationandroid;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by AELION on 22/02/2018.
 */

public class WSUtils {

    public static String sendGetOkHttpRequest(String url) throws Exception {

        Log.w("tag", "url: " + url);
        OkHttpClient client = new OkHttpClient();
        //Création de la requete
        Request request = new Request.Builder().url(url).build();
        //Executionde la requête
        Response response = client.newCall(request).execute();
        //Analyse du code retour
        if (response.code() != 200) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            //Résultat de la requete.
            //ATTENTION .string() ne peut être appelée qu’une seule fois.

            return response.body().string();

        }
    }

    public static ArrayList<StationBean> getStation() throws Exception {

        String url = "https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse&apiKey=ec294a6634bdf8aa124b835d6c218ce8e2a8f42a";
        Gson gson = new Gson();

        String json = sendGetOkHttpRequest(url);

        ArrayList<StationBean> list = gson.fromJson(json, new TypeToken<ArrayList<StationBean>>() {
        }.getType());

        return list;
    }


}



