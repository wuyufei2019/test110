package com.cczu.util.entity;

public class ISSUE_SecurityFileReleaseEntityUtil {
	public long ID;// 编号 ID

	private String Title;// 文件标题

	private String UpdateTime;// 更新时间	

	private int S3;// 删除标识	

	private int M1;// 是否查看：0未查看，1已查看	

	private int M2;// 是否下载：0未下载，1已下载
	
	private String M4;// 备注
	
	private String DownTime;// 下载时间

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}

	public int getS3() {
		return S3;
	}

	public void setS3(int s3) {
		S3 = s3;
	}

	public int getM1() {
		return M1;
	}

	public void setM1(int m1) {
		M1 = m1;
	}

	public int getM2() {
		return M2;
	}

	public void setM2(int m2) {
		M2 = m2;
	}

	public String getDownTime() {
		return DownTime;
	}

	public void setDownTime(String downTime) {
		DownTime = downTime;
	}

	public String getM4() {
		return M4;
	}

	public void setM4(String m4) {
		M4 = m4;
	}
	
	
}
