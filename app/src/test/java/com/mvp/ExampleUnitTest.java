package com.mvp;

import com.mvp.Model.Data;
import com.mvp.Model.ListDataModel;
import com.mvp.Pattern.ParseDataFactory;
import com.mvp.Pattern.ParseDataTheThao24h;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21,manifest = "/src/main/AndroidManifest.xml")
public class ExampleUnitTest {

	private PresenterOps presenterOps;
	private ParseDataFactory parseDataFactory;
	private List<Data> lstDataAll;
	private ListDataModel listDataModel;
	ParseDataTheThao24h parseDataTheThao24h;

	@Before
	public void setup() {
		presenterOps = Mockito.mock(PresenterOps.class);
		lstDataAll = new ArrayList<>();
		parseDataFactory = new ParseDataFactory();
		listDataModel = new ListDataModel(presenterOps);
		parseDataTheThao24h = ParseDataTheThao24h.getInstance();
	}

	@Test
	public void testGetData() {
		List<String> lstDomain = Arrays.asList(
				"24h.com.vn"
		);
		listDataModel.getData(lstDomain);
		parseDataTheThao24h.parseData(presenterOps);
		List<String> lstDomain1 = Arrays.asList(
				"thethao247"
		);
		listDataModel.getData(lstDomain1);
	}
}