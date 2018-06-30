package helloworld.demo.com.godrive;

import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by shyamramesh on 20/05/18.
 */

public class Model1 extends AppCompatActivity {

    private String locationid;
    private String location;


    public Model1(String locationid, String location) {

        this.locationid = locationid;
        this.location = location;
    }


    public String getLocationid() {
        return locationid;
    }

    public String getLocation() {
        return location;
    }


    @Override
    public String toString()
    {
        return location;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Model1)
        {
            Model1 m = (Model1)obj;
            if(m.getLocation().equals(location) && m.getLocationid()==locationid) return true;
        }

        return false;
    }

}