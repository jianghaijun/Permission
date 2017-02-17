package com.permission.listener;

import java.util.List;

/**
 * @author JiangHaiJun
 * @time 2017/2/15 12:07
 */

public interface PermissinoListener {
	void agree();
	void refuse(List<String> refusePermission);
}
