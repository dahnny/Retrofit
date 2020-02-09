package com.danielogbuti.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {

//    Specify the endpoint as a GET request
    @GET("/users/{user}/repos")
    //@Path("user") takes a parameter to pass to the path of the GET request
    Call<List<GitHubRepo>> reposForUser(@Path("user") String user);
}
