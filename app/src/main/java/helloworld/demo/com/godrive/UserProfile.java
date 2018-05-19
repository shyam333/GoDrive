package helloworld.demo.com.godrive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by shyamramesh on 16/05/18.
 */

public class UserProfile extends AppCompatActivity{

    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        button = (Button)findViewById(R.id.btn);
    }
}
