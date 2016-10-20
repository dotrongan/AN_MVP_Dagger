package com.mvp.Presenter;

import com.mvp.Model.Data;
import com.mvp.Model.ListDataModel;
import com.mvp.PresenterOps;
import com.mvp.View.ListDataView;
import com.mvp.View.RequireView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ANDT on 9/22/2016.
 */

public class ListDataPresenter implements PresenterOps,RequireView {
	ListDataModel model;
	private WeakReference<ListDataView> mView;

	@Inject
	public ListDataPresenter(ListDataView view) {
		this.mView = new WeakReference<>(view);
	}

	public void setModel(ListDataModel model) {
		this.model = model;
	}

	public void getData(List<String> lstData) {
		model.getData(lstData);
	}

	@Override
	public void loadDataSuccess(List<Data> lstData) {
		if(!lstData.isEmpty() && lstData.size() > 0) {
			model.setData(lstData);
			mView.get().showData(model.getDataAll());
		}
		else {
			mView.get().noData();
		}
	}

	@Override
	public void loadDataError() {
		mView.get().showError();
	}

	@Override
	public void loadingData(boolean loading) {
		if(loading) {
			mView.get().showLoading();
		}
		else {
			mView.get().hideLoading();
		}
	}

	@Override
	public void onLoadState(ListDataView view) {
		mView = new WeakReference<>(view);
		mView.get().showData(model.getDataAll());
	}
}
