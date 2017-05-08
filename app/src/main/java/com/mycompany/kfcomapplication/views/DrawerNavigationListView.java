package com.mycompany.kfcomapplication.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mycompany.kfcomapplication.R;
import com.mycompany.kfcomapplication.adapters.DrawerNavigationListAdapter;
import com.mycompany.kfcomapplication.events.DrawerSectionItemClickedEvent;
import com.mycompany.kfcomapplication.utils.EventBus;

/**
 * Created by jason_000 on 11/29/2016.
 */

public class DrawerNavigationListView extends ListView implements AdapterView.OnItemClickListener {
    public DrawerNavigationListView(Context context) {
        this(context, null);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        DrawerNavigationListAdapter adapter = new DrawerNavigationListAdapter( getContext(), 0);
        adapter.add( getContext().getString(R.string.section_home));
        adapter.add( getContext().getString(R.string.section_locations) );
        adapter.add( getContext().getString(R.string.section_current_members));

        setAdapter( adapter );

        setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getInstance().post( new DrawerSectionItemClickedEvent((String) parent.getItemAtPosition(position)));
    }
}
