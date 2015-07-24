package com.ogolodali.app.view;

import com.ogolodali.app.R;
import com.ogolodali.app.adapter.TabsPagerAdapter;
import com.ogolodali.app.database.DBHelper;
import com.ogolodali.app.database.FridgeService;
import com.ogolodali.app.model.Ingredient;
import com.ogolodali.app.model.Search;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private static final String TAG = "MainActivity";

	private Search search;

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		search = Search.getInstance();

		viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();


        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        String[] tabsTitles = { getString(R.string.search), getString(R.string.ingredients),  getString(R.string.tags) };
        for (String tab_name : tabsTitles) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
						.setTabListener(this));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

				@Override
				public void onPageSelected(int position) {
					actionBar.setSelectedNavigationItem(position);
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
				}

				@Override
				public void onPageScrollStateChanged(int arg0) {
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case (R.id.saveFridge):{
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle(R.string.saveFridgeConfirm).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						FridgeService fridgeService = new FridgeService(MainActivity.this);
						fridgeService.setFridge(search.getChosenIngredients());
						Toast.makeText(MainActivity.this.getApplicationContext(), R.string.saveFridgeDone, Toast.LENGTH_LONG).show();
					}
				}).setNegativeButton(R.string.no, null);

				AlertDialog alertDialog = builder.create();
				alertDialog.show();
				break;
			}
			case (R.id.loadFridge): {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle(R.string.loadFridgeConfirm).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						FridgeService fridgeService = new FridgeService(MainActivity.this);
						Set<Ingredient> fridge = fridgeService.getFridge();
						if (fridge != null && !fridge.isEmpty()) {
							search.getChosenIngredients().clear();
							search.getChosenIngredients().addAll(fridge);
							search.notifyObservers();
							Toast.makeText(getApplicationContext(), R.string.loadFridgeDone, Toast.LENGTH_LONG).show();
						}
						else {
							Toast.makeText(getApplicationContext(), R.string.emptyFridge, Toast.LENGTH_LONG).show();
						}

					}
				}).setNegativeButton(R.string.no, null);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	public void toListRecipe(View view){
		Intent intent = new Intent(this, ListRecipeActivity.class);
		startActivity(intent);
	}

}
