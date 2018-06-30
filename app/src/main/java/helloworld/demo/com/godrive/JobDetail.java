package helloworld.demo.com.godrive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shyamramesh on 23/05/18.
 */

public class JobDetail extends AppCompatActivity {

    String SHARE_BASE_URL = "http://godrive.co.in/jobdetail.php?&id=";

    List<ListItem> listItem = new ArrayList<>();
    RecyclerView.Adapter mAdapter;
    RecyclerView recyclerView;
    Button button;
    Toolbar toolbar;
    String jobid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view3);

        loadRecyclerViewData();

        button = (Button)findViewById(R.id.btn);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.rc2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setTitle("Job Details");
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.backarrow);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadJobToServer();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data....!");
        progressDialog.show();

        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.GET,
                Constants.URL_JOBS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            progressDialog.dismiss();

                            Intent intent = getIntent();
                            jobid = intent.getStringExtra("key1");

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

                                if(o.getString("id").equals(jobid)) {
                                    listItem.add(item);
                                }
                                mAdapter = new MyAdapter2(listItem,getApplicationContext());
                                recyclerView.setAdapter(mAdapter);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    public  void uploadJobToServer() {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(JobDetail.this);
        final String s1 = pref.getString("candidateid","n/a");
        final String s2 = pref.getString("jobid","n/a");
//            Calendar calendar = Calendar.getInstance();
//            String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.POST,
                Constants.URL_APPLY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String s = jsonObject.getString("status");
                            String statusMessage = jsonObject.getString("status_message");

                            if(s.equals("200")) {
                                Toast.makeText(JobDetail.this, statusMessage, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), jsonObject.getString("status_message"), Toast.LENGTH_LONG).show();
                            }
//                                else {
//                                    Toast.makeText(context,"Already Applied",Toast.LENGTH_SHORT).show();
//                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        stringRequest.addStringParam("candidateid",s1);
        stringRequest.addStringParam("job_id",s2);
        //stringRequest.addStringParam("currentdate",currentDate);

        RequestHandler.getInstance(JobDetail.this).addToRequestQueue(stringRequest);


    }


    public void shareMethod(View view)
    {
       // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(JobDetail.this);
       // String title = preferences.getString("title","n/a");
       // String category = preferences.getString("category","n/a");

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        byte[] data = new byte[0];
        try {
            data = jobid.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data,Base64.DEFAULT);
        Log.d("base64=",base64);
       // String shareBody = "GODRIVE JOBS" + "\n" + "Title: " + title + "\n" + "Category:" + category;
        //String shareBody = "http://godrive.co.in/jobdetail.php?&id=MTIwMw==";
        String shareBody = SHARE_BASE_URL + base64;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(sharingIntent,"Share via"));

    }


}

