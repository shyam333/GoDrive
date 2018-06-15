package helloworld.demo.com.godrive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shyamramesh on 24/05/18.
 */

public class UpdateProfile1 extends AppCompatActivity{

    EditText name,contact,location,industry,education,skills,experience;
    Button next;
    Toolbar toolbar;
//    String s1,s2,s3,s4,s5,s6;
//    List<ListItem> listItem = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile1);

        name = (EditText)findViewById(R.id.edt1);
        contact = (EditText)findViewById(R.id.edt2);
        location = (EditText)findViewById(R.id.edt3);
        industry = (EditText)findViewById(R.id.edt4);
        education = (EditText)findViewById(R.id.edt5);
        skills = (EditText)findViewById(R.id.edt6);
        experience = (EditText)findViewById(R.id.edt7);
        next = (Button)findViewById(R.id.btn);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        toolbar.setTitle("Update Profile");
        setSupportActionBar(toolbar);


        reteriveProfile();


    }


//    private void reteriveProfile() {
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
//        final String id = preferences.getString("candidateid", "n/a");
//
//        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.GET,
//                Constants.URL_RETRIEVE_PROFILE,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//                        try {
//
//                            JSONObject jsonObject = new JSONObject(s);
//                            JSONArray array = jsonObject.getJSONArray("data");
//
//                            //JSONArray array = new JSONArray(s);
//                            for (int i = 0; i < array.length(); i++) {
//                                JSONObject o = array.getJSONObject(i);
//                                ListItem item = new ListItem(
//                                o.getString("name"),
//                                o.getString("education"),
//                                o.getString("experience"),
//                                o.getString("contact_no"),
//                                o.getString("keyskills")
//                                );
//                                listItem.add(item);
//
//                            }
//
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                });
//        stringRequest.addStringParam("candidate_id", id);
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//    }

    public void nextPage(View view) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("prf1name",name.getText().toString());
        editor.putString("prf1contact",contact.getText().toString());
        editor.putString("prf1location",location.getText().toString());
        editor.putString("prf1industry",industry.getText().toString());
        editor.putString("prf1education",education.getText().toString());
        editor.putString("prf1skills",skills.getText().toString());
        editor.putString("prf1experience",experience.getText().toString());
        editor.apply();

        reteriveProfile();

        startActivity(new Intent(UpdateProfile1.this,UpdateProfile2.class));


    }
    private void reteriveProfile() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        name.setText(preferences.getString("prf1name","n/a"));
        contact.setText(preferences.getString("prf1contact","n/a"));
        location.setText(preferences.getString("prf1location","n/a"));
        industry.setText(preferences.getString("prf1industry","n/a"));
        education.setText(preferences.getString("prf1education","n/a"));
        skills.setText(preferences.getString("prf1skills","n/a"));
        experience.setText(preferences.getString("prf1experience","n/a"));

    }

//    public void uploadProfile(View view) {
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
//        final String s = preferences.getString("candidateid","n/a");
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                Constants.URL_RETRIEVE_PROFILE,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray array = jsonObject.getJSONArray("data");
//                            for (int i = 0; i < array.length(); i++)
//                            {
//                                JSONObject o = array.getJSONObject(i);
//                                ListItem Item = new ListItem(
//
//                                        o.getString("name"),
//                                        o.getString("contactno"),
//                                        o.getString("education"),
//                                        o.getString("keyskills"),
//                                        o.getString("experience")
//
//                                );
//                                listItem.add(Item);
//                            }
//                            if(!s.equals("400")) {
//                                Toast.makeText(getApplicationContext(), "Upload Successful", Toast.LENGTH_LONG).show();
//                                //Toast.makeText(getApplicationContext(), jsonObject.getString("status_message"), Toast.LENGTH_LONG).show();
//                            }
//                            else {
//                                Toast.makeText(getApplicationContext(),"Upload Failed",Toast.LENGTH_LONG).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams() throws AuthFailureError {
//
//                Map<String,String>params = new HashMap<>();
//
//                params.put("candidate_id", s);
//
//                return params;
//            }
//        };
//
//        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
//    }
}
