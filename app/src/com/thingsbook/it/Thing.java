package com.thingsbook.it;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.ByteArrayOutputStream;

import android.util.Log;
import android.content.Context;
import android.os.Parcel; 
import android.os.Parcelable; 
import android.util.Log;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.thingsbook.it.Logger;

public class Thing implements Parcelable
{

	private static final String TAG = "ItApplication";

	private String path;
	private String name;
	private String thumbnailUrl;

	// A thing object is initiated by providing the directory
	// where its .git directory is located
	public Thing(File d) {

		path = d.getAbsolutePath();
		name = d.getName();

		// make thumbnail out of the first found image file
		File imagefiles[] = d.listFiles(new FileFilter() {
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

		thumbnailUrl = (imagefiles.length>0) ? makeThumbnail(imagefiles[0].getAbsolutePath()) : null;
	}

	// constructor for the parcel interface
	public Thing(Parcel in) {
		readFromParcel(in);
	}

	// public get/set methods
	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}
	public String getName() {
		return this.name;
	}
	public String getFolderPath() {
		return this.path;
	}


	// METHODS NECCESSARY FOR THE PARCELABLE INTERFACE
	public static final Parcelable.Creator CREATOR =
    new Parcelable.Creator() {
      public Thing createFromParcel(Parcel in) {
        return new Thing(in);
      }

      public Thing[] newArray(int size) {
        return new Thing[size];
      }
    };
  

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel parcel, int flags) { 
		parcel.writeString(path);
		parcel.writeString(thumbnailUrl);
		parcel.writeString(name);
  } 

  private void readFromParcel(Parcel in) {
		path = in.readString();
		thumbnailUrl = in.readString();
		name = in.readString();
	}

	public void setImageViewImage(final ImageView iv) {
		if (thumbnailUrl != null) {
			Bitmap thumbnailBitmap = BitmapFactory.decodeFile(thumbnailUrl);
			iv.setImageBitmap(thumbnailBitmap);
		}
		else {
			iv.setImageResource(R.drawable.placeholder);
		}
		iv.setBackgroundResource(android.R.color.transparent);

	}

	// Downsample larger image files to thumbnail bitmap and return URL
	private String makeThumbnail(String fileName) {

    try  {
      FileInputStream fis = new FileInputStream(fileName);
      Bitmap imageBitmap = BitmapFactory.decodeStream(fis);
      final int THUMBNAIL_HEIGHT = 400;
      double shrinkFactor = (double) THUMBNAIL_HEIGHT/ imageBitmap.getHeight();
      final int THUMBNAIL_WIDTH = Math.round((float)shrinkFactor*imageBitmap.getWidth());
      imageBitmap = Bitmap.createScaledBitmap(imageBitmap, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, false);

      String outputPath = path + "/.thumbnail.jpg";
      File file = new File(outputPath);
      FileOutputStream fos = new FileOutputStream(file);
      imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

      return outputPath;
    }
    catch(Exception ex) {
    	Logger.log(ex);
    	return null;
    }
	}	


}
