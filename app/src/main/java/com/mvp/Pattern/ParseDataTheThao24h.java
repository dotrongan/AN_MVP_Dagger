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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * Created by ANDT on 9/23/2016.
 */

public class ParseDataTheThao24h implements Strategy {

	AQuery aQuery;
	private static final String URL = "http://www.24h.com.vn/upload/rss/bongda.rss";
	private static ParseDataTheThao24h instance;

	private ParseDataTheThao24h(Context context) {
		aQuery = new AQuery(context);
	}

	public static ParseDataTheThao24h getInstance(Context context) {
		if (instance == null) {
			instance = new ParseDataTheThao24h(context);
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
						List<Data> lstData = new ArrayList<>();
						List<XmlDom> items = xmlDom.tags("item");
						for (int i = 0; i < items.size(); i++) {
							XmlDom item = items.get(i);
							Data map = new Data();
							XmlDom title = item.child("title");
							XmlDom description = item.child("description");
							XmlDom link = item.child("link");
							XmlDom ngaydang = item.child("pubDate");
							String textTitle = title.text();
							String cDataReplace = description.text();
							String textDescription = cDataReplace.replace("<![CDATA[", "");
							textDescription = textDescription.replace("]]>", "");
							final Pattern pattern = Pattern.compile("src=\'([^\'>]+)");
							Matcher matcher = pattern.matcher(textDescription);
							String linkngon = null;
							while (matcher.find()) {
								linkngon = matcher.group(1);
							}
							String link_LienKet = link.text();
							String textNgayDang = ngaydang.text();
							map.setTitle(textTitle);
							map.setDescription(textDescription);
							map.setImage(linkngon);
							map.setLink(link_LienKet);
							map.setPubDate(textNgayDang);
							map.setDomain("24h.com.vn");
							lstData.add(map);
						}
						if (loadData != null) {
							if (lstData != null) loadData.loadDataSuccess(lstData);
							else loadData.loadDataError();
						} else {
							throw new RuntimeException("Please set callback for OnLoadUserResult");
						}
					} catch (Exception e) {
						Log.e("Loi", "callback: ", e);
					}
				}
				else {
					loadData.loadDataError();
				}
			}
		});
	}
}
