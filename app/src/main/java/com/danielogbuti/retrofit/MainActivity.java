package com.danielogbuti.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private GitHubAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.pagination_list);

        //This gets the url in which you want to call the api from
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                //This converts to json object
                .addConverterFactory(GsonConverterFactory.create());

        //build the main retrofit object
        Retrofit retrofit = builder.build();

        //calls the interface GitHubClient to obtain the method reposForUser which takes a string as parameter
        GitHubClient client = retrofit.create(GitHubClient.class);
        //gets a list of github repo in an asynchronous thread
        Call<List<GitHubRepo>> call = client.reposForUser("dahnny");

        //
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                //This is called when the connection to the github repo has been established

                Log.i("TAG", "This reached here");
                Log.i("FISH", response.message());
                List<GitHubRepo> repos = response.body();

                adapter = new GitHubAdapter(MainActivity.this, repos);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error Occurred :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
