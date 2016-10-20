package com.mvp.Pattern;

import android.content.Context;
import android.util.Log;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.XmlDom;
import com.mvp.BaseApp;
import com.mvp.Model.Data;
import com.mvp.PresenterOps;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ANDT on 9/23/2016.
 */

public class ParseDataTheThao247 implements Strategy {

	@Inject
	AQuery aQuery;
	private static final String URL = "http://thethao247.vn/home.rss";
	private static ParseDataTheThao247 instance;

	private ParseDataTheThao247() {
		BaseApp.getAppComponent().inject(this);
	}

	public static ParseDataTheThao247 getInstance() {
		if (instance == null) {
			instance = new ParseDataTheThao247();
		}
		return instance;
	}

	@Override
	public void parseData(final PresenterOps loadData) {
		loadData.loadingData(true);
		aQuery.ajax(URL, XmlDom.class, new AjaxCallback<XmlDom>() {
			@Override
			public void callback(String url, XmlDom xmlDom, AjaxStatus status) {
				loadData.loadingData(false);
				if (xmlDom != null) {
					try {
						List<Data> lstData = new ArrayList<Data>();
						List<XmlDom> items = xmlDom.tags("item");
						for (int i = 0; i < items.size(); i++) {
							XmlDom item = items.get(i);
							Data map = new Data();
							XmlDom title = item.child("title");
							XmlDom link = item.child("link");
							XmlDom image = item.child("image");
							XmlDom pubDate = item.child("pubDate");
							String cDataReplace = title.text();
							String textTitle = cDataReplace.replace("<![CDATA[", "");
							textTitle = textTitle.replace("]]>", "");
							String textLink = link.text();
							String textImage = image.text();
							String textpubDate = pubDate.text();
							map.setTitle(textTitle);
							map.setLink(textLink);
							map.setImage(textImage);
							map.setPubDate(textpubDate);
							map.setDomain("thethao247");
							lstData.add(map);
						}
						if (loadData != null) {
							if (lstData != null) loadData.loadDataSuccess(lstData);
							else loadData.loadDataError();
						} else {
							throw new RuntimeException("Please set callback for OnLoadUserResult");
						}
					} catch (Exception e) {
						Log.e("TheThao247", "callback: ", e);
					}
				}
				else {
					loadData.loadDataError();
				}
			}
		});
	}
}
