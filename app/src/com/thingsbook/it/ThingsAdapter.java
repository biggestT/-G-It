package com.thingsbook.it;

import android.widget.ImageView;
import android.widget.BaseAdapter;

import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import android.widget.GridView;
import java.util.ArrayList;


public class ThingsAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Thing> things;

	public ThingsAdapter(Context c, ArrayList<Thing> t) {
		mContext = c;
		things = t;
	}

	public ThingsAdapter(Context c) {
		this(c, new ArrayList<Thing>());
	}

	public int getCount() {
		return things.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {  // if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageBitmap(things.get(position).images[0]);
		return imageView;
	}
}