package com.surajdevgan.recyclerviewusingvolleyandglide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
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

public class MainActivity extends AppCompatActivity {

    // creating a variable for our array list, adapter class,
    // recycler view, progressbar, nested scroll view
     ArrayList<UserModel> userModalArrayList;
    UserAdapter userRVAdapter;
     RecyclerView userRV;
   //  ProgressBar loadingPB;
    RequestQueue requestQueue;
    UserModel userModel;
    LinearLayoutManager linearLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // creating a new array list.
        userModalArrayList = new ArrayList<>();

        // initializing our views.
        userRV = findViewById(R.id.rcView);
     //   loadingPB = findViewById(R.id.idPBLoading);
        requestQueue = Volley.newRequestQueue(this);


        // calling a method to load our api.
        getDataFromAPI();

    }

    public void getDataFromAPI()
    {

        StringRequest request = new StringRequest(Request.Method.GET, "https://reqres.in/api/users",
                response -> {
                    Log.w("response",response);

                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray("data");

                          for(int i=0; i<jsonArray.length();i++)
                            {
                              JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String Email = jsonObject.getString("email");
                                String ImageUrl = jsonObject.getString("avatar");
                                String FirstName =jsonObject.getString("first_name");
                                String LastName =jsonObject.getString("last_name");
                                userModel = new UserModel(id, Email, FirstName, LastName, ImageUrl);

                                userModalArrayList.add(userModel);


                            }

                        userRVAdapter = new UserAdapter(userModalArrayList, this);
                        linearLayoutManager  = new LinearLayoutManager(this);

                        userRV.setLayoutManager(new LinearLayoutManager(this));

                        userRV.setAdapter(userRVAdapter);






                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();



            }
        });
        requestQueue.add(request);
    }

}
