package multi.client;

//이 클래스는 데이터베이스의 테이블 레코드 1건을 담기 위한 클래스 >dto

//언어 DB
//class Entity-테이블
//instance 레코드
//속성 컬럼

public class Chat {
	private int chat_id;
	private String name;
	private String ip;
	
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
