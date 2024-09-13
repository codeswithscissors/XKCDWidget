/*
 * (c) 2011 Anthony Sandrin
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package dev.codingwithscissors.xkcdwidget;

import android.os.AsyncTask;
import android.util.Log;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetJsonTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {

        String url = params[0];
        String jsonText = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            jsonText = reader.readLine();
        } catch (IOException e) {
            Log.e("XKCD", "Unable to get XKCD Json.", e);
        }

        return jsonText;
    }
}
