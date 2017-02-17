package com.permission.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.permission.listener.PermissinoListener;

import java.util.List;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("Object");
	}

	public void getPermission(View view){
		requestAuthority(new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CALENDAR}, new PermissinoListener() {
			@Override
			public void agree() {
				Toast.makeText(MainActivity.this, "权限已全部授权!", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void refuse(List<String> refusePermission) {
				for (String refuse : refusePermission ) {
					Toast.makeText(MainActivity.this, "用户已拒绝：" + refuse + "权限!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
