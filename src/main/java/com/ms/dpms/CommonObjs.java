package com.ms.dpms;

import javafx.scene.layout.VBox;

public class CommonObjs 
{
	private static CommonObjs commonObjs = new CommonObjs();
	
	private CommonObjs() {}
	
	private VBox mainBox;
	
	public static CommonObjs getInstance() {
		return commonObjs;
	}

	public static CommonObjs getCommonObjs() {
		return commonObjs;
	}

	public static void setCommonObjs(CommonObjs commonObjs) {
		CommonObjs.commonObjs = commonObjs;
	}

	public VBox getMainBox() {
		return mainBox;
	}

	public void setMainBox(VBox mainBox) {
		this.mainBox = mainBox;
	}	
}
