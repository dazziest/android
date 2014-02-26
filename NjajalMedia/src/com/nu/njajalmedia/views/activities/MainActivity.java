package com.nu.njajalmedia.views.activities;

import com.nu.njajalmedia.R;
import com.nu.njajalmedia.views.fragments.BaseContentFragment;
import com.nu.njajalmedia.views.fragments.MainContentFragment;
import com.nu.njajalmedia.views.fragments.MainMenuFragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;

public class MainActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		replaceFragment(R.id.menu_frame, new MainMenuFragment());
		overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
		
		contentFragment = new MainContentFragment();
		replaceFragment(R.id.content_frame, contentFragment);
	}

	@Override
	public void onResume(){
		super.onResume();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onBackPressed(){
		if (slidingMenu.isMenuShowing()) {
//			showExitConfirmation();
			finish();
		}else {
			toggle();
		}
	}
	
	@Override
	public void onSelectFragment(Fragment fragment, int pos) {
		super.onSelectFragment(fragment, pos);
		contentFragment = (BaseContentFragment) fragment;
	}

}
