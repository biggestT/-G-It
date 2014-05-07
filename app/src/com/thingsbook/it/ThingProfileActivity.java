package com.thingsbook.it;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import android.widget.ImageView;

import com.thingsbook.it.Thing;
import com.thingsbook.it.MainActivity;

public class ThingProfileActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.thing);

    // Get the message from the intent
    Bundle b = getIntent().getExtras();
    Thing thing = b.getParcelable(MainActivity.EXTRA_THING);

    this.setTitle(thing.getName());

    ImageView pictureView = (ImageView) findViewById(R.id.thingimage);
    Bitmap thumbnailBitmap = BitmapFactory.decodeFile(thing.getThumbnailUrl());
    pictureView.setImageBitmap(thumbnailBitmap);


  }
}