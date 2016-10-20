package com.mvp.Pattern;

import android.content.Context;

/**
 * Created by ANDT on 9/23/2016.
 */

public interface ParseFactory {
	Strategy createParseData(Context context,String domain);
}
