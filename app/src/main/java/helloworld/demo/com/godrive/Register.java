package helloworld.demo.com.godrive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
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
import java.util.regex.Matcher;

/**
 * Created by shyamramesh on 14/05/18.
 */

public class Register extends AppCompatActivity {

    EditText nam,mail,pass;
    Button button;
    ProgressDialog progressDialog;
    String namecheck,emailcheck,passwordcheck;
    ImageView imageView1,imageView2,imageView3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollregister);

        nam = (EditText)findViewById(R.id.nameedt);
        mail = (EditText)findViewById(R.id.mailedt);
        pass = (EditText)findViewById(R.id.passedt);
        button = (Button)findViewById(R.id.button);
        imageView1 = (ImageView)findViewById(R.id.img1);
        imageView2 = (ImageView)findViewById(R.id.img2);
        imageView3 = (ImageView)findViewById(R.id.img3);
        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validationMethod();

            }
        });

    }

    private void validationMethod() {

        namecheck = nam.getText().toString();
        emailcheck = mail.getText().toString();
        passwordcheck = pass.getText().toString();
        if(namecheck.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
        }
        if(emailcheck.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
        }
        if(passwordcheck.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
        }
        else if(emailcheck.contains("@"))
        {
            registerUser();
        }
        else {
            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser()
    {
        final String s1 = nam.getText().toString();
        final String s2 = mail.getText().toString();
        final String s3 = pass.getText().toString();

        progressDialog.setMessage("Registering User....");
        progressDialog.show();

        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            Log.d("STATUS",status);
                            String statusMessage = jsonObject.getString("status_message");
                            Log.d("STATUS_MESSAGE",statusMessage);

                            if(status.equals("200")){

                                Toast.makeText(getApplicationContext(), statusMessage, Toast.LENGTH_SHORT).show();
                                if(statusMessage.equals("Registration Successful"))
                                startActivity(new Intent(Register.this,Login.class));
                            }
                          //  else if(statusMessage.equals("Username or Email Exists"))
//                            if(status.equals("400"))
//                            {
//                                Toast.makeText(getApplicationContext(),"Already Registered",Toast.LENGTH_SHORT).show();
//                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
            }
        });

        stringRequest.addStringParam("name",s1);
        stringRequest.addStringParam("email",s2);
        stringRequest.addStringParam("pass",s3);

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


}
