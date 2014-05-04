package com.thingsbook.it;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.ByteArrayOutputStream;


import android.util.Log;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

public class Thing 
{

	private static final String TAG = "ItApplication";

	public String name;
	public Bitmap images[];

	private File root;
	private File imagefiles[];


	// A thing object is initiated by providing the directory
	// where its .git directory is located
	public Thing(File d) {
		root = d;
		name = d.getName();

		// collect thumbnail bitmaps in array
		// ImageFileFilter imageFilter = new ImageFileFilter();
		imagefiles = root.listFiles(new FileFilter() {
			private final String[] okFileExtensions =  new String[] {"jpg", "png", "gif","jpeg"};
			public boolean accept(File file) {
				for (String extension : okFileExtensions) {
					if (file.getName().toLowerCase().endsWith(extension)) {               
						return true;
					}
				}
				return false;
			}
		});

		images = new Bitmap[imagefiles.length];
		for (int i=0; i<imagefiles.length; i++) {
			images[i] = makeThumbnail(imagefiles[i].getAbsolutePath());
		}
	}

	// Downsample larger image files to thumbnail bitmaps
	private Bitmap makeThumbnail(String fileName) {
		// byte[] imageData = null;
    try     
    {
      FileInputStream fis = new FileInputStream(fileName);
      Bitmap imageBitmap = BitmapFactory.decodeStream(fis);
      final int THUMBNAIL_HEIGHT = 300;
      double shrinkFactor = (double) THUMBNAIL_HEIGHT/ imageBitmap.getHeight();
      final int THUMBNAIL_WIDTH = Math.round((float)shrinkFactor*imageBitmap.getWidth());

      imageBitmap = Bitmap.createScaledBitmap(imageBitmap, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, false);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();  
      imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
      return imageBitmap;
      // imageData = baos.toByteArray();
    }
    catch(Exception ex) {
    	return null;
    }
	}
	


}
