package com.thingsbook.it;

public class LibGit2 {

	static {
		System.loadLibrary("com_thingsbook_it_LibGit2");
	}

  public native static int clone(String URL, String LocalPath);
  public native static void init();
}