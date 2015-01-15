package com.example.getwebsendmail;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private WebTool webTool = new WebTool();
	private final static String url = /*"http://www.newsmth.net/nForum/#!board/CouponsLife";*/
	//"http://www.newsmth.net/nForum/board/CouponsLife";
	"http://www.newsmth.net/nForum/board/CouponsLife?ajax";
	private String webContentString;
	
	/*view*/
	private TextView webContentShowTextView;

	/* hanlder */
	private final static int PRINTWEBCONTENT = 0x00001;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == PRINTWEBCONTENT) {
				if (webContentString != null) {
					Log.i("===", webContentString);
					webContentShowTextView.setText(webContentString);
				} else {
					Log.i("===", "Can not get anything");
				}
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		view_init();

		new webOperThread().start();

	}
	
	private void view_init() {
		webContentShowTextView = (TextView) this.findViewById(R.id.codeView);
	}

	private class webOperThread extends Thread {
		public void run() {
			Log.i("===", "thread test");
			try {
				webContentString = webTool.getWebContentString(url);
				
				Message message = Message.obtain();
				message.what = PRINTWEBCONTENT;
				mHandler.sendMessage(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
