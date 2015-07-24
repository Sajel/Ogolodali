package com.ogolodali.app.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ogolodali.app.R;
import com.ogolodali.app.model.Search;
import com.ogolodali.app.parser.SearchParser;

import java.io.IOException;

public class StartActivity extends Activity {

	private String TAG = "Start Activity";

	private TextView status;
	private ProgressBar progressBar;
	private Button reconnectButton;

	private static Search search;

	private static SearchParseAsyncTask searchParseAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		search = Search.getInstance();

		status = (TextView) findViewById(R.id.statusTextView);
		progressBar = (ProgressBar) findViewById(R.id.connectingProgressBar);
		reconnectButton = (Button) findViewById(R.id.reconnectButton);
	}

	@Override
	protected void onResume() {
		super.onResume();
		connect();
	}

	public void onReconnect(View view) {
		status.setText(getString(R.string.loading));
		progressBar.setVisibility(View.VISIBLE);
		reconnectButton.setVisibility(View.INVISIBLE);
		connect();
	}

	private void connect() {
		if (searchParseAsyncTask == null) {
			searchParseAsyncTask = new SearchParseAsyncTask(this);
			try {
				searchParseAsyncTask.execute();
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}
		else {
			searchParseAsyncTask.setActivity(this);
		}
		Log.i(TAG, "Activity:" + this.hashCode() + ", Task:" + searchParseAsyncTask.hashCode());
	}

	private void fail() {
		status.setText(getString(R.string.connecting_error));
		progressBar.setVisibility(View.INVISIBLE);
		reconnectButton.setVisibility(View.VISIBLE);
	}

	private static class SearchParseAsyncTask extends AsyncTask<Void, Void, Void> {

		private boolean failed = false;

		StartActivity activity;

		SearchParseAsyncTask(StartActivity activity) {
			this.activity = activity;
		}

		void setActivity(StartActivity activity) {
			this.activity = activity;
		}
		@Override
		protected Void doInBackground(Void... params) {
			SearchParser parser = null;
			try {
				Log.i("AsyncTask", "Task:" + this.hashCode() + ", Activity:" + activity.hashCode());
			try {
				parser = SearchParser.parse();
			} catch (IOException e) {
				e.printStackTrace();
			}
			search.setAllIngredients(parser.getIngredientsCategories());
				search.setAllTags(parser.getTagsCategories());
				Intent intent = new Intent(activity,MainActivity.class);
				activity.startActivity(intent);
			} catch (Exception e) {
				Log.e(activity.TAG, e.getStackTrace().toString());
				failed = true;
			};
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			if(failed) {
				activity.fail();
			}
		}
	}
}
