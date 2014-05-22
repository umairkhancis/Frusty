package com.cistem.frusty.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cistem.frusty.R;
import com.cistem.frusty.fragments.MainFragment;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new MainFragment()).commit();
		}
	}
}
