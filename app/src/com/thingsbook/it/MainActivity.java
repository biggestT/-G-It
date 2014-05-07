package com.thingsbook.it;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;
import android.widget.GridView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.content.Context;
import android.app.ActionBar;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import com.thingsbook.it.LibGit2;
import com.thingsbook.it.Thing;
import com.thingsbook.it.ThingsAdapter;
import com.thingsbook.it.ThingProfileActivity;

public class MainActivity extends Activity
{

  private static final String TAG = "ItApplication";

  static final String ACTION_VIEW_THING = "com.thingsbook.it.VIEW_THING";
  static final String EXTRA_THING = "com.thingsbook.it.EXTRA_THING";

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    ArrayList<Thing> things = new ArrayList<Thing>();

    if (isExternalStorageWritable()) {
      File storage = getStorageDir();
      
      File files[] = storage.listFiles();
      for (int i=0; i<files.length; i++ ) {
        if (files[i].isDirectory()){
          things.add(new Thing(files[i]));
        }
      }

      GridView gridview = (GridView) findViewById(R.id.gridview);
      ThingsAdapter thingsadapter = new ThingsAdapter(this, things);
    
      gridview.setAdapter(thingsadapter);

      final Context context = this;

      gridview.setOnItemClickListener(new OnItemClickListener() {
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
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return super.onCreateOptionsMenu(menu);
  } 

  // Called when user intitiates a clone
  public void cloneRepository(View view) {
  	
  	// Start a a new activity
  	// Intent intent = new Intent(this, CloneRepositoryActivity.class);
  	// EditText editText = (EditText) findViewById(R.id.input_remote_url);
  	// String message = editText.getText().toString();
  	// intent.putExtra(EXTRA_MESSAGE, message);
  	// startActivity(intent);

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

