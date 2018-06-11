package helloworld.demo.com.godrive;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shyamramesh on 14/05/18.
 */

public class Login extends AppCompatActivity {


    EditText name,pass;
    Button button;
    ProgressDialog progressDialog;
    String usernamecheck,passwordcheck;
    SharedPreferences mPreferences;
    CheckBox checkBox;
    SharedPreferences.Editor mEditor;
    ImageView imageView1,imageView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_new);


        // newuser = (TextView)findViewById(R.id.newusertxt);
        name = (EditText) findViewById(R.id.emailedt);
        pass = (EditText) findViewById(R.id.passedt);
        button = (Button) findViewById(R.id.logbtn);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        imageView1 = (ImageView)findViewById(R.id.img1);
        imageView2 = (ImageView)findViewById(R.id.img2);
        progressDialog = new ProgressDialog(this);

        mPreferences = getSharedPreferences("helloworld.demo.com.godrive", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        checkedSharedPreferences();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox();
                validationMethod();
                login();
            }
        });
    }

    private void checkBox() {

        if (checkBox.isChecked()) {

            mEditor.putString(getString(R.string.checkbox), "True");
            mEditor.commit();

            String s1 = name.getText().toString();
            mEditor.putString(getString(R.string.name), s1);
            mEditor.commit();

            String s2 = pass.getText().toString();
            mEditor.putString(getString(R.string.name), s2);
            mEditor.commit();

        }
    }

    private void checkedSharedPreferences(){
    String checkbox = mPreferences.getString(getString(R.string.checkbox),"false");
    String s1 = mPreferences.getString(getString(R.string.name),"");
    String s2 = mPreferences.getString(getString(R.string.password),"");
    name.setText(s1);
    pass.setText(s2);
    if(checkbox.equals("True"))
    {
        checkBox.setChecked(true);
    } else {
        checkBox.setChecked(false);
    }
    }


    public void goToRegister(View view) {

        startActivity(new Intent(Login.this,Register.class));
    }


    private void validationMethod() {

        usernamecheck = name.getText().toString();
        passwordcheck = pass.getText().toString();
        if(usernamecheck.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter UserName",Toast.LENGTH_SHORT).show();
        }
        else if(passwordcheck.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
        }

    }

    private void login()
    {
        final String s1 = name.getText().toString();
        final String s2 = pass.getText().toString();

        progressDialog.setMessage("Please Wait....");
        progressDialog.show();

        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            //Converting response to JSON format
                            JSONObject jsonObject = new JSONObject(response);
                            String s = jsonObject.getString("status");

                            //Getting User Id
                            JSONObject dataobject = jsonObject.getJSONObject("data");
                            String canditateid = dataobject.getString("candidateid");
                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Login.this);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("candidateid",canditateid);
                            editor.putString("jobid","173");
                            editor.commit();

                            Log.d("CanditateId: ",canditateid);

                            Log.d("response",response.toString());
                           // if(!jsonObject.getBoolean("status")) {
                            if(!s.equals("400"))
                            {
                                Toast.makeText(getApplicationContext(),"User Login Successful",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                                finish();
                           }
                            else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("status_message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        stringRequest.addStringParam("uname",s1);
        stringRequest.addStringParam("pass",s2);

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


}
