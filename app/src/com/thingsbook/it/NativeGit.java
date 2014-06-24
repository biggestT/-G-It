package com.thingsbook.it;

import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import com.thingsbook.it.Logger;

public class NativeGit {

	// static TextView currentTextView;
	static Handler currentHandler;

  private native static int doClone(String URL, String LocalPath);

	private void progressCallback(String t) {
		Logger.log(t);

		Bundle b = new Bundle();
		b.putString("progressText", t);
		Message msg = Message.obtain(currentHandler);
		msg.setData(b);
		currentHandler.sendMessage(msg);
  }

  // Outward functions:
  // ==========

  public static void cloneWithProgress(String url, String path, Handler ch) {
  	currentHandler = ch;
  	doClone(url, path);
  }
  
	static {
		System.loadLibrary("com_thingsbook_it_NativeGit");
	}

}