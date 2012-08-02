package com.aselalee.bouncingball;

import android.app.Activity;
import android.os.Bundle;

public class BouncingBallActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bouncingball);

		//Set radius read from XML file
		ResourceManager resMgr = new ResourceManager(this);
		String xmlFileName = resMgr.getResourceFileName(R.raw.inputdata);
		if( xmlFileName != null) {
			ParseXMLData xmlData = new ParseXMLData(xmlFileName);
			BouncingBallView bbv = (BouncingBallView) findViewById(R.id.bbv);
			bbv.setRadius(xmlData.getRadius());
		}
	}
}