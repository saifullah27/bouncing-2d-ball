package com.aselalee.bouncingball;

import android.app.Activity;
import android.os.Bundle;
//import android.view.View;

public class BouncingBallActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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