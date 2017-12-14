package com.dannextech.apps.eventmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private EventDbQueries query;
    EventTaskDetails [] events;

    //boolean flag to know if the main fab is open or closed state
    private boolean fabExpanded = false;
    private FloatingActionButton fab;

    //linear layout holding the event submenu
    private LinearLayout llfabEvent;
    private LinearLayout llfabTasks;

    private RecyclerView rvEventList,rvTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        llfabEvent = (LinearLayout) findViewById(R.id.fabEventLayout);
        llfabTasks = (LinearLayout) findViewById(R.id.fabTaskLayout);

        query = new EventDbQueries(getApplicationContext());




        Log.e("EventDetails",String.valueOf(query.retrieveEventDetails().length));
        Log.e("TaskDetails",String.valueOf(query.retrieveTaskDetails().length));
        //Step 1: Get a reference to the recycleview
        rvEventList = (RecyclerView) findViewById(R.id.rvEventList);
        rvTaskList = (RecyclerView) findViewById(R.id.rvTaskList);
        //Step 2: Set the recyclerview to have fixed size (optional) - use this setting to improve performance if you know that changes in content do not change the layout size
        rvEventList.setHasFixedSize(true);
        rvTaskList.setHasFixedSize(true);
        //Step 3: Set layout manager
        rvEventList.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        rvTaskList.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        //Step 4: set the Adapter
        rvEventList.setAdapter(new EventAdapter(query.retrieveEventDetails()));
        rvTaskList.setAdapter(new TaskAdapter(query.retrieveTaskDetails()));
        //Step 5: set item animator to DefaultAnimator
        rvEventList.setItemAnimator(new DefaultItemAnimator());
        rvTaskList.setItemAnimator(new DefaultItemAnimator());

        llfabEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddEvent.class));
            }
        });
        llfabTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddTask.class));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fabExpanded == true){
                    closeSubMenuFab();
                }else {
                    openSubMenuFab();
                }
            }
        });
        //to make main fab to collapse at the beginning
        closeSubMenuFab();
    }

    private void closeSubMenuFab() {
        llfabTasks.setVisibility(View.INVISIBLE);
        llfabEvent.setVisibility(View.INVISIBLE);
        //CHANGE MAIN FAB ICON
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fabExpanded = false;
    }

    private void openSubMenuFab() {
        llfabTasks.setVisibility(View.VISIBLE);
        llfabEvent.setVisibility(View.VISIBLE);
        //CHANGE MAIN FAB ICON
        fab.setImageResource(R.drawable.ic_close_black_24dp);
        fabExpanded = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
