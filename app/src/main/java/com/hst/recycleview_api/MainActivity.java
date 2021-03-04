package com.hst.recycleview_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    RequestQueue requestQueue;
    Adapter adapter;
    ArrayList<ReportsViewModel> dm = new ArrayList<ReportsViewModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv_service);
        rv.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this,2);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
       Login();

    }

    public void Login() {

        //  lottieprogress.setVisibility(View.VISIBLE);

        // Assigning Activity this to progress dialog.
 /*       progressDialog = new ProgressDialog(getContext());
        // Showing progress dialog at user registration time.
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.setMessage("Please Wait");
        progressDialog.show();
 */       // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://picsum.photos/list",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_SHORT).show();

                        //   lottieprogress.setVisibility(View.GONE);

                        /*                     progressDialog.dismiss();
                         */                     // Matching server responce message to our text.
                        try {
                            JSONArray json = new JSONArray(ServerResponse);

                            if (json != null && json.length() > 0) {
                                for (int i = 0; i < json.length(); i++) {

                                    ReportsViewModel ds = new ReportsViewModel();
                                    JSONObject jsonObject =json.getJSONObject(i);
                                 //   Toast.makeText(MainActivity.this, jsonObject.getString("date"), Toast.LENGTH_SHORT).show();
                                    ds.setAuthor(jsonObject.getString("author"));
                                    ds.setId(jsonObject.getString("id"));

                                    dm.add(ds);
                                    adapter = new Adapter(MainActivity.this, dm);      //ds=model       d=Data
                                    rv.setAdapter(adapter);

                                }
                            }

                            // Finish the current Login activity.
                            // Opening the user profile activity using intent.

                            /*else{
                                String msg = j.getString("msg");
                                // Showing Echo Response Message Coming From Server.
                                Toast.makeText(customer_names.this, msg, Toast.LENGTH_LONG).show();

                            }*/
                        } catch (JSONException e) {
                            //    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "egetMessage", Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Hiding the progress dialog after all task complete.
                        //     Toast.makeText(getContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        /*                   progressDialog.dismiss();
                         */
                        Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        NetworkDialog();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() {
                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.


                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }

    private void NetworkDialog() {
        final Dialog dialogs = new Dialog(MainActivity.this);
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogs.setContentView(R.layout.networkdialog);
        dialogs.setCanceledOnTouchOutside(false);
        Button done = (Button) dialogs.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogs.dismiss();
                Login();

            }
        });
        dialogs.show();
    }

}