package com.curtesmalteser.fixervolleygson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.curtesmalteser.fixervolleygson.model.FixerCurrencies;
import com.curtesmalteser.fixervolleygson.model.FixerCurrencies.Rates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String ENDPOINT = "https://api.fixer.io/latest";

    private RequestQueue requestQueue;

    private Gson gson;

    private TextView mTvBase;
    private TextView mTvRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvBase = findViewById(R.id.tv_base);
        mTvRates = findViewById(R.id.tv_rates);

        requestQueue = Volley.newRequestQueue(this);

        GsonBuilder gsonBuilder = new GsonBuilder();

        gson = gsonBuilder.create();

        fetchPosts();

    }

    private void fetchPosts() {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, ENDPOINT, null, onCurrenciesLoaded, onCurrenciesError);

        requestQueue.add(jsObjRequest);
    }

    private final Response.Listener<JSONObject> onCurrenciesLoaded = new Response.Listener<JSONObject>() {

        @Override
        public void onResponse(JSONObject response) {
            //mTxtDisplay.setText("Response: " + response.toString());

            List<FixerCurrencies> currencies = Arrays.asList(gson.fromJson(response.toString(), FixerCurrencies.class));





            for (FixerCurrencies fixerCurrencies : currencies) {
                Log.d("FixerResp", fixerCurrencies.getBase() + "\n" +
                        fixerCurrencies.getCurrenciesDate() + "\n");

                mTvBase.setText(fixerCurrencies.getBase() + "\n" +
                        fixerCurrencies.getCurrenciesDate() + "\n");
            }

            List<Rates> rates = null;

            try {
                //rates = Arrays.asList(gson.fromJson(response.get("rates").toString(), Rates.class));
                rates = Arrays.asList(gson.fromJson(response.get("rates").toString(), Rates.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }



            for (Rates rate : rates) {
                String rateString = rate.getAUD() + "\n" +
                        rate.getBGN() + "\n" +
                        rate.getBRL() + "\n" +
                        rate.getCAD() + "\n" +
                        rate.getCHF() + "\n" +
                        rate.getCNY() + "\n" +
                        rate.getCZK() + "\n" +
                        rate.getDKK() + "\n" +
                        rate.getGBP() + "\n" +
                        rate.getHKD() + "\n" +
                        rate.getHRK() + "\n" +
                        rate.getHUF() + "\n" +
                        rate.getIDR() + "\n" +
                        rate.getILS() + "\n" +
                        rate.getINR() + "\n" +
                        rate.getJPY() + "\n" +
                        rate.getKRW() + "\n" +
                        rate.getMXN() + "\n" +
                        rate.getMYR() + "\n" +
                        rate.getNOK() + "\n" +
                        rate.getNZD() + "\n" +
                        rate.getPHP() + "\n" +
                        rate.getPLN() + "\n" +
                        rate.getRON() + "\n" +
                        rate.getRUB() + "\n" +
                        rate.getSEK() + "\n" +
                        rate.getSGD() + "\n" +
                        rate.getTHB() + "\n" +
                        rate.getTRY() + "\n" +
                        rate.getUSD() + "\n" +
                        rate.getZAR();

                Log.d("FixerResp", rateString );


                mTvRates.setText(rateString);
            }

        }
    };

    /*private final Response.Listener<String> onCurrenciesLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            List<FixerCurrencies> currencies = Arrays.asList(gson.fromJson(response, FixerCurrencies.class));
            List<Rates> rates = Arrays.asList(gson.fromJson(response, Rates.class));

            for (FixerCurrencies fixerCurrencies : currencies) {
                Log.d("FixerResp", fixerCurrencies.getBase() + "\n" +
                        fixerCurrencies.getCurrenciesDate() + "\n");
            }

            for (Rates rate : rates) {
                Log.d("FixerResp","\n"+
                        rate.getAUD() + "\n" +
                        rate.getBGN() + "\n" +
                        rate.getBRL() + "\n" +
                        rate.getCAD() + "\n" +
                        rate.getCHF() + "\n" +
                        rate.getCNY() + "\n" +
                        rate.getCZK() + "\n" +
                        rate.getDKK() //+ "\n" +
//                        currency.getGBP() + "\n" +
//                        currency.getHKD() + "\n" +
//                        currency.getHRK() + "\n" +
//                        currency.getHUF() + "\n" +
//                        currency.getIDR() + "\n" +
//                        currency.getILS() + "\n" +
//                        currency.getINR() + "\n" +
//                        currency.getJPY() + "\n" +
//                        currency.getKRW() + "\n" +
//                        currency.getMXN() + "\n" +
//                        currency.getMYR() + "\n" +
//                        currency.getNOK() + "\n" +
//                        currency.getNZD() + "\n" +
//                        currency.getPHP() + "\n" +
//                        currency.getPLN() + "\n" +
//                        currency.getRON() + "\n" +
//                        currency.getRUB() + "\n" +
//                        currency.getSEK() + "\n" +
//                        currency.getSGD() + "\n" +
//                        currency.getTHB() + "\n" +
//                        currency.getTRY() + "\n" +
//                        currency.getUSD() + "\n" +
//                        currency.getZAR()
                );

            }

        }
    };*/

    private final Response.ErrorListener onCurrenciesError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("FixerResp", error.toString());
        }
    };
}
