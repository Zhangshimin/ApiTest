/**
 * 
 */
package com.ezijing.qa.heatmap;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.ezijing.qa.bl.HeatMapBl;
import com.ezijing.qa.common.TestBase;
import com.ezijing.qa.util.BaseApiUtil;
import com.ezijing.qa.vo.RequestVo;

/**
 * @author cuixiaohui
 *
 */
public class PostHeatmapPush extends TestBase{

	static Logger logger = Logger.getLogger(GetUrls.class);
	HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, String> headers = new HashMap<String, String>();
	RequestVo requestvo = new RequestVo();
	HeatMapBl heatmapbo = new HeatMapBl();
	private String token = "";
	private String data = "%5B%7B%22timestamp%22%3A1433311260243%2C%22uid"
			+ "%22%3A27323%2C%22host%22%3A%22www.ezijing.com%22%2C%22url"
			+ "%22%3A%22http%3A%2F%2Fwww.ezijing.com%2Fspecial%2Fcfc.shtml"
			+ "%22%2C%22resolution%22%3A%7B%22width%22%3A1280%2C%22height"
			+ "%22%3A800%7D%2C%22track_pos%22%3A%7B%22x%22%3A467%2C%22y"
			+ "%22%3A59%7D%2C%22click_pos%22%3A%7B%22x%22%3A0%2C%22y%22"
			+ "%3A0%7D%2C%22clientSize%22%3A%7B%22clientWidth%22%3A1044%2C"
			+ "%22clientHeight%22%3A7414%7D%2C%22windowSize%22%3A%7B%22winWidth"
			+ "%22%3A1044%2C%22winHeight%22%3A70%7D%7D%5D";
	@BeforeMethod
	public void setUp() throws Exception {
		BaseApiUtil.initLog4j(logger, Level.INFO);
		token = BaseApiUtil.SSOLogin(USERNAME, PASSWORD);
		headers.put("token", token);
	}
	@Test
	public void getUrlsNoraml() {

		params.clear();
		params.put("data", data);
		JSONObject json = heatmapbo.heatMapPush(headers, params);
		assertEquals(json.getIntValue("errorCode"), 200);
		
	}
	@AfterMethod
	public void logout() throws InterruptedException {
		BaseApiUtil.SSOLogout(token);
		Thread.sleep(THINKTIME);
	}
	
	
//	@Test
//	public void test() throws IOException {
//		
//		String strurl = "http://sit.cloud.ezijing.com/v1/heatmap/push";
//		JSONObject json = new JSONObject();
//		URL url = new URL(strurl);
//		String str = "data=%5B%7B%22timestamp%22%3A1433311260243%2C%22uid%22%3A27323%2C%22host%22%3A%22www.ezijing.com%22%2C%22url%22%3A%22http%3A%2F%2Fwww.ezijing.com%2Fspecial%2Fcfc.shtml%22%2C%22resolution%22%3A%7B%22width%22%3A1280%2C%22height%22%3A800%7D%2C%22track_pos%22%3A%7B%22x%22%3A467%2C%22y%22%3A59%7D%2C%22click_pos%22%3A%7B%22x%22%3A0%2C%22y%22%3A0%7D%2C%22clientSize%22%3A%7B%22clientWidth%22%3A1044%2C%22clientHeight%22%3A7414%7D%2C%22windowSize%22%3A%7B%22winWidth%22%3A1044%2C%22winHeight%22%3A70%7D%7D%5D";
//		HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//		urlConnection.setRequestMethod("POST");
//		urlConnection.setDoOutput(true);
//		urlConnection.setDoInput(true);
//		//urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
//		//urlConnection.setRequestProperty("Cookie","user.uid=1; user.sign=3a77b744687bce4d833fa850ce79645c"+";");
//		
//		//OutputStream outStrm = urlConnection.getOutputStream();
//		//ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);
//		//objOutputStrm.writeObject(URLDecoder.decode(str.trim(),"uft-8"));
//		//objOutputStrm.flush();
//		//objOutputStrm.close();
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(urlConnection.getOutputStream(),"utf-8"));
//		out.write(str);
//		out.flush();
//		out.close();
//		
//		InputStream in = urlConnection.getInputStream();
//		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(in));
//		StringBuffer temp = new StringBuffer();
//		
//		String line = bufferedreader.readLine();
//		while (line!=null)
//		{
//			temp.append(line).append("");
//			line = bufferedreader.readLine();
//		}
//		
//		json = JSONObject.parseObject(temp.toString());
//		
//		System.out.println("json is :" + json);
//		System.out.println(temp);
//		
//		urlConnection.disconnect();
//	}
}
