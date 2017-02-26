package com.example.anamika.letstry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
//        Intent mIntent = getIntent();
////        Bundle extras = getIntent().getIntExtra();
//        int id = mIntent.getIntExtra("Value1",0);
//        ArrayList<Model> modelArrayList = getIntent().getParcelableExtra("Value2");
//        Toast.makeText(getApplicationContext(), "id " + id , Toast.LENGTH_SHORT).show();


        Bundle b = this.getIntent().getExtras();
        ArrayList<Model> modelArrayList = b.getParcelableArrayList("value1");
        int id = b.getInt("value2");
        TextView description = (TextView) findViewById(R.id.description);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        TextView titleName = (TextView) findViewById(R.id.titleSecondActivity);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnailImagae);

       // Log.d("teriTag","url"+ modelArrayList.get(id).getThumbnailImage()+);
       //   thumbNail.setImageUrl(modelArrayList.get(id).getDescription(),imageLoader);

        description.setText(modelArrayList.get(id).getUrlLink());
        titleName.setText(modelArrayList.get(id).getTitleName());
        String url = modelArrayList.get(id).getThumbnailImage();
        TextView url1 = (TextView) findViewById(R.id.url);
        url1.setText(url);


    }

}
