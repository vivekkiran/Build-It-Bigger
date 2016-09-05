package com.udacity.gradle.builditbigger;


interface JokeFetchListener {

    void onJokeFetched(String joke, Exception e);
}
