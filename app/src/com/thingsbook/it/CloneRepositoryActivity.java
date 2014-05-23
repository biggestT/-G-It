package com.thingsbook.it;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;

import com.thingsbook.it.LibGit2;

public class CloneRepositoryActivity extends Activity
{

  private String basePath;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.clone);

    // Get the message from the intent
    Intent intent = getIntent();
    basePath = getIntent().getStringExtra(MainActivity.EXTRA_PATH) + "/ptt";
    

  }

  public void doClone(View v) {
    // delete previous test folder
    deleteDirectory(new File(basePath));

    // start cloning
    int status = LibGit2.clone("https://github.com/biggestT/project-time-tracker.git", basePath);
  }
  
  // declare native clone method from libgit2
  // public native int git_clone(git_repository **out, const char *url, const char *local_path, const git_clone_options *options);

  static public boolean deleteDirectory(File path) {
    if( path.exists() ) {
      File[] files = path.listFiles();
      for(int i=0; i<files.length; i++) {
         if(files[i].isDirectory()) {
           deleteDirectory(files[i]);
         }
         else {
           files[i].delete();
         }
      }
    }
    return( path.delete() );
  }
}