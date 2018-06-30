package helloworld.demo.com.godrive;

import android.support.v7.app.AppCompatActivity;

public class Pojos extends AppCompatActivity {

    private String location;
    private String locationid;

    public Pojos(String location, String locationid) {
        //this.locationid = locationid;
        this.location = location;
        this.locationid = locationid;
    }

    public Pojos()
    {

    }

    public String getLocation() {
        return location;
    }

    public String getLocationid() {
        return locationid;
    }



}
