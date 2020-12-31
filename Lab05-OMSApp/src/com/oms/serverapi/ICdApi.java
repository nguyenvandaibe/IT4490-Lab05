package com.oms.serverapi;

import java.util.ArrayList;
import java.util.Map;

import com.oms.bean.CompactDisc;

public interface ICdApi {
	public ArrayList<CompactDisc> getCds(Map<String, String> queryParams);
}
