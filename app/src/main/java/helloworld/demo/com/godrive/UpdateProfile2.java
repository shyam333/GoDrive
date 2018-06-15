package helloworld.demo.com.godrive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile2);

        next = (Button) findViewById(R.id.btn);
        designation = (EditText) findViewById(R.id.edt1);
        notice = (EditText) findViewById(R.id.edt2);
        negotiable = (EditText) findViewById(R.id.edt3);
        ctc = (EditText) findViewById(R.id.edt4);
        ectc = (EditText) findViewById(R.id.edt5);
        remarks = (EditText) findViewById(R.id.edt6);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Update Profile");
        setSupportActionBar(toolbar);



       reteriveProfile();

    }



    public void uploadProfile(View view) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile2.this);
        final String s1 = preferences.getString("candidateid", "n/a");
        final String s2 = preferences.getString("prf1name", "n/a");
        final String s3 = preferences.getString("prf1contact", "n/a");
        //  final String s4 = preferences.getString("prf1location","n/a");
        final String s5 = preferences.getString("prf1education", "n/a");
        final String s6 = preferences.getString("prf1skills", "n/a");
        final String s7 = preferences.getString("prf1experience", "n/a");
        final String s8 = designation.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("prf2designation",s8);
        designation.setText(preferences.getString("prf2designation","n/a"));
        final String s9 = notice.getText().toString();
        editor.putString("prf2notice",s9);

        final String s10 = negotiable.getText().toString();
        editor.putString("prf2negotiable",s10);

        final String s11 = ctc.getText().toString();
        editor.putString("prf2ctc",s11);

        final String s12 = ectc.getText().toString();
        editor.putString("prf2ectc",s12);

        final String s13 = remarks.getText().toString();
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
        multipartRequest.addStringParam("education", s5);
        multipartRequest.addStringParam("keyskills", s6);
        multipartRequest.addStringParam("experience", s7);
        multipartRequest.addStringParam("designation", s8);
       // designation.setText(s8);
        multipartRequest.addStringParam("notice_period", s9);
        multipartRequest.addStringParam("np_negotiable", s10);
        multipartRequest.addStringParam("ctc", s11);
        multipartRequest.addStringParam("ectc", s12);

        multipartRequest.addStringParam("remarks", s13);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(multipartRequest);

        reteriveProfile();


    }

    private void reteriveProfile() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile2.this);
        designation.setText(preferences.getString("prf2designation","n/a"));
        notice.setText(preferences.getString("prf2notice","n/a"));
        negotiable.setText(preferences.getString("prf2negotiable","n/a"));
        ctc.setText(preferences.getString("prf2ctc","n/a"));
        ectc.setText(preferences.getString("prf2ectc","n/a"));
        remarks.setText(preferences.getString("prf2remarks","n/a"));
    }

//    private void reteriveProfile() {
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile2.this);
//        final String s = preferences.getString("candidateid","n/a");
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading Data....!");
//        progressDialog.show();
//
//
//        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.POST,
//                Constants.URL_APPLIED_JOBS,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            progressDialog.dismiss();
//
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray array = jsonObject.getJSONArray("data");
//
//                            //JSONArray array = new JSONArray(s);
//                            for (int i = 0; i < array.length(); i++) {
//                                JSONObject o = array.getJSONObject(i);
//
//                                        o.getString("name");
//                                        o.getString("education");
//                                        o.getString("experience");
//                                        o.getString("contact_no");
//                                        o.getString("location_name");
//                                        o.getString("keyskills");
//
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//
//        stringRequest.addStringParam("candidateid",s);
//
//
//        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
//    }


   

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
