package com.mvp.Pattern;

import com.mvp.Model.Data;
import com.mvp.PresenterOps;

import java.util.ArrayList;

/**
 * Created by ANDT on 9/23/2016.
 */

public class NoParseData implements Strategy {

	private static NoParseData instance;

	private NoParseData() {}

	public static NoParseData getInstance() {
		if (instance == null) {
			instance = new NoParseData();
		}
		return instance;
	}

	@Override
	public void parseData(PresenterOps loadData) {
		loadData.loadDataSuccess(new ArrayList<Data>());
	}
}
