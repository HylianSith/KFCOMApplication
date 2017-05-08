package com.mycompany.kfcomapplication.activities;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mycompany.kfcomapplication.R;
import com.mycompany.kfcomapplication.events.DrawerSectionItemClickedEvent;
import com.mycompany.kfcomapplication.fragments.HomeFragment;
import com.mycompany.kfcomapplication.fragments.LocationFragment;
import com.mycompany.kfcomapplication.fragments.MembersFragment;
import com.mycompany.kfcomapplication.utils.EventBus;
import com.squareup.otto.Subscribe;

public class Home extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String mCurrentFragmentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById( R.id.drawer_layout );

        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                null,
                R.string.drawer_opened,
                R.string.drawer_closed )

        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.drawer_opened);
                    mActionBarDrawerToggle.syncState();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(mCurrentFragmentTitle);
                    mActionBarDrawerToggle.syncState();
            }
        };


        mDrawerLayout.addDrawerListener( mActionBarDrawerToggle );

        displayInitialFragment();
    }

    private void displayInitialFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.getInstance() ).commit();
        mCurrentFragmentTitle = getString(R.string.section_home);
        getSupportActionBar().setTitle(mCurrentFragmentTitle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mActionBarDrawerToggle.onOptionsItemSelected( item ) )
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getInstance().register( this );
    }

    @Override
    protected void onStop() {
        EventBus.getInstance().unregister( this );
        super.onStop();
    }

    @Subscribe
    public void onDrawerSectionItemClickedEvent(DrawerSectionItemClickedEvent event) {
        mDrawerLayout.closeDrawers();

        if(event == null || TextUtils.isEmpty( event.section ) || event.section.equalsIgnoreCase(mCurrentFragmentTitle))
        {
            return;
        }

        if(event.section.equalsIgnoreCase( getString(R.string.section_locations) )){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, LocationFragment.getInstance()).commit();
        } else if (event.section.equalsIgnoreCase( getString(R.string.section_current_members) ))
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MembersFragment.getInstance()).commit();
        } else if (event.section.equalsIgnoreCase( getString(R.string.section_home) ))
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.getInstance()).commit();
        } else {
            return;
        }

        mCurrentFragmentTitle = event.section;
    }
}

