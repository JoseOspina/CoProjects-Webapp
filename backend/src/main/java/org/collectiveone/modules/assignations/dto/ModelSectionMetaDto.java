package org.collectiveone.modules.assignations.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.collectiveone.modules.files.FileStoredDto;
import org.collectiveone.modules.model.dto.ModelSectionTagDto;

public class ModelSectionMetaDto {
	
	private String name;
	private String driver;
	private FileStoredDto imageFile;
	private Timestamp creationDate;
	private String color;
	private Boolean modelEnabled;
	private String visibility;
	private List<ModelSectionTagDto> tags = new ArrayList<ModelSectionTagDto>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public FileStoredDto getImageFile() {
		return imageFile;
	}
	public void setImageFile(FileStoredDto imageFile) {
		this.imageFile = imageFile;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Boolean getModelEnabled() {
		return modelEnabled;
	}
	public void setModelEnabled(Boolean modelEnabled) {
		this.modelEnabled = modelEnabled;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public List<ModelSectionTagDto> getTags() {
		return tags;
	}
	public void setTags(List<ModelSectionTagDto> tags) {
		this.tags = tags;
	}
	
}
