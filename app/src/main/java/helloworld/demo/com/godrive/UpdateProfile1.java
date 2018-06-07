package helloworld.demo.com.godrive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
}
