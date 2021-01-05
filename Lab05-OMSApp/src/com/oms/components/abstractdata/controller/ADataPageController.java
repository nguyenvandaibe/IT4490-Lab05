package com.oms.components.abstractdata.controller;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.oms.bean.Media;
import com.oms.components.abstractdata.gui.ADataListPane;
import com.oms.components.abstractdata.gui.ADataPagePane;
import com.oms.components.abstractdata.gui.ADataSearchPane;
import com.oms.components.abstractdata.gui.ADataSinglePane;
import com.oms.serverapi.BookApi;
import com.oms.serverapi.CdApi;
import com.oms.serverapi.DvdApi;
import com.oms.serverapi.IBookApi;
import com.oms.serverapi.ICdApi;
import com.oms.serverapi.IDvdApi;
import com.oms.serverapi.IMediaApi;
import com.oms.serverapi.MediaApi;

public abstract class ADataPageController<T> {
	private ADataPagePane<T> pagePane;

	protected IMediaApi mediaApi;
	protected IBookApi bookApi;
	protected ICdApi cdApi;
	protected IDvdApi dvdApi;

	public ADataPageController() {
		ADataSearchPane searchPane = createSearchPane();

		ADataListPane<T> listPane = createListPane();

		this.mediaApi = MediaApi.getInstance();
		bookApi = new BookApi();
		cdApi = new CdApi();
		dvdApi = new DvdApi();

		searchPane.setController(new IDataSearchController() {
			@Override
			public void search(Map<String, String> searchParams) {
				List<? extends T> list = ADataPageController.this.search(searchParams);
				listPane.updateData(list);
			}
		});

		searchPane.fireSearchEvent();

		pagePane = new ADataPagePane<T>(searchPane, listPane);
	}

	public JPanel getDataPagePane() {
		return pagePane;
	}

	public abstract ADataSearchPane createSearchPane();

	public abstract List<? extends T> search(Map<String, String> searchParams);

	public abstract ADataSinglePane<T> createSinglePane();

	public abstract ADataListPane<T> createListPane();
	
	public abstract void onEdit(ADataSinglePane<Media> singlePane);
}
