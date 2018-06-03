package helloworld.demo.com.godrive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by shyamramesh on 03/06/18.
 */

public class Faqs extends AppCompatActivity {

    TextView q1,ans1,q2,ans2,q3,ans3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faqs);

        q1 = (TextView)findViewById(R.id.txt1);
        ans1 = (TextView)findViewById(R.id.txt2);
        q2 = (TextView)findViewById(R.id.txt3);
        ans2 = (TextView)findViewById(R.id.txt4);
        q3 = (TextView)findViewById(R.id.txt5);
        ans3 = (TextView)findViewById(R.id.txt6);
    }
}
