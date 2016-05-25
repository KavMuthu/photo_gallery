package muthu.assign3.entities;

import java.io.File;

public class Photo {
	private String title;
	private String category;
	private File filePath;
	private String date;
	private String desc;
	
	
	public Photo(){
		this("","",null,"","");
	}
	
	public Photo(String title, String category, File filePath, String date, String desc)
	{
		this.title = title;
		this.category = category;
		this.filePath = filePath;
		this.date = date;
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	public void setFilePath(File filePath){
		this.filePath = filePath;
	}
	public String getTitle(){
		return this.title;
	}
	
	public String getCategory(){
		return this.category;
	}
	public File getFilePath(){
		return this.filePath;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
}

