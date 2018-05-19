package helloworld.demo.com.godrive;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shyamramesh on 14/05/18.
 */

public class Register extends AppCompatActivity {

    TextView name,email,password;
    EditText nam,mail,pass;
    Button button;
    ProgressDialog progressDialog;

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
                registerUser();
            }
        });
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
                            String s = jsonObject.getString("status");
                            if(!s.equals("400")) {
                                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(), jsonObject.getString("status_message"), Toast.LENGTH_LONG).show();
                            }
                            else {
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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
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
