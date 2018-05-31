package helloworld.demo.com.godrive;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by shyamramesh on 22/05/18.
 */

public class JobSearch extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    Spinner spin1,spin2;
    TextView category,location,experience,renumeration;
    AutoCompleteTextView autoComplete1,autoComplete2;
    String[] jobs,locations;
    Button searchbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobsearch);

        category = (TextView)findViewById(R.id.txt1);
        location = (TextView)findViewById(R.id.txt2);
        experience = (TextView)findViewById(R.id.newusertxt);
        renumeration = (TextView)findViewById(R.id.txt4);
        spin1 = (Spinner)findViewById(R.id.spinner1);
        spin2 = (Spinner)findViewById(R.id.spinner2);
//        spin3 = (Spinner)findViewById(R.id.spinner3);
//        spin4 = (Spinner)findViewById(R.id.spinner4);
        autoComplete1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        autoComplete2 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
        jobs = getResources().getStringArray(R.array.jobnames);
        locations = getResources().getStringArray(R.array.locations);
        searchbutton = (Button)findViewById(R.id.btn);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.experience_years,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter1);
        spin1.setOnItemSelectedListener(this);
        spin2.setAdapter(adapter1);
        spin2.setOnItemSelectedListener(this);

//        ArrayAdapter<CharSequence>adapter2 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.expected_salary,android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin3.setAdapter(adapter2);
//        spin3.setOnItemSelectedListener(this);
//        spin4.setAdapter(adapter2);
//        spin4.setOnItemSelectedListener(this);

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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
