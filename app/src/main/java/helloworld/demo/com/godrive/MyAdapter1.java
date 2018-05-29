package helloworld.demo.com.godrive;

import android.content.Context;
import android.content.Intent;
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

    public MyAdapter1(List<ListItem> listItem, Context context) {

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
        holder.expfrom.setText    ("Experience:             "+listItems.getExpfrom()+"-"+listItems.getExpto());
        holder.location.setText   ("Location:               "+listItems.getLocation());
        holder.skills.setText     ("KeySkills:              "+listItems.getSkills());
        holder.description.setText("JobDescription:         "+listItems.getDescription());

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

            title = (TextView)v.findViewById(R.id.txt1);
            category = (TextView)v.findViewById(R.id.txt2);
            expfrom = (TextView)v.findViewById(R.id.txt3);
            location = (TextView)v.findViewById(R.id.txt4);
            skills = (TextView)v.findViewById(R.id.txt5);
            description = (TextView)v.findViewById(R.id.txt6);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String value1 = title.getText().toString();


//                    Intent ieventreport = new Intent(context,JobDetail.class);
//                    context.startActivity(ieventreport);

                    Intent intent = new Intent(context,JobDetail.class);
                    intent.putExtra("key1",value1);
                    context.startActivity(intent);

                    //activity.startActivity(new Intent(activity, NVirementEmmeteur.class));

                    //context.startActivity(new Intent(context,JobDetails.class));

                }
            });

        }


    }

}