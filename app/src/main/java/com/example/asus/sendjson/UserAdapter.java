package com.example.asus.sendjson;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Asus on 11/23/2017.
 */

public class UserAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private Context context;
    private List<User> items;
    ImageLoader imageLoader = AppController.getmInstance().getImageLoader();

    public UserAdapter (Activity activity, List<User> items, Context context){
        this.activity = activity;
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = inflater.inflate(R.layout.row_layout, null);
        }
        imageLoader = AppController.getmInstance().getImageLoader();

        ImageView imageView = view.findViewById(R.id.imageView2);
        TextView textView  = view.findViewById(R.id.textView);
        TextView textView1 = view.findViewById(R.id.textView2);
        TextView textView2 = view.findViewById(R.id.textView3);

        User user = items.get(i);
        textView.setText(user.getName());
        textView1.setText(user.getEmail());
        textView2.setText(user.getMobile());
        String url = "http://192.168.11.63/Volley/" + user.getImage();
        Picasso.with(context).load(url).into(imageView);

        return view;
    }

}
