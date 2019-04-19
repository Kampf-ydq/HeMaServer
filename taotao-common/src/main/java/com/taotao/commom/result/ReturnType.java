package com.taotao.commom.result;

public interface ReturnType {

	int NO_RECIEVE_DATA = 10; //前台没有数据提交
	
	int NO_USER = 11; //该用户名尚未注册
	
	int INVALID_PASSWORD = 12; //密码错误
	
	int SUCCESS = 13; //成功
	
	int ERROR = 14; //失败
	
	int IS_REG = 15 ; //已注册
}
