package com.oms.serverapi;

import java.util.ArrayList;
import java.util.Map;

import com.oms.bean.DigitalVideoDisc;

public interface IDvdApi {
	public ArrayList<DigitalVideoDisc> getDvds(Map<String, String> queryParams);
}
