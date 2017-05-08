package com.mycompany.kfcomapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.mycompany.kfcomapplication.R;
import com.mycompany.kfcomapplication.models.Student;

/**
 * Created by jason_000 on 12/3/2016.
 */

public class MembersAdapter extends ArrayAdapter<Student> {

    public MembersAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if( convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from( getContext() ).inflate( R.layout.view_member_list_item, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.belt = (TextView) convertView.findViewById(R.id.belt);

            convertView.setTag( holder );
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText( getItem( position ).getName() );
        holder.belt.setText(getItem(position).getBelt());

        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView belt;
    }
}
