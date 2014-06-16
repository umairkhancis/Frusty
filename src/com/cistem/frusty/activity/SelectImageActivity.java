package com.cistem.frusty.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cistem.frusty.R;
import com.cistem.frusty.fragments.SelectImageFragment;

public class SelectImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_image);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new SelectImageFragment()).commit();
		}
	}
}
