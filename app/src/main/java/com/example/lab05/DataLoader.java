package com.example.lab05;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class DataLoader extends AsyncTask<String, Void, String> {

    private static InputStream downloadUrl() throws IOException {
        Log.d("DataLoader","downloadUrl() method started");
        URL url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(20000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

    public static String getRateFromECB() throws IOException {
        Log.d("DataLoader","getRateFromECB() method started");
        String rate;
        try (InputStream stream = downloadUrl()) {
            rate = Parser.getRateFromECB(stream);
        }
        return rate;
    }

    protected String doInBackground(String... params) {
        Log.d("DataLoader","doInBackground() method started");
        try {
            return getRateFromECB();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Log.e("DataLoader Error",sw.toString());
            return sw.toString();
        }
    }
}