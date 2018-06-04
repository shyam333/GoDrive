package helloworld.demo.com.godrive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shyamramesh on 20/05/18.
 */

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

    private List<ListItem>listItem = new ArrayList<>();
    Context context;
   // String value2;

    public MyAdapter1(List<ListItem> listItem,Context context) {

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

       // jobid = listItems.getJobid();
       // value2 = listItems.getJobid();

        holder.title.setText(listItems.getJob());
        holder.category.setText(listItems.getCategory());
        if(listItems.getExpto() != null) {
            holder.expfrom.setText("Experience:             " + listItems.getExpfrom() + "-" + listItems.getExpto());
        }
        else {
            holder.expfrom.setText("Experience:             " + listItems.getExpfrom());
        }
        holder.location.setText   ("Location:               "+listItems.getLocation());
        holder.skills.setText     ("KeySkills:              "+listItems.getSkills());


//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString("jobid",jobid);
//        editor.commit();

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,category,expfrom,expto,location,skills,description;

        public MyViewHolder(View v) {
            super(v);

            title = (TextView)v.findViewById(R.id.edt1);
            category = (TextView)v.findViewById(R.id.edt2);
            expfrom = (TextView)v.findViewById(R.id.newusertxt);
            location = (TextView)v.findViewById(R.id.txt4);
            skills = (TextView)v.findViewById(R.id.txt5);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String value1 = title.getText().toString();

                    Intent intent = new Intent(context,JobDetail.class);
                    intent.putExtra("key1",value1);
                    //intent.putExtra("key2",value2);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                }
            });

        }


    }

}
