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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder> {

    private List<ListItem>listItem = new ArrayList<>();
    Context context;
   // Date currentTime;


    public MyAdapter3(List<ListItem> listItem,Context context) {

        this.listItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item4,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ListItem listItems = listItem.get(position);
//        Calendar calendar = Calendar.getInstance();
//        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
       // currentTime = Calendar.getInstance().getTime();
        holder.title.setText(listItems.getJob());
        holder.category.setText(listItems.getCategory());
        holder.jobid.setText(listItems.getJobid());
        holder.date.setText("Applied on " + listItems.getApplieddate());

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
       // TextView title,category,experience,location,skills;
        TextView title,category,date,jobid;


        public MyViewHolder(View v) {
            super(v);

            title = (TextView)v.findViewById(R.id.txt1);
            category = (TextView)v.findViewById(R.id.txt2);
            date = (TextView)v.findViewById(R.id.txt3);
            jobid = (TextView)v.findViewById(R.id.txt4);



            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String value1 = jobid.getText().toString();
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("jobid",value1);
                    editor.apply();

                    Intent intent = new Intent(context,JobDetail.class);
                    intent.putExtra("key1",value1);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                }
            });


        }


    }

}
