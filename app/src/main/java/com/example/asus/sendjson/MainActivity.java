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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edname, edadd, edmobile;
    Button btnsave, btnbrowse;
    ImageView imgView;
    String server_url = "http://192.168.11.63/Volley/insert.php";
    AlertDialog.Builder builder;
    private static final int RESULT_IMAGE = 1;
    Uri uri;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname = (EditText) findViewById(R.id.editText);
        edadd = (EditText) findViewById(R.id.editText2);
        edmobile = (EditText) findViewById(R.id.editText3);
        btnsave = (Button) findViewById(R.id.button);
        btnbrowse = (Button) findViewById(R.id.button2);
        imgView = (ImageView) findViewById(R.id.imageView);
        builder = new AlertDialog.Builder(MainActivity.this);

        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String [] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        RESULT_IMAGE);
            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == RESULT_IMAGE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/");
                startActivityForResult(intent, RESULT_IMAGE);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_IMAGE && resultCode == RESULT_OK && data!=null){
            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgView.setImageBitmap(bitmap);

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private String imageToString (Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte [] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
    public void uploadFile(){
        final String uname, uemail, umobile;
        uname = edname.getText().toString();
        uemail = edadd.getText().toString();
        umobile = edmobile.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response: " + response);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                edname.setText(" ");
                                edadd.setText(" ");
                                edmobile.setText(" ");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", uname);
                params.put("email", uemail);
                params.put("mobile", umobile);
                params.put("image", imageToString(bitmap));
                return params;
            }
        };
        mySingleton.getmInstance(MainActivity.this).addToRequestque(stringRequest);
    }
}
