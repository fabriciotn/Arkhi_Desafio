package com.arkhi.gxt3.shared.model;


public enum Tipo {
	TEXT("Text"), 
	DATE("Date");

	private String label;

	private Tipo(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
