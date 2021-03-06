package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.vivek.jokes.backend.jokesApi.JokesApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static JokesApi myApiService = null;
    private JokeFetchListener mListener;
    private Exception mException;

    EndpointsAsyncTask(JokeFetchListener listener) {
        this.mListener = listener;
    }

    @Override
    protected String doInBackground(Void... arg0) {

        if (myApiService == null) {  // Only do this once

            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(BuildConfig.SERVER_URL);

            if (BuildConfig.DEBUG) {
                builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
            }

            myApiService = builder.build();
        }


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            mException = e;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mListener.onJokeFetched(result, mException);
    }
}