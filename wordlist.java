package mypackage;
import java.util.LinkedList;

/*
 * wordlist class�� index.txt ���� �� �ؽøʿ� ���� ������, ������ �̸��� �˻��� �ܾ� ��, ��θ� ���������� �����ϱ� ���� class 
 */

public class wordlist {
	String textpath; // �ܾ �� �ؽ�Ʈ ������ ���
	String name; // �ؽ�Ʈ������ �̸�
	int searchnum; // �� �ؽ�Ʈ ���Ͽ� �˻��� �ܾ��� ����
	
	//������
	public wordlist(String s, String k, int t) {
		this.name=k;
		this.textpath=s;
		this.searchnum=t;
	}
	
	//searchnum ��ȯ
	public int getsc(){
		return this.searchnum;
	}
	
	//��ι�ȯ
	public String gettp() {
		return this.textpath;
	}
	
	//���ϸ� ��ȯ
	public String getname() {
		return this.name;
	}
	
	//searchnum�� ����
	public void numberplus() {
		(this.searchnum)++;
	}
}
