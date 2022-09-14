package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonReader {
	private static JSONObject rcbTeam;
	private static List<String> values = new ArrayList();

	public static JSONObject getJSON() throws IOException {

		File file = new File("RcbTeam.json");
		String teamFile = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		rcbTeam = new JSONObject(teamFile);
		return rcbTeam;
	}

	public static List<String> getKey(JSONObject rcbTeam, String key) {

		boolean exists = rcbTeam.has(key);
		Iterator<?> keys;
		String nextKeys;
		if (!exists) {
			keys = rcbTeam.keys();
			while (keys.hasNext()) {
				nextKeys = (String) keys.next();
				try {
					if (rcbTeam.get(nextKeys) instanceof JSONObject) {
						if (exists == false) {
							getKey(rcbTeam.getJSONObject(nextKeys), key);
						}
					} else if (rcbTeam.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonarray = rcbTeam.getJSONArray(nextKeys);
						for (int i = 0; i < jsonarray.length(); i++) {
							String jsonarrayString = jsonarray.get(i).toString();
							JSONObject innerJSOn = new JSONObject(jsonarrayString);
							if (exists == false) {
								getKey(innerJSOn, key);
							}
						}
					}
				} catch (Exception e) {
				}
			}
		} else {

			values.add(String.valueOf(rcbTeam.get(key)));

		}

		return values;
	}

}
