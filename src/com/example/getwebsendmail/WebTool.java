package com.example.getwebsendmail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import junit.framework.Test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.utils.StreamTool;
import com.example.xmlparse.XmlParseMain;

import android.util.Log;

public class WebTool {

	public String getWebContentString(String path) throws Exception {
		Log.i("===", path);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		
		if (conn.getResponseCode() == 200) {
			Log.i("===", "test");
			InputStream inStream = conn.getInputStream();


			
			
			byte[] data = StreamTool.read(inStream);
			String html = new String(data, "GB2312");
			

			String tmpString = html.substring(0, 100);
			Log.i("===", "tmp: " + tmpString + tmpString.indexOf("content"));
			
//			Testxml(inStream);
			XmlParseMain xmlParseMain = new XmlParseMain();
			// InputStreamReader isr = new InputStreamReader(inStream,
			// "GB2312");
		InputStream in_withcode = new ByteArrayInputStream(
					html.getBytes("GB2312"));
			xmlParseMain.readXML(inStream);// inStream will be closed in this
												// function
			
			if (html == null) {
				Log.i("===", "html null");
			}
			return html;
		} else {
			Log.i("===", "connet failed");
		}
		return null;
	}

	private void Testxml(InputStream inStream2) {
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
				+ "<persons> <person id=\"23\"> <name>liming</name> <age>30</age> </person></persons>";
		InputStream inStream = new ByteArrayInputStream(str.getBytes());
		XmlParseMain xmlParseMain = new XmlParseMain();
		// InputStreamReader isr = new InputStreamReader(inStream, "GB2312");
		xmlParseMain.readXML(inStream);// inStream will be closed in this
										// function
	}

	public String getWebBody(String path) {

		return null;
	}
}
