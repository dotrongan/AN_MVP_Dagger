package com.mvp.Pattern;

import android.content.Context;

/**
 * Created by ANDT on 9/23/2016.
 */

public class ParseDataFactory implements ParseFactory {

	@Override
	public Strategy createParseData(Context context,String domain) {
		if(domain.contains("24h.com.vn")) {
			return ParseDataTheThao24h.getInstance(context);
		}
		else if(domain.contains("thethao247")) {
			return ParseDataTheThao247.getInstance(context);
		}
		else {
			return NoParseData.getInstance();
		}
	}
}
