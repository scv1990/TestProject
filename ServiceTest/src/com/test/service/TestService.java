/**
 * 项目名称: 七七同城
 * 
 * 文件名称: TestService.java
 * 
 * Copyright: 2015 合肥以撒网络 Inc. All rights reserved.
 */

package com.test.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 *
 * 类名称: TestService.java
 * 类描述:	 
 * 创建人:  hq
 * 创建时间: 2016年6月14日下午5:31:36
 * -------------------------修订历史------------
 * 修改人:
 * 修改时间:
 * 修改备注:
 */
public class TestService extends Service {
	private static final String TAG = "TestService"; 
	public MyLoaclBind mBind = new MyLoaclBind();
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBind;
	}
	
	@Override
	public void unbindService(ServiceConnection conn) {
		super.unbindService(conn);
		Log.i(TAG, "unbindService");
	}
	
	public class MyLoaclBind extends Binder {
		public TestService getInstance(){
			return TestService.this;
		}
	}
	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		Log.i(TAG, "bindService");
		return super.bindService(service, conn, flags);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
	}

}
