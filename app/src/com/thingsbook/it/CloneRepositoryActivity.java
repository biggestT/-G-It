package com.thingsbook.it;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.os.Message;

import com.thingsbook.it.NativeGit;

public class CloneRepositoryActivity extends Activity implements Runnable
{

  private String basePath;
  private TextView progressText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.clone);

    // Get the message from the intent
    Intent intent = getIntent();
    basePath = getIntent().getStringExtra(MainActivity.EXTRA_PATH) + "/ptt";
    
    progressText = (TextView) findViewById(R.id.clone_progress);

    if(progressText != null) {
      Thread currentThread = new Thread(this);
      currentThread.start();
    }


  }

  @Override
  public void run() {
      
      deleteDirectory(new File(basePath));
      
      NativeGit.cloneWithProgress("https://github.com/biggestT/project-time-tracker", basePath, threadHandler);
  }


  private Handler threadHandler = new Handler() {
    public void handleMessage(Message msg) {
      progressText.setText(msg.getData().getString("progressText"));
    }
  };

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