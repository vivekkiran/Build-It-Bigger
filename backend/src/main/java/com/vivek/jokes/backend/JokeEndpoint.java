/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.vivek.jokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.jokes.JokeProvider;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokesApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.jokes.vivek.com",
                ownerName = "backend.jokes.vivek.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    /**
     * A simple endpoint to return a random joke
     */
    @ApiMethod(name = "getJoke")
    public JokeBean getJoke() {
        JokeBean response = new JokeBean();
        response.setData(JokeProvider.getJoke());
        return response;
    }

}
