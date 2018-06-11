package helloworld.demo.com.godrive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by shyamramesh on 24/05/18.
 */

public class UpdateProfile1 extends AppCompatActivity{

    EditText name,contact,location,industry,education,skills,experience;
    Button next;
  //  List<ListItem> listItem = new ArrayList<>();

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


    }

    public void nextPage(View view) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("prf1name",name.getText().toString());
        editor.putString("prf1contact",contact.getText().toString());
        editor.putString("prf1location",location.getText().toString());
        editor.putString("prf1education",education.getText().toString());
        editor.putString("prf1skills",skills.getText().toString());
        editor.putString("prf1experience",experience.getText().toString());
        editor.commit();


        startActivity(new Intent(UpdateProfile1.this,UpdateProfile2.class));


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
