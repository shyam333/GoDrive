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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Created by shyamramesh on 14/05/18.
 */

public class Register extends AppCompatActivity {

    TextView name,email,password;
    EditText nam,mail,pass;
    Button button;
    ProgressDialog progressDialog;
    String namecheck,emailcheck,passwordcheck;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name = (TextView)findViewById(R.id.namtxt);
        email = (TextView)findViewById(R.id.emailtxt);
        password = (TextView)findViewById(R.id.passtxt);
        nam = (EditText)findViewById(R.id.namedt);
        mail = (EditText)findViewById(R.id.mailedt);
        pass = (EditText)findViewById(R.id.passedt);
        button = (Button)findViewById(R.id.button);
        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmail(mail.getText().toString());
                validationMethod();
            }
        });

    }

    private void clearEditText(){

        nam.setText("");
        mail.setText("");
        pass.setText("");

    }

    private void validateEmail(String s) {

        if(s.contains("@"))
        {
           registerUser();
        }
        else {
            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
        }

    }


    private void validationMethod() {

        namecheck = nam.getText().toString();
        emailcheck = mail.getText().toString();
        passwordcheck = pass.getText().toString();
        if(namecheck.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
        }
        else if(emailcheck.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
        }
        else if(passwordcheck.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser()
    {
        final String s1 = nam.getText().toString();
        final String s2 = mail.getText().toString();
        final String s3 = pass.getText().toString();

        progressDialog.setMessage("Registering User....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String s1 = jsonObject.getString("status");
                            Log.d("STATUS:",s1);
                           // String s2 = jsonObject.getString("status_message");
                           // Log.d("STATUS_MESSAGE:",s2);
                           // Log.d("RESPONSE:",response.toString());
                          //  if(s2.equals("User insert")) {
                            if(!s1.equals("400")){

                                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                clearEditText();
                                startActivity(new Intent(Register.this,Login.class));
                                //Toast.makeText(getApplicationContext(), jsonObject.getString("status_message"), Toast.LENGTH_LONG).show();
                            }
//                            else if(s2.equals("Username or Email Exists"))
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
                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String>getParams() throws AuthFailureError{
                Map<String,String>params = new HashMap<>();
                params.put("name",s1);
                params.put("email",s2);
                params.put("pass",s3);
                return params;
            }
        };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


}
