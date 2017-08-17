package com.example.awagallus.w3d3gitrepos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import gitPro.IndProfil;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    IndProfil indProfil;
    String result = " ";
    @BindView(R.id.tvUser)
    TextView tvUser;
    @BindView(R.id.tvProfile)
    TextView tvProfile;
    @BindView(R.id.tvGitHub)
    TextView tvGitHub;
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.tvCreated)
    TextView tvCreated;


    String img;
    Bitmap bitmap;
    public static final String BASE_URL = "https://api.github.com/users/octocat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Profile");
        ButterKnife.bind(this);
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(BASE_URL).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

            }

            public void onFailure(Call call, IOException e) {
            }


            public void onResponse(Call call, Response response) throws IOException {
                result = response.body().string();
                final Gson gson = new Gson();
                indProfil = gson.fromJson(result, IndProfil.class);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvProfile.setText(IndProfil.getName());
                        tvUser.setText(IndProfil.getLogin());
                        tvCreated.setText(String.valueOf(IndProfil.getCreatedAt()));
                        tvGitHub.setText(IndProfil.getHtmlUrl());
                        img = IndProfil.getAvatarUrl();
                        Log.d(TAG, "onResponse: " + IndProfil.getName()
                                + " " + IndProfil.getAvatarUrl()
                                + " " + IndProfil.getFollowers()
                                + " " + IndProfil.getLogin()
                                + " " + IndProfil.getHtmlUrl());
                    }
                });
            }
        });
        getImageByUrl(img);

    }
    public void getImageByUrl(String url){
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
            ivImage.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToRepos(View view) {
        Intent intent = new Intent(this, ReposActivity.class);
        startActivity(intent);
    }
}