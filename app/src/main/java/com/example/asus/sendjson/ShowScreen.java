package com.example.asus.sendjson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowScreen extends AppCompatActivity {

    public static final String json_url = "http://192.168.11.63/Volley/greetings.php";
    private ProgressDialog dialog;
    private List<User> array = new ArrayList<User>();
    private ListView listView;
    private UserAdapter userAdapter;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_screen);
        listView = (ListView) findViewById(R.id.listView);
        userAdapter = new UserAdapter(this, array, ShowScreen.this);
        listView.setAdapter(userAdapter);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(json_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                hideDialog();
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        User user = new User();
                        user.setName(object.getString("name"));
                        user.setEmail(object.getString("email"));
                        user.setMobile(object.getString("mobile"));
                        user.setImage(object.getString("image"));
                        array.add(user);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
                userAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                error.printStackTrace();
            }
        });
        AppController.getmInstance().addToRequestQueue(jsonArrayRequest);
    }
    public void hideDialog (){
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
