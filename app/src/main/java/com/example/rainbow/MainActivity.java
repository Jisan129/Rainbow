package com.example.rainbow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private CurrentWeather currentWeather=new CurrentWeather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView darksky=findViewById(R.id.legal);
        darksky.setMovementMethod(LinkMovementMethod.getInstance());


        String key="2286dd2204e405b21ad2dec95e789d49";  /* Declaring our variables,secret key,latitude and longitude*/
        String latitude= "24.7471";
        String longitude="90.4203";
        String URL="https://api.darksky.net/forecast/"+ key+"/"+latitude+","+longitude;

        if(isNetworkAvailable()&&isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            //RequestBody requestBody=RequestBody.create(json,JSON)
            Request request = new Request.Builder().url(URL).build();
            //sending our request

            Call call = client.newCall(request);
            //calling the request to get the data
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    try {
                        String JsonData=response.body().string();
                        //declared to reuse code
                        Log.v(TAG, JsonData);
                        if (response.isSuccessful()) {
                           currentWeather= getCurrentDetails(JsonData);
                        } else {
                            alertDialogFragment();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "IO exception ", e);
                    }
                    catch (JSONException e){
                        Log.e(TAG,"Json Exception",e);
                    }

                }
            });

        }
        else {
            Toast.makeText(this,"Network is unavailable",Toast.LENGTH_LONG).show();
        }


    }


    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast =new JSONObject(jsonData);
        JSONObject currently=forecast.getJSONObject("currently");
        //Making JSON object
        String timezone =forecast.getString("timezone");
        Log.i(TAG,"TimeZone: "+timezone);
        CurrentWeather currentWeather=new CurrentWeather();
        currentWeather.setSummary(currently.getString("summary"));
        //currentWeather.setPrecipChance(currently.getDouble("pr"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setLocationLebel("Mymensingh ,Dhaka ,Bangladesh");
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setTimezone(timezone);
       //modified
       // String iconId=currently.getString("icon");
       // getIcon(iconId);


        Log.d(TAG,currentWeather.TimeFormat());
        return currentWeather;
    }

    private boolean isNetworkAvailable() {
        //check network availability if the network is available then
        //this code will run otherwise it does not make sense to run this
        //and crash this

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
        boolean isAvailable = false;

        if (capabilities!= null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                isAvailable = true;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                isAvailable = true;
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                isAvailable = true;
            }
        }
           return isAvailable;
    }


    private void alertDialogFragment() {
        //calling the alertDialog fragment

           AndroidDialogFragment dialogFragment=new AndroidDialogFragment();

           dialogFragment.show(getFragmentManager(),"Error dialog");
    }
}
