package com.permission.activity;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.permission.listener.PermissinoListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiangHaiJun
 * @time 2017/2/15 11:43
 */

public class BaseActivity extends AppCompatActivity {
	private PermissinoListener perListerner;

	/**
	 * 申请权限
	 * @param permissions
	 * @param perListerner
	 */
	public void requestAuthority(String[] permissions, PermissinoListener perListerner){
		this.perListerner = perListerner;
		List<String> permissionList = new ArrayList<>();
		for (String permission : permissions) {
			if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
				permissionList.add(permission);
			}
		}

		if (!permissionList.isEmpty()) {
			ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
		} else {
			perListerner.agree();
		}
	}

	/**
	 * 权限申请回调函数
	 * @param requestCode
	 * @param permissions
	 * @param grantResults
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case 1:
				if (grantResults.length > 0) {
					List<String> refusePermissionList = new ArrayList<>();

					for (int i = 0; i < grantResults.length; i++) {
						if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
							refusePermissionList.add(permissions[i]);
						}
					}

					if (!refusePermissionList.isEmpty()) {
						perListerner.refuse(refusePermissionList);
					} else {
						perListerner.agree();
					}
				}
			break;
		}
	}
}
