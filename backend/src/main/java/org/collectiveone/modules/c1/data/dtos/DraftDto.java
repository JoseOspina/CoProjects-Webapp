package org.collectiveone.modules.c1.data.dtos;

public class DraftDto {
	
	private String elementId;
	private DataDto data;
	
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public DataDto getData() {
		return data;
	}
	public void setData(DataDto data) {
		this.data = data;
	}
}