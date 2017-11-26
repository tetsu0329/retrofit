package com.example.asus.sendjson;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText edname, edadd, edmobile;
    Button btnsave, btnbrowse;
    ImageView imgView;
    String server_url = "http://192.168.11.63/Volley/insert.php";
    AlertDialog.Builder builder;
    private static final int RESULT_IMAGE = 1;
    Uri uri;
    Bitmap bitmap;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_screen);
        listView = (ListView) findViewById(R.id.listView);
        btnbrowse = (Button) findViewById(R.id.button3);
        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowScreen.class);
                startActivity(intent);
            }
        });
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsageOkHttpClient();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://10.20.110.10/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        GitHubClient client = retrofit.create(GitHubClient.class);
        Call<List<User>> call = client.reposForUser("insert");

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> repos = response.body();
                listView.setAdapter(new GitHubRepoAdapter(MainActivity.this, repos));
                Toast.makeText(getApplicationContext(), "Success...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong..." + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private String imageToString (Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte [] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
    public void uploadFile(){

    }
}
