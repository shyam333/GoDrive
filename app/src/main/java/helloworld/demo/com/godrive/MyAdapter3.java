package helloworld.demo.com.godrive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder> {

    private List<ListItem>listItem = new ArrayList<>();
    Context context;

    public MyAdapter3(List<ListItem> listItem,Context context) {

        this.listItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ListItem listItems = listItem.get(position);

        holder.title.setText(listItems.getJob());
        holder.category.setText(listItems.getCategory());
        if(listItems.getExpto() != null) {
            holder.experience.setText("Experience:             " + listItems.getExpfrom() + "-" + listItems.getExpto());
        }
        else {
            holder.experience.setText("Experience:             " + listItems.getExpfrom());
        }
        holder.location.setText   ("Location:               "+listItems.getLocation());
        holder.skills.setText     ("KeySkills:              "+listItems.getSkills());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,category,experience,location,skills;

        public MyViewHolder(View v) {
            super(v);

            title = (TextView)v.findViewById(R.id.txt1);
            category = (TextView)v.findViewById(R.id.txt2);
            experience = (TextView)v.findViewById(R.id.txt3);
            location = (TextView)v.findViewById(R.id.txt4);
            skills = (TextView)v.findViewById(R.id.txt5);



        }


    }

}
