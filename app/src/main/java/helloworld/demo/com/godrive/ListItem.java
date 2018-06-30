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
    private String positions;
    private String salaryfrom;
    private String salaryto;
    private String education;
    private String contactperson;
    private String email;
    private String jobid;
    private String name;
    private String contact;
    private String experience;
    private String applieddate;



//private String imageUrl;


    public ListItem(String job, String category, String expfrom, String expto, String location, String skills, String description, String jobid,String applieddate) {

        this.job = job;
        this.category = category;
        this.expfrom = expfrom;
        this.expto = expto;
        this.location = location;
        this.skills = skills;
        this.skills = skills;
        this.description = description;
        this.jobid = jobid;
        this.applieddate = applieddate;
    }





    public ListItem(String job, String category, String expfrom, String expto, String location, String skills, String description, String jobid) {

        this.job = job;
        this.category = category;
        this.expfrom = expfrom;
        this.expto = expto;
        this.location = location;
        this.skills = skills;
        this.skills = skills;
        this.description = description;
        this.jobid = jobid;

    }



    public ListItem(String job, String category, String expfrom, String expto, String positions, String location, String salaryfrom, String salaryto, String description, String skills, String education, String contactperson, String email, String jobid) {
        this.job = job;
        this.category = category;
        this.expfrom = expfrom;
        this.expto = expto;
        this.positions = positions;
        this.location = location;
        this.salaryfrom = salaryfrom;
        this.salaryto = salaryto;
        this.description = description;
        this.skills = skills;
        this.education = education;
        this.contactperson = contactperson;
        this.email = email;
        this.jobid = jobid;
    }



    public String getApplieddate() {
        return applieddate;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getExperience() {
        return experience;
    }

    public String getJobid() {
        return jobid;
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

    public String getPositions() {
        return positions;
    }

    public String getSalaryfrom() {
        return salaryfrom;
    }

    public String getSalaryto() {
        return salaryto;
    }

    public String getContactperson() {
        return contactperson;
    }

    public String getEmail() {
        return email;
    }

    public String getEducation() {
        return education;
    }
}
