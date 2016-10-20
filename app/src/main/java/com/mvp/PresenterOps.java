package com.mvp;

import android.content.Context;

import com.mvp.Model.Data;
import com.mvp.View.ListDataView;

import java.util.List;

/**
 * Created by ANDT on 9/23/2016.
 */

public interface PresenterOps {
	void loadDataSuccess(List<Data> lstData);
	void loadDataError();
	void loadingData(boolean loading);
}
