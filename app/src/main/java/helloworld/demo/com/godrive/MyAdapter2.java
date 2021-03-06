package helloworld.demo.com.godrive;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by shyamramesh on 23/05/18.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    private List<ListItem>listItem = new ArrayList<>();
    Context context;

    public MyAdapter2(List<ListItem> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2,parent,false);

        return new MyAdapter2.MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListItem listItems = listItem.get(position);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("title",listItems.getJob());
        editor.putString("category",listItems.getCategory());
        editor.apply();

        holder.title.setText(listItems.getJob());
        holder.category.setText(listItems.getCategory());
        if(listItems.getExpto() != null) {
            holder.experience.setText(listItems.getExpfrom() + "-" + listItems.getExpto() + "Years");
        }
        else {
            holder.experience.setText(listItems.getExpfrom() + " Years");
        }
        holder.vacancy.setText(listItems.getPositions() + " Vacancies");
        holder.location.setText(listItems.getLocation());
        if(listItems.getSalaryto() != null) {
            holder.salary.setText(listItems.getSalaryfrom() + "-" + listItems.getSalaryto() + " Lakhs per Annum");
        }
        else {
            holder.salary.setText(listItems.getSalaryfrom());
        }
        holder.description.setText(listItems.getDescription());
        holder.skills.setText(listItems.getSkills());
        holder.education.setText(listItems.getEducation());
        holder.contactperson.setText("Preetha Ranganathan");
        holder.email.setText("jobs@godrive.co.in");
       // holder.contactperson.setText(listItems.getContactperson());
       // holder.email.setText(listItems.getEmail());

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static void uploadJobToServer() {
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,category,experience,vacancy,location,salary,description,skills,education,contactperson,email;
      //  Button applybutton;


        public MyViewHolder(View v) {
            super(v);

            title = (TextView)v.findViewById(R.id.job);
            category = (TextView)v.findViewById(R.id.category);
            experience = (TextView)v.findViewById(R.id.edt1);
            vacancy = (TextView)v.findViewById(R.id.edt2);
            location = (TextView)v.findViewById(R.id.newusertxt);
            salary = (TextView)v.findViewById(R.id.txt4);
            description = (TextView) v.findViewById(R.id.txt6);
            skills = (TextView)v.findViewById(R.id.txt8);
            education = (TextView)v.findViewById(R.id.txt13);
            contactperson = (TextView)v.findViewById(R.id.txt15);
            email = (TextView)v.findViewById(R.id.txt16);
           // applybutton = (Button)v.findViewById(R.id.btn);

//            applybutton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    uploadJobToServer();
//
//                }
//            });

        }

//        public static void uploadJobToServer() {
//
//            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//            final String s1 = pref.getString("candidateid","n/a");
//            final String s2 = pref.getString("jobid","n/a");
////            Calendar calendar = Calendar.getInstance();
////            String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
//
//            SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.POST,
//                    Constants.URL_APPLY,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                String s = jsonObject.getString("status");
//                                String statusMessage = jsonObject.getString("status_message");
//
//                                if(s.equals("200")) {
//                                    Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show();
//                                    //Toast.makeText(getApplicationContext(), jsonObject.getString("status_message"), Toast.LENGTH_LONG).show();
//                                }
////                                else {
////                                    Toast.makeText(context,"Already Applied",Toast.LENGTH_SHORT).show();
////                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
//            stringRequest.addStringParam("candidateid",s1);
//            stringRequest.addStringParam("job_id",s2);
//            //stringRequest.addStringParam("currentdate",currentDate);
//
//            RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
//
//
//        }
    }


}
