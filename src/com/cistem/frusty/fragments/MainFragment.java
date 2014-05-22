package com.cistem.frusty.fragments;

import java.util.Random;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cistem.frusty.R;

public class MainFragment extends Fragment {
	
	protected static final int BOX1 = 1;
	protected static final int BOX2 = 2;
	protected static final int BOX3 = 3;
	protected static final int BOX4 = 4;
	
	private View mRootView;
	private ImageView mBox1;
	private ImageView mBox2;
	private ImageView mBox3;
	private ImageView mBox4;
	
	
	private int mDelay = 2000;
	
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			mBox1.setImageResource(0);
			mBox2.setImageResource(0);
			mBox3.setImageResource(0);
			mBox4.setImageResource(0);
			switch (msg.what) {
				case BOX1: {
					mBox1.setImageResource(R.drawable.zardari);
					break;
				}
				case BOX2: {
					mBox2.setImageResource(R.drawable.zardari);
					break;
				}
				case BOX3: {
					mBox3.setImageResource(R.drawable.zardari);
					break;
				}
				case BOX4: {
					mBox4.setImageResource(R.drawable.zardari);
					break;
				}
			}
		}
	};


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_main, container, false);
		
		mBox1 = (ImageView) mRootView.findViewById(R.id.box1);
		mBox2 = (ImageView) mRootView.findViewById(R.id.box2);
		mBox3 = (ImageView) mRootView.findViewById(R.id.box3);
		mBox4 = (ImageView) mRootView.findViewById(R.id.box4);
		
		mBox1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mBox1.setImageResource(R.drawable.egg);
			}
		});
		
		mBox2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mBox2.setImageResource(R.drawable.egg);
			}
		});
		
		mBox3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mBox3.setImageResource(R.drawable.egg);
			}
		});
		
		mBox4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mBox4.setImageResource(R.drawable.egg);
			}
		});
		
		return mRootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		new Thread(new loadEnemyImage(mHandler)).start();
	}
	
	private class loadEnemyImage implements Runnable {
		private final Handler handler;

		loadEnemyImage(Handler handler) {
			this.handler = handler;
		}

		public void run() {

			while(true) {
				int rand = getRandomNumber(1, 5);
				int id = getBoxIdFromRandomNumber(rand);
				sleep();
				Message msg = handler.obtainMessage(rand);
				handler.sendMessage(msg);
			}
		}
		
		private void sleep() {
			try {
				Thread.sleep(mDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private int getBoxIdFromRandomNumber(int rand) {
		switch (rand) {
		case 1:
			return R.id.box1;
			
		case 2:
			return R.id.box2;
			
		case 3:
			return R.id.box3;
			
		case 4:
			return R.id.box4;
		}
		return R.id.box4;
	}

	private int getRandomNumber(int low, int high) {
		Random r = new Random();
		int R = r.nextInt(high-low) + low;
		
		return R;
	}

	private void sleep() {
		try {
			Thread.sleep(mDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
