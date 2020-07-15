package mypackage;
import java.util.LinkedList;

/*
 * wordlist class는 index.txt 생성 전 해시맵에 넣을 변수로, 파일의 이름과 검색된 단어 수, 경로를 다중적으로 포함하기 위한 class 
 */

public class wordlist {
	String textpath; // 단어가 들어간 텍스트 파일의 경로
	String name; // 텍스트파일의 이름
	int searchnum; // 이 텍스트 파일에 검색된 단어의 개수
	
	//생성자
	public wordlist(String s, String k, int t) {
		this.name=k;
		this.textpath=s;
		this.searchnum=t;
	}
	
	//searchnum 반환
	public int getsc(){
		return this.searchnum;
	}
	
	//경로반환
	public String gettp() {
		return this.textpath;
	}
	
	//파일명 반환
	public String getname() {
		return this.name;
	}
	
	//searchnum의 증가
	public void numberplus() {
		(this.searchnum)++;
	}
}
