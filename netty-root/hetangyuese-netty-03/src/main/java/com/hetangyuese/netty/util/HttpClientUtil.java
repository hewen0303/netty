package com.hetangyuese.netty.util;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * @Description: 
 * @author hewen
 * @date 2019/10/30 10:49 
*/
public class HttpClientUtil {

	/**
	 * post请求  JSON
	 * @param url 访问路径
	 * @param params 参数对象JSONStr
	 */
	public static String doPost(String url, String params) throws Exception {
		Header header = new BasicHeader("", "");
		Header[] headers = {header};
		return doPostByHeaders(url , params , headers);
	}


	/**
	 * get请求
	 * @return
	 */
	public static String doGet(String url) {
		try {
			HttpClient client = new DefaultHttpClient();
			//发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			/**请求发送成功，并得到响应**/
			int code = response.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				/**读取服务器返回过来的json字符串数据**/
				String strResult = EntityUtils.toString(response.getEntity());
				return strResult;
			}else{
				System.out.println("状态码：" + code);
				return null;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * post请求 自定义headers   JSON方式
	 * @param url 访问路径
	 * @param params 参数对象JSONStr
	 */
	public static String doPostByHeaders(String url, String params , Header[] headers) throws Exception {
		return doPostByContentType(url,params,headers,"application/json");
	}


	/**
	 * post请求  XML
	 * @param url 访问路径
	 * @param params 参数对象XMLStr
	 */
	public static String doPostToXml(String url, String params) throws Exception {
		Header header = new BasicHeader("", "");
		Header[] headers = {header};
		return doPostByHeadersToXml(url , params , headers);
	}

	/**
	 * post请求 自定义headers   XML方式
	 * @param url 访问路径
	 * @param params 参数对象XMLStr
	 */
	public static String doPostByHeadersToXml(String url, String params , Header[] headers) throws Exception {
		return doPostByContentType(url,params,headers,"application/xml");
	}



	/**
	 * post请求 自定义headers   根据ContentType类型
	 * @param url 访问路径
	 * @param params 参数对象JSONStr
	 * @param contentType
	 */
	public static String doPostByContentType(String url, String params , Header[] headers , String contentType) throws Exception {
		BufferedReader in = null;
		// 定义HttpClient
		HttpClient client = new DefaultHttpClient();
		// 实例化HTTP方法
		HttpPost request = new HttpPost();
		request.setURI(new URI(url));
		//设置参数
		StringEntity entity = new StringEntity(params,contentType, "UTF-8");
		request.setEntity(entity);
		request.setHeaders(headers);
		HttpResponse response = client.execute(request);
		int code = response.getStatusLine().getStatusCode();
		if(code == HttpStatus.SC_OK){
			//请求成功
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent(),"utf-8"));
			StringBuffer sb = new StringBuffer("");
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			return sb.toString();
		}else{
			System.out.println("状态码：" + code);
			return null;
		}
	}


}
