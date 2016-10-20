package com.mvp.View;
import com.mvp.Model.Data;
import java.util.List;

/**
 * Created by ANDT on 9/22/2016.
 */

public interface ListDataView {
	void showLoading();
	void hideLoading();
	void noData();
	void showData(List<Data> lstData);
	void showError();
}
