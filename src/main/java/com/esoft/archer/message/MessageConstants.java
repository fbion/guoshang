package com.esoft.archer.message;

public class MessageConstants {
	public final static class InBoxConstants{
		public final static String NOREAD = "0";
		public final static String ISREAD = "1";
	}
	
	/**
	 * UserMessageNode Id
	 * 信息发送节点ID
	 * @author Administrator
	 *
	 */
	public final static class UserMessageNodeId{
		/**
		 * 注册成功激活
		 */
		public final static String REGISTER_ACTIVE = "register_active";
		/**
		 * 通过邮箱找回登录密码
		 */
		public final static String FIND_LOGIN_PASSWORD_BY_EMAIL = "find_login_password_by_email";
		/**
		 * 通过手机找回登录密码
		 */
		public final static String FIND_LOGIN_PASSWORD_BY_MOBILE = "find_login_password_by_mobile";
		/**
		 * 修改绑定邮箱
		 */
		public static final String CHANGE_BINDING_EMAIL = "change_binding_email";
		/**
		 * 绑定邮箱
		 */
		public static final String BINDING_EMAIL = "binding_email";
		/**
		 * 绑定手机号
		 */
		public static final String BINDING_MOBILE_NUMBER = "binding_mobile_number";
		/**
		 * 修改绑定手机号
		 */
		public static final String CHANGE_BINDING_MOBILE_NUMBER = "change_binding_mobile_number";
		/**
		 * 通过手机号找回交易密码
		 */
		public static final String FIND_CASH_PASSWORD_BY_MOBILE = "find_cash_password_by_mobile";
		/**
		 * 通过手机号注册
		 */
		public static final String REGISTER_BY_MOBILE_NUMBER = "register_by_mobile_number";
		/**
		 * 投资
		 */
		public static final String INVEST = "invest";
		/**
		 * 充值成功
		 */
		public static final String RECHARGE_SUCCESS = "recharge_success";
		
		/**
		 * 审核通过
		 */
		public static final String LOAN_VERIFY_SUCCESS = "loan_verify_success";
		
		/**
		 * 审核不通过
		 */
		public static final String LOAN_VERIFY_FAIL = "loan_verify_fail";
		
		/**
		 * 审核不通过
		 */
		public static final String REPAY_ALERT = "repay_alert";
		
		
	}


	/**
	 * YtxMessageTemplate Id
	 * @author Administrator
	 *
	 */
	public final static class YtxMessageTemplateId{
		//注册时发送的验证码
		public final static String USER_REGISTER_CODE = "58587";
		//注册成功
		//public final static String USER_REGISTER_SUCCESS = "58588";
		//注册成功(新)
		public final static String USER_REGISTER_SUCCESS = "60781";
		//更改绑定手机
		public final static String USER_CHANGE_MOBILE = "58589";
		//充值成功
		public final static String USER_RECHARGE_SUCCESS = "58591";
		//找回密码
		public final static String USER_FIND_PASSWORD = "59120";
		//投资成功
		public final static String LOAN_CREATE_SUCCESS = "58592";
		//提现成功
		public final static String WITHDRAW_CREATE_SUCCESS = "58596";
	}
	
	
	/**
	 * UserMessageTemplate status
	 * @author Administrator
	 *
	 */
	public final static class UserMessageTemplateStatus{
		public final static String OPEN = "可选";
		public final static String REQURIED = "必须";		
		public final static String CLOSED = "关闭";
	}
	
	/**
	 * UserMessageWayStatus status
	 * @author Administrator
	 *
	 */
	public final static class UserMessageWayStatus{
		public final static String OPEN = "开启";
		public final static String CLOSED = "关闭";
	}
	
	/**
	 * UserMessageNodeStatus status
	 * @author Administrator
	 *
	 */
	public final static class UserMessageNodeStatus{
		public final static String OPEN = "开启";
		public final static String CLOSED = "关闭";
	}
	
	/**
	 * UserMessageWay Id
	 * @author Administrator
	 *
	 */
	public final static class UserMessageWayId{
		/**
		 * 邮件
		 */
		public final static String EMAIL = "email";
		/**
		 * 站内信
		 */
		public final static String LETTER = "letter";		
		/**
		 * 短信
		 */
		public final static String SMS = "sms";
	}
	
}
