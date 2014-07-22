package com.thingsbook.it;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;
import android.widget.GridView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.content.Context;
import android.app.ActionBar;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import com.thingsbook.it.NativeGit;
import com.thingsbook.it.Thing;
import com.thingsbook.it.ThingsAdapter;
import com.thingsbook.it.ThingProfileActivity;
import com.thingsbook.it.CloneRepositoryActivity;
import com.thingsbook.it.Logger;

public class MainActivity extends Activity
{

  static final String ACTION_VIEW_THING = "com.thingsbook.it.VIEW_THING";
  static final String EXTRA_THING = "com.thingsbook.it.EXTRA_THING";
  static final String EXTRA_PATH = "com.thingsbook.it.EXTRA_PATH";

  private String storagePath;
  private GridView thingsGridView;
  private ThingsAdapter thingsAdapter;
  private ArrayList<Thing> things;
  private File storage;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    Logger.log("Main onCreate");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    things = new ArrayList<Thing>();

    if (isExternalStorageWritable()) {
      storage = getStorageDir();
      
      storagePath = storage.getAbsolutePath();

      thingsGridView = (GridView) findViewById(R.id.thingsgridview);
      thingsAdapter = new ThingsAdapter(this, things);
    
      thingsGridView.setAdapter(thingsAdapter);

      final Context context = this;

      thingsGridView.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
          // Start a new activity that shows the clicked things whole profile
          Intent intent = new Intent(context, ThingProfileActivity.class);
          Thing clickedThing = (Thing) parent.getAdapter().getItem(position);
          intent.putExtra(EXTRA_THING, clickedThing);
          startActivity(intent);
        } 
      });
    
    }
    
  }
  @Override
  public void onResume() {
    Logger.log("Main onResume");
    super.onResume();
    updateThingsList();
    thingsAdapter.notifyDataSetChanged();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return super.onCreateOptionsMenu(menu);
  } 

  // Update thingslist
  private void updateThingsList() {
    File files[] = storage.listFiles();
    things.clear(); 
      for (int i=0; i<files.length; i++ ) {
        if (files[i].isDirectory()){
          things.add(new Thing(files[i]));
        }
      }
  }
  // Called when user intitiates a clone
  public void cloneRepository(MenuItem item) {
  	
  	// Start a a new activity
  	Intent intent = new Intent(this, CloneRepositoryActivity.class);
  	intent.putExtra(EXTRA_PATH, storagePath);
  	startActivity(intent);

    Logger.log("Starting CloneRepositoryActivity");
  }

  public boolean isExternalStorageWritable() {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
      return true;
    }
    return false;
  }

  public File getStorageDir() {
    // Get the directory for the user's public pictures directory.
    File itstorage = new File(Environment.getExternalStorageDirectory(), "it");
    if (!itstorage.isDirectory()){
      itstorage.mkdirs();
    }
    return itstorage;
  }

}

