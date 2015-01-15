package com.example.getwebsendmail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.utils.StreamTool;

import android.util.Log;

public class WebTool {

	public String getWebContentString(String path) throws Exception {
		Log.i("===", path);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getResponseCode() == 200){
			Log.i("===", "test");
			InputStream inStream = conn.getInputStream();
			byte[] data = StreamTool.read(inStream);
			String html = new String(data, "GB2312");
			if(html == null){
				Log.i("===", "html null");
			}
			return html;
		}else{
			Log.i("===", "connet failed");
		}
		return null;
	}
}
