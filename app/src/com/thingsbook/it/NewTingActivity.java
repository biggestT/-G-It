package com.thingsbook.it;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;

// camera shit
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.widget.ImageView;
import android.widget.EditText;
import android.view.View;

import com.thingsbook.it.Logger;
import com.thingsbook.it.TingApp;
import com.thingsbook.it.Thing;

public class NewTingActivity extends Activity {

  static final int REQUEST_IMAGE_CAPTURE = 1;

	private ImageView tingImageView;
	private EditText tingTagsView;
	private TingApp myApp;
	private Intent takePictureIntent;
	private File imageFile, tempTingDir;
	private Thing tempTing;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logger.log("oncreate in NewTingActivity");
		setContentView(R.layout.new_ting);

		tingImageView = (ImageView) findViewById(R.id.ting_image);
		tingTagsView = (EditText) findViewById(R.id.ting_tags);

		myApp = (TingApp) getApplicationContext();

		// start of by requesting an image from a camera application
		takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
    	imageFile = null;
    	try {
    		imageFile = createTempImageFile();
    	} catch (IOException ex) {
    		Logger.log(ex);
    	}
    	if (imageFile != null) {
    		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
    		startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);	
    	}
    }

	}

	@Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
    	tempTing = new Thing(tempTingDir);
      tempTing.setImageViewImage(tingImageView);
      tingImageView.setRotation(90.0f);
    }
  }

  public void createTing(View v) {
  	String tingInfo = tingTagsView.getText().toString();
  	Logger.log(tingInfo);
 	  makePermanentTing(tingInfo.split("\\s", 1)[0]); // get first word in info string
 	  finish();
  }

  private File createTempImageFile() throws IOException {

  	tempTingDir = new File(myApp.getStorageDir(), "temp");
  	tempTingDir.mkdirs();
  	File tempImage = File.createTempFile(
        "profile",  /* prefix */
        ".jpg",     /* suffix */
        tempTingDir      /* directory */
    );
    return tempImage;
  }


  private void makePermanentTing(String tingName) {

    // Create a permanent folder for this new Ting
    tempTingDir.renameTo(new File(tingName));

  }
}