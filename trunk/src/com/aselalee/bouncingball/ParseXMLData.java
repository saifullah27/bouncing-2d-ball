package com.aselalee.bouncingball;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;


public class ParseXMLData {
	Element docEle;

	public ParseXMLData(String xmlFile){
		//get the factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(new File(xmlFile));
			docEle = (Element) dom.getDocumentElement();
		}catch(ParserConfigurationException pce) {
			Log.e("XMLError", "Error parsing XML file " + pce);
			pce.printStackTrace();
		}catch(SAXException se) {
			Log.e("XMLError", "SAX exception " + se);
			se.printStackTrace();
		}catch(IOException ioe) {
			Log.e("XMLError", "I/O exception " + ioe);
			ioe.printStackTrace();
		}
	}

	public float getRadius() {
		float radius = getFloatValueByTagName(docEle,"radius");;
		return radius;
	}

	private String getTextValueByTagName(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		//It is assumed that there's only one element by the given name.
		//If there are more than one, first entry is used.
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

	private float getFloatValueByTagName(Element ele, String tagName) {
		return Float.parseFloat(getTextValueByTagName(ele,tagName));
	}
}
