package com.example.login_api;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnDel, btnCanl, btnAdd, btnEdt;
    Adapter adt;
    ListView listName;
    TextView tvNamee,tvId,tvName;
    ArrayList<Employee> arrEmpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvId = findViewById(R.id.tvId);
        tvName = findViewById(R.id.tvName);
        tvNamee =findViewById(R.id.tvdisplay);
        btnDel = findViewById(R.id.btnDel);
        btnCanl = findViewById(R.id.btnCanl);
        listName = findViewById(R.id.lsN);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdt = findViewById(R.id.btnEd);

        // Get data từ mocki
        String urlGet="https://60ac73b99e2d6b0017457396.mockapi.io/Employee";
        GetData(urlGet);
        listName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee =arrEmpl.get(position);
                id =employee.getId();
                String name = employee.getName();
                System.setProperty("ID", id+"");
                System.setProperty("Name",name);

                tvNamee.setText(System.getProperty("Name"));
                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String urlDel="https://60ac73b99e2d6b0017457396.mockapi.io/Employee";
                        DeleteAPI(urlDel);
                        GetData(urlGet);
                    }
                });
            }
        });
        btnCanl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    // Add library Volley
    public void GetData(String url) {
        arrEmpl = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrEmpl.add(new Employee(object.getInt("id"), object.getString("name")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adt = new Adapter(getApplicationContext(), arrEmpl, R.layout.item_listview);
                        listName.setAdapter(adt);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Json Object....", Toast.LENGTH_SHORT).show();
            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void DeleteAPI(String url) {
        StringRequest request = new StringRequest(
                Request.Method.DELETE, url+'/'+Integer.parseInt(System.getProperty("ID")),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Successfully Delete", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Faild Delete", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}