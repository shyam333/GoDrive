package helloworld.demo.com.godrive;

import android.support.v7.app.AppCompatActivity;

public class Pojos2 extends AppCompatActivity {

    private String industry;
    private String industryid;

    public Pojos2(String industry, String industryid) {
        //this.locationid = locationid;
        this.industry = industry;
        this.industryid = industryid;
    }

    public Pojos2()
    {

    }

    public String getIndustry() {
        return industry;
    }

    public String getIndustryid() {
        return industryid;
    }
}
