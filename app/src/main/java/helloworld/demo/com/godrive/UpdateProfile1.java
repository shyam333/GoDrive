package helloworld.demo.com.godrive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by shyamramesh on 24/05/18.
 */

public class UpdateProfile1 extends AppCompatActivity{

    EditText name,contact,location,education,skills,experience;
    Button next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile1);

        name = (EditText)findViewById(R.id.edt1);
        contact = (EditText)findViewById(R.id.edt2);
        location = (EditText)findViewById(R.id.edt3);
        education = (EditText)findViewById(R.id.edt4);
        skills = (EditText)findViewById(R.id.edt5);
        experience = (EditText)findViewById(R.id.edt6);
        next = (Button)findViewById(R.id.btn);

        String s1 = name.getText().toString();
        String s2 = contact.getText().toString();
        String s3 = location.getText().toString();
        String s4 = education.getText().toString();
        String s5 = skills.getText().toString();
        String s6 = experience.getText().toString();


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile1.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",s1);
        editor.putString("contact",s2);
        editor.putString("education",s3);
        editor.putString("skills",s4);
        editor.putString("experience",s5);
        editor.putString("experience",s6);
        editor.commit();



    }

    public void nextPage(View view) {

        startActivity(new Intent(UpdateProfile1.this,UpdateProfile2.class));

    }
}
