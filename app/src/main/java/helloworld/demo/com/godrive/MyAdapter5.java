package helloworld.demo.com.godrive;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter5 extends ArrayAdapter<Pojos2> {


    public MyAdapter5(UpdateProfile1 mainActivity, ArrayList<Pojos2> myList) {
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
                    R.layout.indudtry_spinner_item, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.txt);

        Pojos2 currentItem = getItem(position);

        if (currentItem != null) {

            textViewName.setText(currentItem.getIndustry());
        }

        return convertView;
    }
}
