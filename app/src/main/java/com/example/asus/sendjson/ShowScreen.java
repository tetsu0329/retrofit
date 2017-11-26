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

public class ShowScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

    }
}
