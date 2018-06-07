package helloworld.demo.com.godrive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shyamramesh on 02/06/18.
 */

public class UpdateProfile3 extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    private int PICK_PDF_REQUEST = 1;
    private Uri filePath;

    ImageView imageView;
    TextView textView,textView2;
    Button gall,choose,save;
    Bitmap bitmap;
    String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile3);

        imageView = (ImageView)findViewById(R.id.img);
        textView = (TextView)findViewById(R.id.txt1);
        textView2 = (TextView)findViewById(R.id.txt2);
        gall = (Button)findViewById(R.id.btn1);
        choose = (Button)findViewById(R.id.btn2);
        save = (Button)findViewById(R.id.btn3);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
        }

    }

    public void uploadProfile(View view) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile3.this);

        final String s1 = preferences.getString("candidateid","n/a");
        //final String s2 = getIntent().getExtras().getString("key4");
        final String s2 = preferences.getString("prf1name","n/a");
        final String s3 = preferences.getString("prf1contact","n/a");
        final String s4 = preferences.getString("prf1location","n/a");
        final String s5 = preferences.getString("prf1education","n/a");
        final String s6 = preferences.getString("prf1skills","n/a");
        final String s7 = preferences.getString("prf1experience","n/a");
        final String s8 = preferences.getString("prf2notice","n/a");
        final String s9 = preferences.getString("prf2negotiable","n/a");
        final String s10 = preferences.getString("prf2ctc","n/a");
        final String s11 = preferences.getString("prf2ectc","n/a");
        final String s12 = preferences.getString("prf2remarks","n/a");

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_UPDATE_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String s = jsonObject.getString("status");
                            Log.d("RESPONSE:",response.toString());
                            if(!s.equals("400")) {
                                Toast.makeText(getApplicationContext(), "Upload Successful", Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(), jsonObject.getString("status_message"), Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Upload Failed",Toast.LENGTH_LONG).show();
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
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String>params = new HashMap<>();

                    params.put("candidate_id", s1);

                params.put("candidate_name",s2);
//                params.put("contact_no",s3);
//                params.put("location_id",s4);
              //  params.put("education",s2);
//                params.put("keyskills",s6);
//                params.put("experience",s7);

             //   params.put("photo",imageToString(bitmap));

             //   path = FilePath.getPath(UpdateProfile3.this,filePath);

             //   params.put("resume",path);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private String imageToString(Bitmap bitmap)
    {
      //  bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

    public void openGallery(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
    }

    public void openFile(View view) {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_PDF_REQUEST);
    }
}