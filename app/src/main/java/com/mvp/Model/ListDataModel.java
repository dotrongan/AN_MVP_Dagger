package com.mvp.Model;

import com.mvp.Pattern.ParseDataFactory;
import com.mvp.Pattern.Strategy;
import com.mvp.PresenterOps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANDT on 9/22/2016.
 */

public class ListDataModel implements ModelOps {
	private ParseDataFactory parseFactory;
	private PresenterOps presenterOps;
	private List<Data> lstDataAll;


	public ListDataModel(PresenterOps presenterOps) {
		this.presenterOps = presenterOps;
		lstDataAll = new ArrayList<>();
	}

	public void setParseFactory(ParseDataFactory parseFactory) {
		this.parseFactory = parseFactory;
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
		Strategy strategyParse = parseFactory.createParseData(domain);
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
