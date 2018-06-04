package helloworld.demo.com.godrive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.List;

/**
 * Created by shyamramesh on 23/05/18.
 */

public class JobDetail extends AppCompatActivity {

    List<ListItem> listItem = new ArrayList<>();
    RecyclerView.Adapter mAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view2);

        loadRecyclerViewData();

        recyclerView = (RecyclerView)findViewById(R.id.rc2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data....!");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.URL_JOBS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            progressDialog.dismiss();

                            Intent intent = getIntent();
                            String jobtitle = intent.getStringExtra("key1");
                           // String jobid = intent.getStringExtra("key2");
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("data");

                            //JSONArray array = new JSONArray(s);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                ListItem item = new ListItem(

                                        o.getString("job_title"),
                                        o.getString("category_name"),
                                        o.getString("experience_from"),
                                        o.getString("experience_to"),
                                        o.getString("no_of_position"),
                                        o.getString("location_name"),
                                        o.getString("budget_from"),
                                        o.getString("budget_to"),
                                        o.getString("job_description"),
                                        o.getString("keyskills"),
                                        o.getString("education"),
                                        o.getString("contact_person"),
                                        o.getString("email_address"),
                                        o.getString("id")
                                );

                                if(o.getString("job_title").equals(jobtitle)) {
                                    listItem.add(item);
                                }
                            }
                            mAdapter = new MyAdapter2(listItem,getApplicationContext());
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}

