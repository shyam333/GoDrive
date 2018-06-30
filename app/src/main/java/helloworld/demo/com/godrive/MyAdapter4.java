package helloworld.demo.com.godrive;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter4 extends ArrayAdapter<Pojos> {


    public MyAdapter4(UpdateProfile1 mainActivity, ArrayList<Pojos> myList) {
        super(mainActivity,0,myList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.location_spinner_item, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.txt);

        Pojos currentItem = getItem(position);

        if (currentItem != null) {

            textViewName.setText(currentItem.getLocation());
        }

        return convertView;
    }
}
