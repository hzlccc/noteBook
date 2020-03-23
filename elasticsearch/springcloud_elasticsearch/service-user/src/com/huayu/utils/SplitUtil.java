package com.huayu.utils;

import java.util.ArrayList;
import java.util.List;

import com.huayu.bean.user.Shgx;
import com.huayu.bean.user.Srjl;
import com.huayu.bean.user.User;

public class SplitUtil {

	private static int count = 0;
	public static List<List<User>> splitUser(List<User> list, int len) {  
		
		if (list == null || list.size() == 0 || len < 1) {  
			return null;  
		}  
	  
		List<List<User>> result = new ArrayList<List<User>>();  
	  
		int size = list.size();  
		count = (size + len - 1) / len;  
		for (int i = 0; i < count; i++) {  
			List<User> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));  
			result.add(subList);  
		}  
		return result;  
	}
	
	public static List<List<Shgx>> splitShgx(List<Shgx> list, int len) {  
		
		if (list == null || list.size() == 0 || len < 1) {  
			return null;  
		}  
	  
		List<List<Shgx>> result = new ArrayList<List<Shgx>>();  
	  
		int size = list.size();  
		count = (size + len - 1) / len;  
		for (int i = 0; i < count; i++) {  
			List<Shgx> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));  
			result.add(subList);  
		}  
		return result;  
	}

	public static List<List<Srjl>> splitSrjl(List<Srjl> list, int len) {  
		
		if (list == null || list.size() == 0 || len < 1) {  
			return null;  
		}  
	  
		List<List<Srjl>> result = new ArrayList<List<Srjl>>();  
	  
		int size = list.size();  
		count = (size + len - 1) / len;  
		for (int i = 0; i < count; i++) {  
			List<Srjl> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));  
			result.add(subList);  
		}  
		return result;  
	}
	
}
