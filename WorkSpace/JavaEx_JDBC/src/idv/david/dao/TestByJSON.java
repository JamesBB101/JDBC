package idv.david.dao;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

// 使用JSON官方套件進行JSON資料解析
public class TestByJSON {
	private static final String URL = "http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=51839ac9-6454-49f2-aaf2-abd05c5405c8";
	
	public static void main(String[] args) throws Exception {
		InputStream is = new URL(URL).openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String str;
		while((str = br.readLine()) != null) {
			sb.append(str);
		}
		
		JSONObject object = new JSONObject(sb.toString());
		JSONObject result = object.getJSONObject("result");
		JSONArray array = result.getJSONArray("results");
		List<House> houseList = new ArrayList<>();
		for(int i = 0; i < array.length(); i++) {
			JSONObject j = array.getJSONObject(i);
			int _id = Integer.parseInt(j.getString("_id"));
			String case_t = j.getString("CASE_T");
			String district = j.getString("DISTRICT");
			String case_f = j.getString("CASE_F");
			String location = j.getString("LOCATION");
			House house = new House(_id, case_t, district, case_f, location);
			houseList.add(house);
		}
		
		HouseDAOImpl dao = new HouseDAOImpl();
		System.out.println(houseList.size());
		dao.addAll(houseList);
		System.out.println("Done!");
	}

}
