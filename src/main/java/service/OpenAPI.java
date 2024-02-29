package service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import dao.DBConnection;


public class OpenAPI {
	// 공공 데이터 URL
	private final String mstr_ApiUrl = "http://openapi.seoul.go.kr:8088/4f53676543746f7439367869614779/json/TbPublicWifiInfo";
	private OkHttpClient ok_Client = new OkHttpClient();
	
	// 공공 와이파이 저장 
	public int open_Wifi_Save() throws UnsupportedEncodingException{
		// 공공와이파이 총수량 
		int i_Cnt = open_Wifi_Cnt(mstr_ApiUrl);
		// DB저장한 총수량
		int i_DB_Save_Cnt = 0;
		DBConnection Dbc = new DBConnection();
		
		// 공공와이파이 만큼 1000씩 증가하면서 반복 -- 데이터가 1000개씩 받아와지기 떄문에 1000개씩 증가
		for(int i = 0; i < i_Cnt; i += 1000) {
			// 공공 데이터 URL 할당
			StringBuilder sb_Url = new StringBuilder(mstr_ApiUrl);
			// 데이터 조회 시작번호
			sb_Url.append("/" + URLEncoder.encode(Integer.toString(i + 1),"UTF-8")); 
			// 데이터 조회 마지막번호
			sb_Url.append("/" + URLEncoder.encode(Integer.toString(i + 1000),"UTF-8")); 
			
			Request.Builder builder = new Request.Builder().url(sb_Url.toString()).get();
			builder.addHeader("Content-type", "application/json");
			Request request = builder.build();
			
		
			try {
				Response response = ok_Client.newCall(request).execute();
				String responseString = response.body().string();
				JsonObject json_Obj = JsonParser.parseString(responseString).getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
				JsonArray json_ArrRow = (JsonArray) json_Obj.get("row");
				i_DB_Save_Cnt += Dbc.wifi_Save(json_ArrRow);
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
		}
		
		if(i_DB_Save_Cnt != i_Cnt) {
			i_DB_Save_Cnt = -1;
		}
		
		return i_DB_Save_Cnt;
	}
	
	
	// 공공 와이파이 총수량 조회
	public int open_Wifi_Cnt(String str_Url) {
		str_Url += "/1/1";
		Request.Builder builder = new Request.Builder().url(str_Url).get();
		builder.addHeader("Content-type", "application/json");
		Request request = builder.build();
		
		try {
			Response response = ok_Client.newCall(request).execute();
			String responseString = response.body().string();
		
			return JsonParser.parseString(responseString).getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject().get("list_total_count").getAsInt();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}
}