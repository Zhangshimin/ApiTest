package com.ezijing.qa.common;

public class TestBase {
	
	
	// Login&Logout
	public static String userchargefix = "/user/charge";

	// Business Type
	public static String cloudfix = "/cloud";
	public static String webfix = "/web";
	public static String apifix = "/api";
	public static String nodefix = "/node";

	// api version
	public static String data_version = "/v2";
	public static String api_version = "/v1";
	public static String slashfix = "/";
	public static String previousfix = "/previous";
	
	//user
	public static String userloginfix = "/users/login";
	public static String userlogoutfix = "/users/logout";
	public static String userisloginfix = "/user/islogin";
	public static String userinformationfix = "/user/information";
	public static String userallfix = "/user/all";
	
	// Course
	public static String coursefix = "/courses";
	public static String coursemyjoinedfix = "/course/my-joined";
	public static String coursemypurchasedfix = "/course/my-purchased";
	public static String coursemyfavoritefix = "/course/my-favorite";
	public static String courseinformationfix = "/course/information";
	public static String coursefavoritefix = "/course/favorite";
	public static String courseunfavoritefix = "/course/unfavorite";
	public static String coursejudgefix = "/course/judge";
	public static String coursesubscribefix = "/course/subscribe";
	public static String courseunsubscribefix = "/course/unsubscribe";
	public static String coursecommentfix = "/course/comment";
	public static String courseProgressfix = "/course/progress";
	public static String courseProgressallfix = "/course/progress-all";
	public static String courseNewsfix = "/course/news";
	public static String coursehotfix = "/course/hot";
	public static String lastplayedfix = "/course/last-played";
	public static String coursepurchasefix = "/course/purchase";
	
	public static String exampushfix = "/exam/push";
	public static String episodefix = "/episode";

	// api path
	public static String longinfix = "/users/login";
	public static String couponretrievefix = "/coupon/retrieve";
	public static String threadfix = "/thread";
	
	//order
	public static String orderfix = "/order";
	public static String queryfix = "/query";
	public static String purchasefix = "/purchase";
	
	//
	public static String questionfix = "/qa-question";
	public static String answerfix = "/qa-answer";
	public static String heatmapurlsfix = "/heatmap/urls";
	public static String heatmappushfix = "/heatmap/push";
	public static String commentfix = "/comment";
	public static String purchasedfix = "/node/purchased";
	public static String subscribedfix = "/subscribed";
	public static String unsubscribefix = "/unsubscribe";
	public static String subscribefix = "/subscribe";
	
	// message

	public static String messagefix = "/message";
	public static String messagesfix = "/messages";

	// mobile
	public static String mobilesliderfix = "/mobile/slider";
	public static String mobilechannelfix = "/mobile/channel";
	public static String mobileswitchfix = "/mobile/switch";

	// player
	public static String playerpushfix = "/player/push";
	public static String playerreviewfix = "/player/review";
	public static String playerajaxpushfix = "/player/ajax-push";
	public static String playerajaxreviewfix = "/player/ajax-review";

	// video
	public static String videofix = "/video";
	public static String progressfix = "/progress";
	public static String progressallfix = "/progress-all";
	public static String ismarkfix = "/ismark";
	public static String markfix = "/mark";
	public static String ajaxmarksfix = "/ajax-mark";

	// learn centre api
	public static String packagecreatefix = "/package/create";
	public static String packageajaxcreatefix = "/package/ajax-create";
	public static String planviewfix = "/plan/view";
	public static String plancreatefix = "/plan/create";
	public static String planvalidatefix = "/plan/validate";
	public static String planupdatefix = "/plan/update";
	public static String learninglistfix = "/report/learning-list";
	public static String learningreportfix = "/report/learning";
	public static String planajaxupdatefix = "/plan/ajax-update";

	// sso interface
	public static String ssofix = "/sso";
	public static String ticketsfix = "/tickets";
	public static String proxyValidatefix = "/proxyValidate";
	public static String registerCheckfix = "/registerCheck";
	public static String registerfix = "/register";
	public static String modifyPasswordfix = "/modifyPassword";
	public static String userfix = "/user";
	public static String userajaxfix = "/user/ajax";
	public static String userRolefix = "/role";
	public static String sendSmsfix = "/sendSms";
	public static String updateuserfix = "/updateUser";
	public static String userinfofix = "/userInfo";
	public static String validateEmailfix = "/validateEmail";
	
	
	//jssdk
	public static String jssdkfix = "/jssdk";
	public static String accountfix = "/account";
	public static String loginfix = "/login";
	public static String logoutfix = "/logout";
	public static String precheckfix = "/precheck";
	public static String showfix = "/show";
	public static String updatefix = "/update";
	public static String passupdatefix = "/passupdate";
	public static String passresetfix = "/passreset";
	public static String usersfix = "/users";
	
	
	// oauth api
	public static String oauthTicketfix = "/oauth-client/oauthTicket";
	public static final String QQ_APPID = "101207109";
	public static final String QQ_APPKEY = "ab6ce3220b4787305d842cfedf3356a9";
	public static final String WEIBO_CLIENTID = "247619875";
	public static final String WEIBO_CLIENTSERCRET = "6560ae6e2bdd3eb3fdbfcb6150c5674d";
	public static final String WEIXIN_APPID = "wxbd47bbfa8931d94f";
	public static final String WEIXIN_APPSERCRET = "7301621cad67f02b11a54569368619ff";
	public static final String RR_APPID = "a5280cbc823440679a94abae522daaa0";
	public static final String RR_APPSERCRET = "d5db2681f34d4d4f94d3e828d16b06e6";
	public static final String WEIXINURL = "https://api.weixin.qq.com/cgi-bin/token";

	// api final params
	public static final int SUPPORT = 1;
	public static final int UNSUPPORT = 0;
	public static final String USERNAME = "test919";
	public static final String PASSWORD = "111111";
	public static final int LIMIT = 1;
	public static final int PAGE = 1;
	public static final String SERVICE_SIT = "http://sit.ezijing.com";

	// picture base64
	public static String picone = "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";
	public static String pictwo = "";

	// quiz
	public static String quizshowfix = "/quiz/show";
	public static String quizgetoptionsfix = "/quiz/settings/getOptions";
	public static String quizfeedbackfix = "/quiz/feedback";
	public static String quizsetoptionsfix = "/quiz/settings/setOptions";
	public static String courseexamfeedbackfix = "/course/exam/feedback";

	// request url
	public static String api_url = "https://api.ezijing.com";
	public static String login_url = "https://login.ezijing.com";
	public static String sit_url = "http://sit.ezijing.com";
	public static String sit_cloud_url = "http://sit.cloud.ezijing.com";
	public static String oauth_url = "http://oauth.connect.ezijing.com";
	public static String api_login_url = api_url + webfix + userloginfix;
	public static String api_logout_url = api_url + webfix + userlogoutfix;
	public static String sit_sso_login = "http://sit.ezijing.com/v1/user/login"; 
	public static String sit_sso_logout = "http://sit.ezijing.com/v1/user/logout";
	public static String sle_sso_login = "http://sle.ezijing.com/v1/user/login"; 
	public static String sle_sso_logout = "http://sle.ezijing.com/v1/user/logout";
	public static String test_url = "http://test.ezijing.com";
	public static String sle_url = "http://sle.ezijing.com";
	
	//excle file name
	public static final String EXCEL_DATA_FILE = "datacenter";
	public static final String EXCEL_WEB_FILE = "webapi";
	public static final String EXCEL_SSO_FILE = "sso";
	
	//think time
	public static final int THINKTIME = 1000;
}
