package com.thingsbook.it;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.content.Context;
import android.app.ActionBar;
import java.io.File;
import android.os.Environment;

import com.thingsbook.it.LibGit2;



public class MainActivity extends Activity
{

  private static final String TAG = "ItApplication";

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    ActionBar actionBar = getActionBar();
    
    setContentView(R.layout.thing_list);
    final ListView listView = (ListView) findViewById(R.id.listview);
    
    String[] dirnames;

    String folderpath = Environment.getDataDirectory().toString();
    Log.d(TAG, "Root path: " + folderpath);

    File d = new File(folderpath);
    File file[] = d.listFiles();
    if (file.length > 0) {
      Log.d(TAG, "File length: " + file.length);
    }
    
    for (int i=0; i<file.length; i++ ) {
      Log.d(TAG, "FolderName: " + file[i].getName());
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
}

