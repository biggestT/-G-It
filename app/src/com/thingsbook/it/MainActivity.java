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



public class MainActivity extends Activity
{

  private static final String TAG = "ItApplication";

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    ArrayList<Thing> things = new ArrayList<Thing>();

    if (isExternalStorageWritable()) {
      File storage = getStorageDir();
      Log.d(TAG, "Storage path: " + storage.toString());
      
      File files[] = storage.listFiles();
      for (int i=0; i<files.length; i++ ) {
        if (files[i].isDirectory()){
          Log.d(TAG, "Found dir: " + files[i].getName());
          things.add(new Thing(files[i]));
        }
      }
      Log.d(TAG, "File length: " + files.length);

      GridView gridview = (GridView) findViewById(R.id.gridview);
      ThingsAdapter thingsadapter = new ThingsAdapter(this, things);
    
      Log.d(TAG, "setting adapter ... ");
      if (gridview == null) {
        Log.d(TAG, "gridview is null ... ");
      }
      gridview.setAdapter(thingsadapter);
      Log.d(TAG, "adapter set");

      // gridview.setOnItemClickListener(new OnItemClickListener() {
      //   public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
      //     Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
      //   } 
      // });
    
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

