package helloworld.demo.com.godrive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shyamramesh on 03/06/18.
 */

public class Feedback extends AppCompatActivity{

    TextView textView;
    EditText editText;
    Button button;
    String s1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        textView = (TextView)findViewById(R.id.txt);
        editText = (EditText) findViewById(R.id.edt);
        button = (Button) findViewById(R.id.btn);

    }

    public void sendFeedback(View view) {

        s1 = editText.getText().toString();
        editText.setText("");

        if(s1.isEmpty()) {

            Toast.makeText(Feedback.this, "Please Enter Feedback", Toast.LENGTH_SHORT).show();

        }
        else {

            Toast.makeText(Feedback.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}
