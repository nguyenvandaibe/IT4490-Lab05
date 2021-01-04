package com.oms.components.media.controller;

import com.oms.bean.Media;
import com.oms.components.abstractdata.controller.ADataPageController;
import com.oms.components.abstractdata.controller.IDataManageController;
import com.oms.components.abstractdata.gui.ADataListPane;
import com.oms.components.abstractdata.gui.ADataSinglePane;
import com.oms.components.media.book.gui.BookEditDialog;
import com.oms.components.media.gui.AdminMediaListPane;

public abstract class AdminMediaPageController extends ADataPageController<Media> {
	public AdminMediaPageController() {
		super();
	}
	
	@Override
	public ADataListPane<Media> createListPane() {
		return new AdminMediaListPane(this);
	}
	
	public abstract Media updateMedia(Media media);
	
	public void onEdit(ADataSinglePane<Media> singlePane) {
		AdminMediaPageController controller = this;
		IDataManageController<Media> manageController = new IDataManageController<Media>() {
			@Override
			public void onAct(Media t) {
				Media newMedia = controller.updateMedia(t);
				singlePane.updateData(newMedia);
			}
		};
		
		new BookEditDialog(singlePane.getData(), manageController);
	}
}
