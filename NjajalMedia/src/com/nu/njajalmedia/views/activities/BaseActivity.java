package com.nu.njajalmedia.views.activities;

import com.nu.njajalmedia.R;
import com.nu.njajalmedia.helpers.NetworkHelper;
import com.nu.njajalmedia.helpers.NetworkHelper.WifiListener;
import com.nu.njajalmedia.helpers.sliding.SlidingMenu;
import com.nu.njajalmedia.helpers.sliding.app.SlidingFragmentActivity;
import com.nu.njajalmedia.views.fragments.BaseContentFragment;
import com.nu.njajalmedia.views.fragments.MainMenuFragment.OnMainMenuClickListener;

import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


/**
 * Base activity which provide basic function 
 * @author muhammad_a
 *
 */
public class BaseActivity extends SlidingFragmentActivity implements OnMainMenuClickListener, WifiListener {
	protected SlidingMenu slidingMenu;
	protected BaseContentFragment contentFragment;
	private NetworkHelper wifiReceiver;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.app_name));
		// customize the SlidingMenu
		slidingMenu = getSlidingMenu();
		setSliding();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		setSlidingActionBarEnabled(true);

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		// set the Above View
		setContentView(R.layout.content_frame);
		wifiReceiver = new NetworkHelper();
	}
	
	private void setSliding() {
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//		slidingMenu.setBehindWidth(300);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setSliding();
	}
	
	@Override
	public void onResume(){
		super.onResume();
//		setDropDownResources();
		this.registerReceiver(wifiReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}
	
	@Override
	public void onClickMainMenu() {
		slidingMenu.showContent();
	}
	
	/**
	 * Replace target layout with source fragment
	 * @param target
	 * @param source
	 */
	protected void replaceFragment(int target, Fragment source) {
		this.getSupportFragmentManager().beginTransaction()
		.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
		.replace(target, source).commit();
	}

	@Override
	public void onSelectFragment(Fragment fragment, int pos) {
		slidingMenu.showContent();
		replaceFragment(R.id.content_frame, fragment);
	}
	
	/***
	 * create action bar menu on top from menu layout
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.refresh, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (contentFragment != null) {
			contentFragment.onPrepareOptionsMenu(menu);
		}		
		return super.onPrepareOptionsMenu(menu);
	}
	
	/**
	 * Handle action bar action
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean state = super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			break;
		case R.id.menu_refresh:
			if (contentFragment != null) {
				contentFragment.onOptionsItemSelected(item);
			}			
//			setDropDownResources();
			break;
		default:
			break;
		}
		return state;
	}
	
	/**
	 * Get all drop down options
	 */
//	private void setDropDownResources() {
//		if (!DropDownMenuHelper.isSynchronized()) {
//			DropDownMenuHelper.setOptions(getApplicationContext());
//		}
//	}
	
	public void toastIt(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
	@Override
	  protected void onPause(){
	    super.onPause();
	    //closing transition animations
	    overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
	    this.unregisterReceiver(wifiReceiver);
	  }

	@Override
	public void onSaveAllChanges() {}

	@Override
	public void onResetAll() {}

	@Override
	public void onWifiStateChange(boolean connected) {
		if (!connected) {
			toastIt("Wifi Connection Lost");
		}else if (contentFragment != null && !contentFragment.isSynchronize()) {
			contentFragment.retrieveData();
		}
	}
}
