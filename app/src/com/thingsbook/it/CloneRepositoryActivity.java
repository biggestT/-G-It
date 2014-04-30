package com.thingsbook.it;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;


public class CloneRepositoryActivity extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // Get the message from the intent
    Intent intent = getIntent();
    String remoteUrl = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

    // int status = git_clone()
    // Create the text view
    // TextView textView = new TextView(this);
    // textView.setTextSize(40);
    // textView.setText(message);

    // Set the text view as the activity layout
    // setContentView(textView);

  }

  
  // declare native clone method from libgit2
  // public native int git_clone(git_repository **out, const char *url, const char *local_path, const git_clone_options *options);

}