package com.saucedemo.com.saucedemo.utils;

import com.saucelabs.saucerest.SauceREST;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class SauceUtils
{
	private SauceREST api;

	public SauceUtils(SauceREST sauceREST)
	{
		api = sauceREST;
	}

	public void updateResults(boolean testResults, String sessionId) throws JSONException
	{
		Map<String, Object> updates = new HashMap<>();
		updates.put("passed", testResults);
		api.updateJobInfo(sessionId, updates);
	}
}