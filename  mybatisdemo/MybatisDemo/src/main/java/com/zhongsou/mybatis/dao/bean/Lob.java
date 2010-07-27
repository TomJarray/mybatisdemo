package com.zhongsou.mybatis.dao.bean;

public class Lob extends PO{
	
	private static final long serialVersionUID = -4745420754586682312L;
	
	private Long lob_id;
	private byte[] lob_blob;
	private String lob_text;
	public Long getLob_id() {
		return lob_id;
	}
	public void setLob_id(Long lobId) {
		lob_id = lobId;
	}
	public byte[] getLob_blob() {
		return lob_blob;
	}
	public void setLob_blob(byte[] lobBlob) {
		lob_blob = lobBlob;
	}
	public String getLob_text() {
		return lob_text;
	}
	public void setLob_text(String lobText) {
		lob_text = lobText;
	}
}
