package helloworld.demo.com.godrive;

import android.support.v7.app.AppCompatActivity;

public class Model2 extends AppCompatActivity {

    private String industryid;
    private String industry;

    public Model2(String industryid, String industry) {
        this.industryid = industryid;
        this.industry = industry;
    }

    public Model2() {
    }

    public String getIndustryid() {
        return industryid;
    }

    public String getIndustry() {
        return industry;
    }

    @Override
    public String toString()
    {
        return industry;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Model1)
        {
            Model2 m2 = (Model2)obj;
            if(m2.getIndustry().equals(industry) && m2.getIndustryid()==industryid)

                return true;
        }

        return false;
    }
}
