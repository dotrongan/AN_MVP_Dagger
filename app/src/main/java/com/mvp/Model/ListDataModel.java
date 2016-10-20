package com.mvp.Model;

import android.content.Context;

import com.mvp.BaseApp;
import com.mvp.Pattern.ParseDataFactory;
import com.mvp.Pattern.ParseFactory;
import com.mvp.Pattern.Strategy;
import com.mvp.PresenterOps;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ANDT on 9/22/2016.
 */

public class ListDataModel implements ModelOps {
	private ParseDataFactory parseFactory;
	private PresenterOps presenterOps;
	private List<Data> lstDataAll;
	private Context context;


	public ListDataModel(PresenterOps presenterOps) {
		this.presenterOps = presenterOps;
		lstDataAll = new ArrayList<>();
	}

	public void setParseFactory(ParseDataFactory parseFactory) {
		this.parseFactory = parseFactory;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void getData(List<String> domains) {
		if(lstDataAll != null) {
			lstDataAll.clear();
		}
		for(String domain : domains) {
			createParseData(domain);
		}
	}

	@Override
	public void createParseData(String domain) {
		Strategy strategyParse = parseFactory.createParseData(context,domain);
		strategyParse.parseData(presenterOps);
	}

	@Override
	public void setData(List<Data> lstData) {
		lstDataAll.addAll(lstData);
	}

	@Override
	public List<Data> getDataAll() {
		return lstDataAll;
	}
}
