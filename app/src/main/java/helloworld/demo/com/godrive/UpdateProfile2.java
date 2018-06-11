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

public class UpdateProfile2 extends AppCompatActivity {

    Button next;
    EditText designation,notice,negotiable,ctc,ectc,remarks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile2);

        next = (Button)findViewById(R.id.btn);
        designation = (EditText)findViewById(R.id.edt1);
        notice = (EditText)findViewById(R.id.edt2);
        negotiable = (EditText)findViewById(R.id.edt3);
        ctc = (EditText)findViewById(R.id.edt4);
        ectc = (EditText)findViewById(R.id.edt5);
        remarks = (EditText)findViewById(R.id.edt6);

    }

    public void nextPage(View view) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateProfile2.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("prf2designation",designation.getText().toString());
        editor.putString("prf2notice",notice.getText().toString());
        editor.putString("prf2negotiable",negotiable.getText().toString());
        editor.putString("prf2ctc",ctc.getText().toString());
        editor.putString("prf2ectc",ectc.getText().toString());
        editor.putString("prf2remarks",remarks.getText().toString());

        startActivity(new Intent(UpdateProfile2.this,UpdateProfile3.class));
    }
}
