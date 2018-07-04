package helloworld.demo.com.godrive;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shyamramesh on 22/05/18.
 */

public class JobSearch extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spin1,spin2;
    TextView category,location,experience,renumeration;
    AutoCompleteTextView autoComplete1,autoComplete2;
    String[] jobs,locations;
    Button searchbutton;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobsearch_new);

        category = (TextView)findViewById(R.id.edt1);
        location = (TextView)findViewById(R.id.edt2);
        experience = (TextView)findViewById(R.id.newusertxt);
        renumeration = (TextView)findViewById(R.id.txt4);
        spin1 = (Spinner)findViewById(R.id.spinner1);
        spin2 = (Spinner)findViewById(R.id.spinner2);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        autoComplete1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        autoComplete2 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
        jobs = getResources().getStringArray(R.array.jobnames);
        locations = getResources().getStringArray(R.array.locations);
        searchbutton = (Button)findViewById(R.id.btn);


        toolbar.setTitle("Search Jobs");
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.backarrow);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);

        spin1.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);
        List<String> experience = new ArrayList<>();
        experience.add("0");
        experience.add("1");
        experience.add("2");
        experience.add("3");
        experience.add("4");
        experience.add("5");
        experience.add("6");
        experience.add("7");
        experience.add("8");
        experience.add("9");

        ArrayAdapter<String>dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,experience);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(dataAdapter);
        spin2.setAdapter(dataAdapter);

        ArrayAdapter<String>arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,jobs);
        autoComplete1.setAdapter(arrayAdapter1);

        ArrayAdapter<String>arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,locations);
        autoComplete2.setAdapter(arrayAdapter2);

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String value1 = autoComplete1.getText().toString();
                final String value2 = autoComplete2.getText().toString();
                final String value3 = spin1.getSelectedItem().toString();
                final String value4 = spin2.getSelectedItem().toString();

                Intent intent = new Intent(JobSearch.this,SearchedJobs.class);
                intent.putExtra("key1",value1);
                intent.putExtra("key2",value2);
                intent.putExtra("key3",value3);
                JobSearch.this.startActivity(intent);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        finish();
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
       // Long i = parent.getSelectedItemId();
       // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
