package com.example.asus.sendjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Asus on 11/24/2017.
 */

public class GitHubRepoAdapter extends ArrayAdapter<User> {
    private Context context;
    private List<User> values;

    public GitHubRepoAdapter(Context context, List<User> values) {
        super(context, R.layout.activity_show_screen, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
        }
        TextView textView = row.findViewById(R.id.textView);
        TextView textView1 = row.findViewById(R.id.textView2);
        TextView textView2 = row.findViewById(R.id.textView3);
        ImageView imageView = row.findViewById(R.id.imageView2);
        User item = values.get(position);

        textView.setText(item.getName());
        textView1.setText(item.getEmail());
        textView2.setText(item.getMobile());
        String url = "http://10.20.110.10/Volley/" + item.getImage();
        Picasso.with(context).load(url).into(imageView);



        return row;
    }
}
