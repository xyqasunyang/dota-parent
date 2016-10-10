package com.cd.dota.core.util;


public class UserValidateUtil {

	/**
	 * 通过验证返回true
	 * @param account
	 * @param password
	 * @return
	 */
	public static boolean validateUser(String account, String password) {
		if (account != null && !"".equals(account) && password != null
				&& !"".equals(password))
			return true;
		return false;
	}

}
