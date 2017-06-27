package org.foodrev.androidretrofitexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.foodrev.androidretrofitexample.interfaces.EndpointInterface;
import org.foodrev.androidretrofitexample.pojos.JsonTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String BASE_URL = "http://echo.jsontest.com/"; // trailing slash necessary
    private Retrofit retrofit;
    private EndpointInterface jsonTestApis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init retrofit
        initRetrofit();
        createEndpoint();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                enqueueGetRequests("gskielian");
            }
        });
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private void createEndpoint() {
        jsonTestApis = retrofit.create(EndpointInterface.class);
    }

    private void enqueueGetRequests(String username) {

        Call<JsonTest> call = jsonTestApis.getValue("one");

        call.enqueue(new Callback<JsonTest>() {

            @Override
            public void onResponse(Call<JsonTest> call, Response<JsonTest> response) {
                JsonTest jsonTest = response.body();
                String keyValue = jsonTest.getKey();
                String oneValue = jsonTest.getOne();
                Toast.makeText(MainActivity.this, "keyValue = " + keyValue, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "oneValue = " + oneValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonTest> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());

            }
        });

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
}
