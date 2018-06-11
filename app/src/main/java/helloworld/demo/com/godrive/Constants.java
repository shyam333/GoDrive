package helloworld.demo.com.godrive;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by shyamramesh on 16/05/18.
 */

public class Constants extends AppCompatActivity {


    //final String s = preferences.getString("candidateid","n/a");


    public static final String URL_REGISTER = "http://www.godigitell.in/dev/hrportal/apiregister.php" ;

    public static final String URL_LOGIN =  "http://www.godigitell.in/dev/hrportal/apilogin.php";

    public static final String URL_JOBS = "http://www.godigitell.in/dev/hrportal/apijobsearch.php";

    public static final String URL_APPLY = "http://www.godigitell.in/dev/hrportal/apijobapply.php";


    public static final String URL_APPLIED_JOBS = "http://www.godigitell.in/dev/hrportal/apijobapplied.php";

    public static final String URL_UPDATE_PROFILE = "http://www.godigitell.in/dev/hrportal/apiupdateprofile.php";

    public static final String URL_RETRIEVE_PROFILE = "http://www.godigitell.in/dev/hrportal/apiretriveprofile.php";






}
