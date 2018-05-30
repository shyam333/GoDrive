package helloworld.demo.com.godrive;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by shyamramesh on 25/05/18.
 */

public class Model extends AppCompatActivity {

    public String userid;

    public Model(){

    }

    public Model(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setString(String userid) {

        this.userid = userid;
    }

    public String getString() {

        return userid;
    }
}
