package cn.mldn.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class News implements Serializable {
	private long nid ;
	private String title ;
	private Date pubdate ;
	public long getNid() {
		return nid;
	}
	public void setNid(long nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
}
