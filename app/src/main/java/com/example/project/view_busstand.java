package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class view_busstand extends AppCompatActivity implements View.OnClickListener {
    EditText cityname;
    TextView standnumber;
    Button btnfetch;
    String city_name_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_busstand);
        cityname = findViewById(R.id.city_name);
        standnumber = findViewById(R.id.result_tp);
        btnfetch = findViewById(R.id.btn_fetch);
        btnfetch.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_fetch) {
                   city_name_1=cityname.getText().toString();
                    try {
                        getData();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
    }

    private void getData() throws MalformedURLException {
                Uri uri=Uri.parse("https://brannier-splices.000webhostapp.com/php_rest_myapp/api/post/read.php").buildUpon().build();
                URL url=new URL(uri.toString());
                new DoTask().execute(url);

            }

    class DoTask extends AsyncTask<URL,Void,String>{

                @Override
                protected String doInBackground(URL... urls) {
                    URL url=urls[0];
                    String data=null;
                    try {
                         data= NetworkUtils.makeHTTPRequest(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return data;
                }
                @Override
                protected void onPostExecute(String s){
                    try {
                        parseJson(s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                public void parseJson(String data) throws JSONException {
                    JSONObject jsonObject=null;
                    try {
                        jsonObject=new JSONObject(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray standArray=jsonObject.getJSONArray("data");
                    for (int i=0;i<standArray.length();i++){
                        JSONObject stando=standArray.getJSONObject(i);
                        String standn=stando.get("district").toString();
                        if (standn.equals(city_name_1)){
                            String standphone=stando.get("number").toString();
                            standnumber.setText(standphone);
                            break;
                        }
                        else {
                            standnumber.setText("Not found");
                        }
                    }
                }
            }



}