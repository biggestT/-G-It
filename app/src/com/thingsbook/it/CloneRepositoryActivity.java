package com.thingsbook.it;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import android.widget.ProgressBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.os.Message;
import android.graphics.drawable.Drawable;

import com.thingsbook.it.NativeGit;
import com.thingsbook.it.Logger;

public class CloneRepositoryActivity extends Activity implements Runnable
{
  public AtomicBoolean isCloning = new AtomicBoolean(false);

  private String basePath;
  private TextView progressText;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.clone);
    Logger.log("cloneRepositoryActivity onCreate");
    // Get the message from the intent
    Intent intent = getIntent();
    basePath = getIntent().getStringExtra(MainActivity.EXTRA_PATH) + "/OD-11";
    
    progressText = (TextView) findViewById(R.id.clone_progress_text);
    progressBar = (ProgressBar) findViewById(R.id.clone_progress_bar);

    Drawable progressBarStyle = getResources().getDrawable(R.drawable.progressbar);
    progressBar.setProgressDrawable(progressBarStyle);


  }
  @Override 
  protected void onStart() {
    super.onStart();
  
    Thread currentThread = new Thread(this);
    isCloning.set(true);
    currentThread.start();
  
    Logger.log("cloneRepositoryActivity onStart");
  }

  @Override
  protected void onStop() {
    super.onStop();
    isCloning.set(false);
    Logger.log("cloneRepositoryActivity onStop");
  }

  @Override
  public void run() {
    deleteDirectory(new File(basePath));
    NativeGit.cloneWithProgress("https://github.com/biggestT/example-product.git", basePath, threadHandler);
    finish();
  }

  private Handler threadHandler = new Handler() {
    public void handleMessage(Message msg) {
      progressText.setText(msg.getData().getString("progressText"));
      progressBar.setProgress(msg.getData().getInt("progressPercent"));
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