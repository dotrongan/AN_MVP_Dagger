package com.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.guna.libmultispinner.MultiSelectionSpinner;
import com.mvp.Adapter.DataAdapter;
import com.mvp.Common.GlobalValue;
import com.mvp.Common.StateMaintainer;
import com.mvp.Dagger.DaggerMainScreenComponent;
import com.mvp.Dagger.MainScreenModule;
import com.mvp.Model.Data;
import com.mvp.Presenter.ListDataPresenter;
import com.mvp.View.ListDataView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ListDataView, MultiSelectionSpinner
		.OnMultipleItemsSelectedListener{
	private ListView lvData;
	@Inject
	ListDataPresenter presenter;
	@Inject
	SharedPreferences sharedPreferences;
	private DataAdapter dataAdapter;
	private ProgressDialog progressDialog;
	protected final String TAG = "MainActivityStateMaintainer";

	private final StateMaintainer mStateMaintainer =
			new StateMaintainer(this.getFragmentManager(), TAG);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DaggerMainScreenComponent.builder()
				.appComponent(((BaseApp) getApplicationContext()).getAppComponent())
				.mainScreenModule(new MainScreenModule(this))
				.build().inject(this);

		setContentView(R.layout.activity_main);
		lvData = (ListView) findViewById(R.id.listView);
		setupMultipleDropdownList();
		startMVPOps();
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Vui lòng đợi !");
		checkDependency();
	}

	private void setupMultipleDropdownList() {
		List<String> domains = GlobalValue.lstDomain;
		MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.mySpinner);
		multiSelectionSpinner.setItems(domains);
		multiSelectionSpinner.setListener(this);
	}

	public void startMVPOps() {
		try {
			if (mStateMaintainer.firstTimeIn()) {
				Log.d("MainActivity", "onCreate() called for the first time");
				initialize();
			} else {
				Log.d("MainActivity", "onCreate() called more than once");
				reinitialize();
			}
		} catch (InstantiationException | IllegalAccessException e) {
			Log.d("MainActivity", "onCreate() " + e);
			throw new RuntimeException(e);
		}
	}

	private void initialize()
			throws InstantiationException, IllegalAccessException {
		mStateMaintainer.put("ListDataPresenter", presenter);
	}

	private void reinitialize()
			throws InstantiationException, IllegalAccessException {
		presenter = mStateMaintainer.get("ListDataPresenter");
		presenter.onLoadState(this);
	}

	@Override
	public void showLoading() {
		progressDialog.show();
	}

	@Override
	public void hideLoading() {
		progressDialog.dismiss();
	}

	@Override
	public void noData() {
		Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_LONG).show();
	}

	@Override
	public void showData(final List<Data> lstData) {
		Intent intent = new Intent(this,TestActivity.class);
		intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) lstData);
		this.startActivity(intent);
	}

	@Override
	public void showError() {
		Toast.makeText(this, "Lỗi kết nối !", Toast.LENGTH_LONG).show();
	}

	@Override
	public void selectedIndices(List<Integer> indices) {
		 if(indices != null) {
			 Log.d("MainActivity", Arrays.asList(indices).toString());
		 }
	}

	@Override
	public void selectedStrings(List<String> strings) {
		if (presenter != null) {
			presenter.getData(strings);
		}
	}

	private void checkDependency() {
		if(sharedPreferences != null) {
			Log.d("Dependency", "checkDependency: ngon roi");
		}
		else {
			Log.d("Dependency", "checkDependency: chua ngon");
		}
	}

}
