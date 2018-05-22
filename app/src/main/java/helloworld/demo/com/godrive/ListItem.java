package helloworld.demo.com.godrive;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by shyamramesh on 20/05/18.
 */

public class ListItem extends AppCompatActivity {

    private String job;
    private String category;
    private String expfrom;
    private String expto;
    private String location;
    private String skills;
    private String description;
    //private String imageUrl;


    public ListItem() {
    }

    public ListItem(String job, String category, String expfrom, String expto, String location, String skills, String description) {
        this.job = job;
        this.category = category;
        this.expfrom = expfrom;
        this.expto = expto;
        this.location = location;
        this.skills = skills;
        this.description = description;
    }

    public String getJob() {
        return job;
    }

    public String getCategory() {
        return category;
    }

    public String getExpfrom() {
        return expfrom;
    }

    public String getExpto() {
        return expto;
    }

    public String getLocation() {
        return location;
    }

    public String getSkills() {
        return skills;
    }

    public String getDescription() {
        return description;
    }
}
