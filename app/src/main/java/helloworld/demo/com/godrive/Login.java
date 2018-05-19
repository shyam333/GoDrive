package helloworld.demo.com.godrive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

/**
 * Created by shyamramesh on 14/05/18.
 */

public class Login extends AppCompatActivity {

    TextView email,password;
    EditText mail,pass;
    Button button;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = (TextView)findViewById(R.id.emailtxt);
        password = (TextView)findViewById(R.id.passtxt);
        mail = (EditText)findViewById(R.id.emailedt);
        pass = (EditText)findViewById(R.id.passedt);
        button = (Button)findViewById(R.id.logbtn);
        progressDialog = new ProgressDialog(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login()
    {
        final String s1 = mail.getText().toString();
        final String s2 = pass.getText().toString();

        progressDialog.setMessage("Please Wait....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            //Converting response to JSON format

                            JSONObject jsonObject = new JSONObject(response);
                            String s = jsonObject.getString("status");
                           // JSONObject dataobject = jsonObject.getJSONObject("data");
                           // String userid = dataobject.getString("userid");
                           // Log.d("userid",userid);
                            Log.d("response",response.toString());
                           // if(!jsonObject.getBoolean("status")) {
                            if(!s.equals("400"))
                            {
                                Toast.makeText(getApplicationContext(),"User Login Successful",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                                finish();
                           }
                            else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("status_message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("uname",s1);
                params.put("pass",s2);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
