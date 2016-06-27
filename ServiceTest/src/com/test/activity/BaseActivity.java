/**
 * 项目名称: 七七同城
 * 
 * 文件名称: BaseActivity.java
 * 
 * Copyright: 2015 合肥以撒网络 Inc. All rights reserved.
 */

package com.test.activity;

import java.sql.NClob;

import com.example.sevicetest.R;
import com.test.service.TestService;
import com.test.service.TestService.MyLoaclBind;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 *
 * 类名称: BaseActivity.java
 * 类描述:
 * 创建人: hq
 * 创建时间: 2016年6月14日下午5:01:47
 * -------------------------修订历史------------
 * 修改人:
 * 修改时间:
 * 修改备注:
 */
public class BaseActivity extends Activity implements OnClickListener {
	private Button mStartServiceBtn;
	private Button mStoptServiceBtn;
	private Button mBindServiceBtn;
	private Button mUnbindServiceBtn;
	private Context mContext;
	
	private TestService mService;
	private boolean isConnected;
	ServiceConnection mConnect = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			isConnected = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			TestService.MyLoaclBind binder = (MyLoaclBind) service;
			mService = binder.getInstance();
			isConnected = true;  
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		mContext = BaseActivity.this;
		initView();
	}

	public void initView() {
		mStartServiceBtn = (Button) findViewById(R.id.start_service);
		mStoptServiceBtn = (Button) findViewById(R.id.stop_service);
		mBindServiceBtn = (Button) findViewById(R.id.bind_service);
		mUnbindServiceBtn = (Button) findViewById(R.id.unbind_service);
		mStartServiceBtn.setOnClickListener(this);
		mStoptServiceBtn.setOnClickListener(this);
		mBindServiceBtn.setOnClickListener(this);
		mUnbindServiceBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.start_service:
			Intent intent = new Intent(mContext, TestService.class);
			startService(intent);
			break;
		case R.id.stop_service:
			stopService(new Intent(mContext, TestService.class));
			break;
		case R.id.bind_service:
			bindService(new Intent(mContext, TestService.class), mConnect, 1);
			break;
		case R.id.unbind_service:
			unbindService(mConnect);  
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(isConnected){
			unbindService(mConnect);  
            isConnected = false; 
		}
	}
}
