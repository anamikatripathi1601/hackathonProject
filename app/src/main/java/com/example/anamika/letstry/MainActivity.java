package com.example.anamika.letstry;

import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.anamika.letstry.Model;
import com.example.anamika.letstry.AppController;
import com.example.anamika.letstry.CustomAdapter;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "myTag";
    //urlINitialisarion
    private static final String url = "https://newsapi.org/v1/articles?source=buzzfeed&sortBy=top&apiKey=c7744a7da7cf433c8bdd446c4bc1ffaa";
    private ProgressDialog pDialog;
    private List<Model> modelList = new ArrayList<Model>();
    private ListView listView;
    private CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(this, modelList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        //for facebook share button

       /* Button facebookShareButton = (Button) findViewById(R.id.button2);
        facebookShareButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                ShareDialog shareDialog;
                FacebookSdk.sdkInitialize(MainActivity.this);
                shareDialog = new ShareDialog(act);
                ShareLinkContent linkContent = new ShareLinkContent.Builder().setContentTitle("title").setContentDescription("Description").setContentUrl(Uri.parse(modelList.get(0).getUrlLink())).build();
                shareDialog.show(linkContent);

            }
        });*/




        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();


        JsonObjectRequest firstRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("myTag", response.toString());
                hidePDialog();
                try
                {
                    //jsonResponse = "";
                    JSONArray article = response.getJSONArray("articles");
                    for (int i=0; i<article.length();i++)
                    {
                        JSONObject oneObject = (JSONObject) article.get(i);
                        Model model = new Model();
                        model.setTitleName(oneObject.getString("title"));
                        model.setThumbnailImage(oneObject.getString("urlToImage"));
                        model.setDescription(oneObject.getString("description"));
                        model.setUrlLink(oneObject.getString("url"));
                        modelList.add(model);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("myTag", "Error: "+ error.getMessage());
                hidePDialog();

            }
        });

        AppController.getInstance().addToRequestQueue(firstRequest);

    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), "You clicked on position : " + position + " and id : " + id, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), Description.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("value1", (ArrayList<? extends Parcelable>) modelList);
        bundle.putInt("value2", (int) id);
        i.putExtras(bundle);
        // i.putExtra("Value1", id);
       // i.putParcelableArrayListExtra("Value2", (ArrayList<? extends Parcelable>) modelList);

        startActivity(i);

    }
}
