package com.thingsbook.it;

import java.io.File;

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

public class CloneRepositoryActivity extends Activity implements Runnable
{

  private String basePath;
  private TextView progressText;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.clone);

    // Get the message from the intent
    Intent intent = getIntent();
    basePath = getIntent().getStringExtra(MainActivity.EXTRA_PATH) + "/ptt";
    
    progressText = (TextView) findViewById(R.id.clone_progress_text);
    progressBar = (ProgressBar) findViewById(R.id.clone_progress_bar);
    Drawable progressBarStyle = getResources().getDrawable(R.drawable.progressbar);
    progressBar.setProgressDrawable(progressBarStyle);
    if(progressText != null) {
      Thread currentThread = new Thread(this);
      currentThread.start();
    }


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