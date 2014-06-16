package com.cistem.frusty.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.cistem.frusty.R;

public class SelectImageFragment extends Fragment {
	
	private View mRootView;
	private Button mFriendBtn;
	private Button mEnemyBtn;
	private ImageView mFriendIv;
	private ImageView mEnemyIv;
	
    // this is the action code we use in our intent, 
    // this way we know we're looking at the response from our own action
    private static final int FRIEND_PICTURE = 1;
    private static final int ENEMY_PICTURE = 2;

    private String friendImagePath;
    private String enemyImagePath;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.select_image_frag, container, false);
	
		mFriendBtn = (Button) mRootView.findViewById(R.id.btn_select_friend_image);
		mEnemyBtn = (Button) mRootView.findViewById(R.id.btn_select_enemy_image);
		mFriendIv = (ImageView) mRootView.findViewById(R.id.friend_image);
		mEnemyIv = (ImageView) mRootView.findViewById(R.id.enemy_image);
		
		mFriendBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pickFromGallery(FRIEND_PICTURE);
			}
		});
		
		mEnemyBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pickFromGallery(ENEMY_PICTURE);
			}
		});
	
		return mRootView;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK) {
            Uri selectedImageUri = data.getData();
            if (requestCode == FRIEND_PICTURE) {
            	friendImagePath = getPath(selectedImageUri);
            	mFriendIv.setImageBitmap(BitmapFactory.decodeFile(friendImagePath));
            }
            else if (requestCode == ENEMY_PICTURE) {
            	enemyImagePath = getPath(selectedImageUri); 
            	mEnemyIv.setImageBitmap(BitmapFactory.decodeFile(enemyImagePath));
            	
            }
        }
    }
	
	private void pickFromGallery(int requestCode) {
        // in onCreate or any event where your want the user to
        // select a file
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode);
	}
	/**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
            // just some safety built in 
            if( uri == null ) {
                // TODO perform some logging or show user feedback
                return null;
            }
            // try to retrieve the image from the media store first
            // this will only work for images selected from gallery
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
            if( cursor != null ){
                int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
            // this is our fallback here
            return uri.getPath();
    }

	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
	}
	
}
