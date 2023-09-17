package com.gk.diabetesprediction;
import com.android.volley.toolbox.JsonObjectRequest;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView test;
    EditText glc, ins, bmi,pf,age;
    Button btn;
    String URL = "http://52.72.45.73:8000/diabetes_prediction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        glc=findViewById(R.id.glucoseReading);
        ins=findViewById(R.id.insReading);
        bmi=findViewById(R.id.bmiReading);
        pf=findViewById(R.id.pedReading);
        age=findViewById(R.id.ageReading);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("Glucose", glc.getText());
                    requestBody.put("Insulin", ins.getText());
                    requestBody.put("BMI", bmi.getText());
                    requestBody.put("DiabetesPedigreeFunction", pf.getText());
                    requestBody.put("Age", age.getText());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, requestBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    Toast.makeText(MainActivity.this, response.getString("message"), Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("TAG", "onErrorResponse: ");
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                };
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(jsonObjectRequest);
            }
        });

    }


}