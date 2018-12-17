package com.justclack.smsapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText number, msg;
    Button sent;
    private final android.content.Context TAG = MainActivity.this;
    RequestQueue requestQueue;
    String url = "https://sendpk.com/api/sms.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.number);
        msg = findViewById(R.id.message);
        sent = findViewById(R.id.sent);
        requestQueue = Volley.newRequestQueue(TAG);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(number.getText())) {
                    number.setError("Required");
                    number.requestFocus();
                } else if (TextUtils.isEmpty(msg.getText())) {
                    msg.setError("Required");
                    msg.requestFocus();
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("response", response);
                            Toast.makeText(TAG, response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("response", error.toString());
                            Toast.makeText(TAG, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {

                            // Creating Map String Params.
                            Map<String, String> params = new HashMap<String, String>();
                            // Adding All values to Params.
                            params.put("username", "");
                            params.put("password", "");
                            params.put("sender", "ANDROID IOS COMPUTER TECHNOLOGY FSD");
                            params.put("mobile", number.getText().toString().trim());
                            params.put("message", msg.getText().toString().trim());
                            return params;
                        }

                    };
                    requestQueue.add(stringRequest);
                    //Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}