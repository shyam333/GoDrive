package helloworld.demo.com.godrive;

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
import android.widget.Button;
import android.widget.EditText;
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

public class UpdateProfile2 extends AppCompatActivity {

    Button next;
    EditText designation, notice, negotiable, ctc, ectc, remarks;
    Toolbar toolbar;
    String strdesig,strnotice,strneg,strctc,strectc,strremarks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile2_new);

        next = (Button) findViewById(R.id.btn);
        designation = (EditText) findViewById(R.id.edt1);
        notice = (EditText) findViewById(R.id.edt2);
        negotiable = (EditText) findViewById(R.id.edt3);
        ctc = (EditText) findViewById(R.id.edt4);
        ectc = (EditText) findViewById(R.id.edt5);
        remarks = (EditText) findViewById(R.id.edt6);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Update Profile");
        Drawable drawable = ContextCompat.getDrawable(UpdateProfile2.this,R.drawable.backarrow);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);

        reteriveProfile();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void uploadProfile(View view) {

      validationmethod();

    }


    private void validationmethod()
    {

        final String s1 = notice.getText().toString();

        final String s2 = negotiable.getText().toString();

        final String s3 = ctc.getText().toString();

        final String s4 = ectc.getText().toString();

        final String s5 = remarks.getText().toString();

        if(s1.isEmpty())
        {
            Toast.makeText(this, "Enter Notice", Toast.LENGTH_SHORT).show();
        }
        else if(s2.isEmpty())
        {
            Toast.makeText(this, "Enter Negotiable", Toast.LENGTH_SHORT).show();
        }
        else if(s3.isEmpty())
        {
            Toast.makeText(this, "Enter Ctc", Toast.LENGTH_SHORT).show();
        }
        else if(s4.isEmpty())
        {
            Toast.makeText(this, "Enter Ectc", Toast.LENGTH_SHORT).show();
        }
        else if(s5.isEmpty())
        {
            Toast.makeText(this, "Enter Remarks", Toast.LENGTH_SHORT).show();
        }

        else if(!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && !s4.isEmpty() && !s5.isEmpty()){

            uploadNow();
        }

    }

    private void uploadNow() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile2.this);
        final String s1 = preferences.getString("candidateid", "n/a");
        final String s2 = preferences.getString("prf1name", "n/a");
        final String s3 = preferences.getString("prf1contact", "n/a");
        final String s4 = preferences.getString("prf1location_id","n/a");
        final String s5 = preferences.getString("prf1industry_id","n/a");
        final String s6 = preferences.getString("prf1education", "n/a");
        final String s7 = preferences.getString("prf1skills", "n/a");
        final String s8 = preferences.getString("prf1experience", "n/a");
        final String s9 = designation.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("prf2designation",s8);
        //designation.setText(preferences.getString("prf2designation","n/a"));
        final String s10 = notice.getText().toString();
        editor.putString("prf2notice",s9);

        final String s11 = negotiable.getText().toString();
        editor.putString("prf2negotiable",s10);

        final String s12 = ctc.getText().toString();
        editor.putString("prf2ctc",s11);

        final String s13 = ectc.getText().toString();
        editor.putString("prf2ectc",s12);

        final String s14 = remarks.getText().toString();
        editor.putString("prf2remarks",s13);
        editor.apply();

        SimpleMultiPartRequest multipartRequest = new SimpleMultiPartRequest(Request.Method.POST,
                Constants.URL_UPDATE_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String s = jsonObject.getString("status");
                            Log.d("RESPONSE:", response.toString());
                            if (!s.equals("400")) {

                                Toast.makeText(getApplicationContext(), "Upload Successful", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(UpdateProfile2.this,Main2Activity.class));
                                //Toast.makeText(getApplicationContext(), jsonObject.getString("status_message"), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_LONG).show();
                            }
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

        multipartRequest.addStringParam("candidate_id", s1);
        multipartRequest.addStringParam("candidate_name", s2);
        multipartRequest.addStringParam("contact_no", s3);
        multipartRequest.addStringParam("location_id", s4);
        multipartRequest.addStringParam("industry_id", s5);
        multipartRequest.addStringParam("education", s6);
        multipartRequest.addStringParam("keyskills", s7);
        multipartRequest.addStringParam("experience", s8);
        multipartRequest.addStringParam("designation", s9);
        multipartRequest.addStringParam("notice_period", s10);
        multipartRequest.addStringParam("np_negotiable", s11);
        multipartRequest.addStringParam("ctc", s12);
        multipartRequest.addStringParam("ectc", s13);
        multipartRequest.addStringParam("remarks", s14);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(multipartRequest);

    }

    private void reteriveProfile() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile2.this);
        final String id = preferences.getString("candidateid", "n/a");

        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.POST,
                Constants.URL_RETRIEVE_PROFILE,
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
                                strdesig = o.getString("designation");
                                strnotice = o.getString("notice_period");
                                strneg = o.getString("np_negotiable");
                                strctc = o.getString("ctc");
                                strectc = o.getString("ectc");
                                strremarks = o.getString("remarks");
                            }

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

        designation.setText(strdesig);
        notice.setText(strnotice);
        negotiable.setText(strneg);
        ctc.setText(strctc);
        ectc.setText(strectc);
        remarks.setText(strremarks);

        removeNullValue();
    }

    private void removeNullValue() {

        if (designation.getText().toString().equals("null")) {
            designation.setText("");
        }
        if (notice.getText().toString().equals("null")) {
            notice.setText("");
        }
        if (negotiable.getText().toString().equals("null")) {
            negotiable.setText("");
        }
        if (ctc.getText().toString().equals("null")) {
            ctc.setText("");
        }
        if (ectc.getText().toString().equals("null")) {
            ectc.setText("");
        }
        if (remarks.getText().toString().equals("null")) {
            remarks.setText("");
        }
    }

//    public void nextPage(View view) {
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile2.this);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("prf2designation",designation.getText().toString());
//        editor.putString("prf2notice",notice.getText().toString());
//        editor.putString("prf2negotiable",negotiable.getText().toString());
//        editor.putString("prf2ctc",ctc.getText().toString());
//        editor.putString("prf2ectc",ectc.getText().toString());
//        editor.putString("prf2remarks",remarks.getText().toString());
//
//        startActivity(new Intent(UpdateProfile2.this,UpdateProfile3.class));
//    }
}
