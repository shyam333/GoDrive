package helloworld.demo.com.godrive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shyamramesh on 24/05/18.
 */

public class UpdateProfile1 extends AppCompatActivity {

    EditText name, contact, location, industry, education, skills, experience;
    Button next;
    Toolbar toolbar;
    String strname, stredu, strexp, strnum, strskills, strlocationid, strindustryid, strlocation, strindustry;
    Spinner spinner1, spinner2;
    String spinnertext1,spinnertext2,value1,value2,reteriveStrlocationid,reteriveStrlocation,reteriveStrindustryid,reteriveStrindustry,locid,indid;
    MyAdapter4 locationAdapter;
    MyAdapter5 industryAdapter;
    ArrayList<Pojos>mylocationList = new ArrayList<>();
    ArrayList<Pojos2>myindustryList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage1_scroll);

        name = (EditText) findViewById(R.id.edt1);
        contact = (EditText) findViewById(R.id.edt2);
        location = (EditText) findViewById(R.id.edt3);
        industry = (EditText) findViewById(R.id.edt4);
        education = (EditText) findViewById(R.id.edt5);
        skills = (EditText) findViewById(R.id.edt6);
        experience = (EditText) findViewById(R.id.edt7);
        next = (Button) findViewById(R.id.btn);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinner1 = (Spinner) findViewById(R.id.spin1);
        spinner2 = (Spinner) findViewById(R.id.spin2);




        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        String index1 = sharedPreferences.getString("locationindex", "0");
        spinner1.setSelection(Integer.parseInt(index1));

        SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        String index2 = sharedPreferences2.getString("industryindex", "0");
        spinner2.setSelection(Integer.parseInt(index2));


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setTitle("Update Profile");
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.backarrow);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);


        //closeKeyboard();
        reteriveAlllocations();
        reteriveAllIndustries();
        reteriveProfile();


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pojos clickedItem = (Pojos) parent.getItemAtPosition(position);
                String clickedLocationName = clickedItem.getLocation();
                locid = clickedItem.getLocationid();

                spinnertext1 = spinner1.getSelectedItem().toString();
                if(!spinnertext1.equals("Ahmedabad"))
                {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
                    value1 = sharedPreferences.getString("locationkey","Location");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pojos2 clickedItem = (Pojos2) parent.getItemAtPosition(position);
                String clickedIndustryName = clickedItem.getIndustry();
                indid = clickedItem.getIndustryid();

                spinnertext2 = spinner2.getSelectedItem().toString();
                if(!spinnertext2.equals("Accounting \\/ Finance"))
                {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
                    value2 = sharedPreferences.getString("industrykey","Industry");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }



    private void reteriveAlllocations() {

        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.GET,
                Constants.URL_ALL_LOCATIONS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            // response = new JSONObject();
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("response",response);
                            JSONArray array = jsonObject.getJSONArray("data");
                            // JSONArray array = new JSONArray("data");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);

                                Pojos pojos = new Pojos(

                                        strlocation = o.getString("location_name"),
                                        strlocationid = o.getString("location_id")

                                );

                                mylocationList.add(pojos);
                                locationAdapter = new MyAdapter4(UpdateProfile1.this,mylocationList);
                                spinner1.setAdapter(locationAdapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void reteriveAllIndustries() {

        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.GET,
                Constants.URL_ALL_INDUSTRIES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            // response = new JSONObject();
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("response",response);
                            JSONArray array = jsonObject.getJSONArray("data");
                            // JSONArray array = new JSONArray("data");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);

                                Pojos2 pojos = new Pojos2(

                                        strindustry = o.getString("industry_name"),
                                        strindustryid = o.getString("industry_id")

                                );

                                myindustryList.add(pojos);
                                industryAdapter = new MyAdapter5(UpdateProfile1.this,myindustryList);
                                spinner2.setAdapter(industryAdapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void nextPage(View view) {

        profilePage1Validation();
        //reteriveProfile();
    }

    private void profilePage1Validation() {

        String s1,s2,s3,s4,s5;

        s1 = name.getText().toString();
        s2 = contact.getText().toString();
        s3 = education.getText().toString();
        s4 = skills.getText().toString();
        s5 = experience.getText().toString();

        if(s1.isEmpty())
        {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
        }
        else if(s2.isEmpty())
        {
            Toast.makeText(this, "Enter Contact Number", Toast.LENGTH_SHORT).show();
        }
        else if(s3.isEmpty())
        {
            Toast.makeText(this, "Enter Educational Qualification", Toast.LENGTH_SHORT).show();
        }
        else if(s4.isEmpty())
        {
            Toast.makeText(this, "Enter KeySkills", Toast.LENGTH_SHORT).show();
        }
        else if(s5.isEmpty())
        {
            Toast.makeText(this, "Enter Experience", Toast.LENGTH_SHORT).show();
        }

       else if(!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && !s4.isEmpty() && !s5.isEmpty()){

            nextPage();
        }
    }

    private void nextPage()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("prf1name", name.getText().toString());
        editor.putString("prf1contact", contact.getText().toString());
        editor.putString("prf1location_id",locid);
        editor.putString("prf1industry_id",indid);
        editor.putString("prf1education", education.getText().toString());
        editor.putString("prf1skills", skills.getText().toString());
        editor.putString("prf1experience", experience.getText().toString());
        editor.apply();

        startActivity(new Intent(UpdateProfile1.this, UpdateProfile2.class));

    }
    private void reteriveProfile() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        final String id = preferences.getString("candidateid", "n/a");

        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.POST,
                Constants.URL_RETRIEVE_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            // response = new JSONObject();
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("response", response);
                            JSONArray array = jsonObject.getJSONArray("data");
                            // JSONArray array = new JSONArray("data");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                strname = o.getString("name");
                                stredu = o.getString("education");
                                strexp = o.getString("experience");
                                strnum = o.getString("contact_no");
                                strskills = o.getString("keyskills");
                                reteriveStrlocationid = o.getString("location_id");
                                reteriveStrlocation = o.getString("location_name");
                                reteriveStrindustryid = o.getString("industry_id");
                                reteriveStrindustry = o.getString("industry_name");

                            }

                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("locationkey",reteriveStrlocation);
                            editor.putString("industrykey",reteriveStrindustry);
                            editor.apply();

                            setLocationSpinnerToValue(spinner1,reteriveStrlocation);
                            setIndustrySpinnerToValue(spinner2,reteriveStrindustry);

                            showMethod();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        stringRequest.addStringParam("candidate_id", id);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showMethod() {

        name.setText(strname);
        education.setText(stredu);
        experience.setText(strexp);
        contact.setText(strnum);
        skills.setText(strskills);

        //  setSpinnerToValue(spinner1,strlocation);
        //  setSpinnerToValue(spinner1,strlocation);
        //  selectedposition = dataAdapter.getPosition();
        // Model1 location = (Model1) spinner1.getSelectedItem();
        //int spinnerposition = dataAdapter.getPosition(strlocation);
        //spinner1.setSelection(spinnerposition);
//        spinner1.setAdapter(dataAdapter);
        //spinner1.setSelection(Integer.parseInt(strlocation));
//        spinner2.setAdapter(dataAdapter2);
        //   spinner2.setSelection(Integer.parseInt(strindustry));
        removeNullValue();
    }

    private void removeNullValue() {

        if (name.getText().toString().equals("null")) {
            name.setText("");
        }
        if (education.getText().toString().equals("null")) {
            education.setText("");
        }
        if (experience.getText().toString().equals("null")) {
            experience.setText("");
        }
        if (contact.getText().toString().equals("null")) {
            contact.setText("");
        }
        if (skills.getText().toString().equals("null")) {
            skills.setText("");
        }

    }

    public void setLocationSpinnerToValue(Spinner spinner, String value) {

        int index = 0;

        for (int i = 0; i<mylocationList.size(); i++) {
            if(mylocationList.get(i).getLocation().equals(value)) {
                index = i;

            }
        }
        spinner.setSelection(index);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("locationindex", String.valueOf(index));
        editor.apply();

    }

    public void setIndustrySpinnerToValue(Spinner spinner, String value) {

        int index = 0;

        for (int i = 0; i<myindustryList.size(); i++) {
            if(myindustryList.get(i).getIndustry().equals(value)) {
                index = i;

            }
        }
        spinner.setSelection(index);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("industryindex", String.valueOf(index));
        editor.apply();

    }




}





