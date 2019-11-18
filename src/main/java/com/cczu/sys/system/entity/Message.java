package com.cczu.sys.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cczu.sys.comm.utils.DateUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 即时消息推送表
 * @author jason
 * @date 2017年10月11日
 */
@Entity
@Table(name="t_msg")
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3623104689629734943L;
	public static  final String MSGTARGET_H5 = "H5";
	public static  final  String MSGTARGET_PC = "PC";
	public  enum MessageType{
		/**
		 * 类型说明:
		 * wx  微信端专用
		 * dsh 待审核信息
		 * dsp 待审批信息
		 * djc 待检查信息
		 * xwj 新文件待查看信息
		 * pxtz 培训通知信息
		 * aqhy 安全会议
		 * lbyp 劳保用品
		 * dxj 待巡检
		 * dcwj 调查问卷
		 * dzg 待整改
		 */
		WX("wx"),DSH("dsh"),DSP("dsp"),DJC("djc"),XWJ("xwj"),PXTZ("pxtz"),AQHY("aqhy"),LBYP("lbyp"),DXJ("dxj"),DCWJ("dcwj"),DZG("dzg");
		private String typename;
		MessageType(String type){
			this.typename=type;
		}

		public String getMsgType() {
			return this.typename;
		}  
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "CONTENT", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String CONTENT ;//消息正文
	
	@Column(name = "TOUSER", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String TOUSER;//收件人
	
	@Column(name = "FROMUSER", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String FROMUSER;//发件人
	
	@Column(name = "SCHSENDTIME", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp SCHSENDTIME;//定时发送时间
	
	@Column(name = "SENDSTATUE", nullable = true, columnDefinition="varchar(1) default '0'")
	@Setter
	@Getter
	private String SENDSTATUE;//发送状态 1、已读 0、未读
	
	@Column(name = "CREATETIME", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp CREATETIME;//建立时间
	
	@Column(name = "SUCCESSTIME", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp SUCCESSTIME;//发送完成时间
	
	@Column(name = "TITLE", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String TITLE;//消息标题
	
	/**
	 * 类型说明:
	 * wx  微信端专用
	 * dsh 待审核信息
	 * dsp 待审批信息
	 * djc 待检查信息
	 * xwj 新文件待查看信息
	 * pxtz 培训通知信息
	 * aqhy 安全会议
	 * lbyp 劳保用品
	 * dxj 待巡检
	 * dcwj 调查问卷
	 */
	@Column(name = "MSGTYPE", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String MSGTYPE ;//消息类型
	
	@Column(name = "URL", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String  URL;//点击消息体请求地址
	
	public Message(){}
	
	public Message(msgBuilder build){
		this.TOUSER=build.TOUSER;
		this.FROMUSER=build.FROMUSER;
		this.TITLE=build.TITLE;
		this.MSGTYPE=build.MSGTYPE;
		this.URL=build.URL;
		this.CONTENT=build.CONTENT;
		this.SCHSENDTIME=build.SCHSENDTIME;
		this.SUCCESSTIME=build.SUCCESSTIME;
		this.CREATETIME=build.CREATETIME;
		this.SENDSTATUE=build.SENDSTATUE;
	}
	 
	public Message(String TOUSER,String FROMUSER,String title,String MSGTYPE,String CONTENT){
		this.TOUSER=TOUSER;
		this.FROMUSER=FROMUSER;
		this.MSGTYPE=MSGTYPE;
		this.TITLE=title;
		this.CONTENT=CONTENT;
	}
	
	public Message(String TOUSER,String FROMUSER,String TITLE,String MSGTYPE,String CONTENT, String url){
		this(TOUSER,FROMUSER,TITLE,MSGTYPE,CONTENT);
		this.URL=url;
	}
	
	public Message(String TOUSER,String FROMUSER,String TITLE,String MSGTYPE,String CONTENT, String url,Timestamp SCHSENDTIME,Timestamp SUCCESSTIME){
		this(TOUSER,FROMUSER,TITLE,MSGTYPE,CONTENT,url);
		this.SCHSENDTIME=SCHSENDTIME;
		this.SUCCESSTIME=SUCCESSTIME;
	}
	 public static class msgBuilder {
		 
			private String CONTENT ;//消息正文
			
			private String TOUSER;//收件人
			
			private String FROMUSER;//发件人
			
			private Timestamp SCHSENDTIME ;//定时发送时间
			
			private String SENDSTATUE ="0";//发送状态 1、已读 0、未读
			
			private Timestamp CREATETIME =DateUtils.getSystemTime();//建立时间
			
			private Timestamp SUCCESSTIME;//发送完成时间
			
			private String TITLE;//消息标题
			
			private String MSGTYPE ;//消息类型
			
			private String  URL;//点击消息体请求地址
			
			public msgBuilder(){}
			
			public msgBuilder(String TOUSER,String TITLE){
				this.TOUSER = TOUSER;
				this.TITLE = TITLE;
			}
			public msgBuilder setFromUser(String FROMUSER){
				this.FROMUSER = FROMUSER;
				return this;
			}
			public msgBuilder setContent(String CONTENT){
				this.CONTENT = CONTENT;
				return this;
			}
			public msgBuilder setMsgtype(String MSGTYPE){
				this.MSGTYPE = MSGTYPE;
				return this;
			}
			public msgBuilder setUrl(String URL){
				this.URL = URL;
				return this;
			}
			public msgBuilder setContent(Timestamp SCHSENDTIME){
				this.SCHSENDTIME = SCHSENDTIME;
				return this;
			}
			public msgBuilder setSuccessTime(Timestamp SUCCESSTIME){
				this.SUCCESSTIME = SUCCESSTIME;
				return this;
			}
			public Message build(){
				return new Message(this);
			}
		 
	 }
}
