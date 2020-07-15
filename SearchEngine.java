package mypackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
M ���Ϸ� N1 ���� �� �ܾ , N2 ��ü ���Ͽ� ���� �ܾ

�ؽ��ʿ� �ܾ����������� �ð�
O(M*N1) = O(N2) 
+
�ϳ��� �޾ƿ��鼭 �ε������ۼ��ϴ� �ð�
O(N2)
+
�ε������� �˻��ϴ� �ð�
O(N2)
--> index�� �ؽ��ʿ� �ִ°�  O(N2)�� �Ҹ��ϳ�, ���α׷��� ������� �ʾƼ� �ؽ����� �����Ǵ� ��� O(I) 
--> ���⼱ ���α׷��� ���������, ��������ʰ� ��� �����ִ� �ý����̸� Ž���� �ѹ� ���� �� O(I)���� ����  
 */
public class SearchEngine {
	
	public static void main(String[] args) throws Exception {
		
		//FolderNmae�� ��� ������ �ʿ��մϴ�. Fordername�� ��θ� �缳�� ���ֽø� �˴ϴ�
		//���� Ž���� ������ index������ access�ϱ� ���� �����Դϴ�.
		String ForderName = "C:\\Users\\�����\\Desktop\\test";
		String FileName = ForderName+"\\index.txt";
		
		// ����, ���� �� txt���ϵ�, index.txt�� File variable
		File TargetForder = new File(ForderName);
		File IndexFile = new File(FileName); 
		File TargetList[] = TargetForder.listFiles();
		
		//���� ���ϵ��� �о�ö� ��� ����
		FileReader target = null;

		
		HashMap<String, LinkedList<wordlist>> WordCollect = new HashMap(); // �ܾ���� ������ �ؽ���
		HashMap<String, LinkedList<String>> WordSearch = new HashMap(); // �ܾ���� ������ �ؽ���
		LinkedList<wordlist> fileinfo = new LinkedList<wordlist>(); // ���Ḯ��Ʈ, �� ��忡 �ؽ�Ʈ��ο� �ܾ� ���� ����
		
		// �ε��̰� ���ǹ� �ܰ��Է�, �Է°��� ������ �Ű�����
		Scanner sc = new Scanner(System.in);
		int input;

		// print �Ǵ� ������ ���� input�� �Է��ϴ� �ܰ�
		System.out.println("1:�ε��� �ܰ�           //      2:���ǹ� �ܰ�");
		input=sc.nextInt();
		
		if(input==1){ // �ε��� �ܰ� ����
			FileWriter index = new FileWriter(IndexFile,true); // �ε��� �ϱ����� FileWriter
			
			 for(File f: TargetList){ // Ÿ�����ϵ��� �ϳ��� �о�´�.
				 	target = new FileReader(f);
		            BufferedReader inFile = new BufferedReader(target);
		            
		            String path = f.getPath();
		            String name = f.getName();
		            String OneLine = "";
		            String word;
		            int plus=1;
		            
		            while((OneLine = inFile.readLine()) != null) { // ���۸� �̿��� ������ ������ ������ �޾ƿ´�.
		            	StringTokenizer tokenizer = new StringTokenizer(OneLine); // �޾ƿ� ���ٿ����� ���⸦ ���� ��ū�� �ִ´�.
		            	
		            	do { // ������ ���������� �� �ܾ �޾ƿ´�.
		            		word=tokenizer.nextToken();
		            		
		                	if(WordCollect.containsKey(word)) { // �ܾ �ؽ��ʿ� �����Ѵٸ�
		                		
		                		//���Ḯ��Ʈ�� ����� ���ϰ� ������������ ������ ���� �������� Ȯ���Ѵ�.
		                		if(WordCollect.get(word).getFirst().gettp()!=path) {  // �ܾ�� �������ٸ������̶��, ����Ʈ�� ���ο� wordlist �߰�
		                			fileinfo=WordCollect.get(word);
				            		wordlist wl=new wordlist(path,name,plus);
				            		fileinfo.add(wl);
				            		WordCollect.put(word,fileinfo);
		                		}
		                		
		                		else // �ܾ�� ���������̸� ���� ������ �ϸ�ȴ�.
		                			WordCollect.get(word).getFirst().numberplus();
		                		
		                	}
		                	
		                	else { //�״ܾ ������ ���ο� ���Ḯ��Ʈ�� �Ҵ�ް� �ؽ��ʿ� �ִ´�.
		                		fileinfo = new LinkedList<wordlist>();
			            		wordlist wl=new wordlist(path,name,plus);
			            		fileinfo.add(wl);
			            		WordCollect.put(word,fileinfo);
		                	}
		                	
		            	}while(tokenizer.hasMoreTokens());
		            }
		            inFile.close();
		            target.close();
			 }
			 
			 //��������� ������ ������ index.txt�� ���� �����̴�.
			 for(String key:WordCollect.keySet()) { // �ؽ��� ���� �ִ� ��� key�� ȣ���Ѵ�.
				 //�ܾ�, �� �ܾ �ִ� ������ ����, �� �ܾ �˻��� ����, (���ϸ�, �����Ͽ��� �˻��� ���� ,.,.,.,. )
				 int sum=0;
				 index.write(key+" "); 
				 index.write(WordCollect.get(key).size()+" ");
				 for(wordlist wlist:WordCollect.get(key))
					 sum+=wlist.getsc();
				 index.write(sum+" ");
				 for(wordlist wlist:WordCollect.get(key))
					 index.write(wlist.getname()+" "+wlist.getsc()+" ");
				 index.write("\r\n"); // �� �ܾ� ������ ���� ��
				 
			 }
			 index.close();
		}
		
		
		else if(input==2){ // ���ǹ� Ž��
			
			String SearchWord; // �˻��ܾ�
			
			if(!IndexFile.exists()) // Ÿ�������� �������� ���� ���
				System.out.println("�ε��̴ܰ� ���� �� ������ �����մϴ�. �ٽ� �����ϼ���.");
			
			else {
				System.out.println("���ǹ��� �Է����ּ���.");
				SearchWord=sc.next(); // Ž���ܾ� �Է�
				
				// Ÿ���� index.txt
			 	target = new FileReader(IndexFile);
	            BufferedReader inFile = new BufferedReader(target);
	            
	            String OneLine="";
	            String Word;
	            String name;
	            int search=1; // Ž���� ����� ��ȣ �ű� 
	            
	            StringTokenizer tok = null;
	            
	            //index�� �ܾ���� �����̸��� �����ؼ� �ؽøʿ� �ø�
	            while((OneLine = inFile.readLine()) != null) { // ���� �޾ƿ���
	            	tok = new StringTokenizer(OneLine); // ������ ��Ʈ����ū
	            	
	            	//SearchCollect �ؽ����� ���Ḯ��Ʈ�� ��Ʈ��
	            	LinkedList<String> names=new LinkedList<String>();
	            	Word=tok.nextToken(); // �ܾ�
	            	
	            	//�˻� �ܾ� �ڿ� ���� �ΰ� ���� ����
	            	tok.nextToken(); 
	            	tok.nextToken();
	            	
	            	do {
		            	name=tok.nextToken(); // ���� �̸�
		            	tok.nextToken(); // ���� ����
		            	names.add(name);
	            	}while(tok.hasMoreTokens());
	            	
	            	WordSearch.put(Word,names);
	            }
	            
	            //�˻���� ã�� �ܾ �ִٸ�
	            if(WordSearch.containsKey(SearchWord)) {
	            	System.out.println("----------------------------------------");
	            	System.out.println("\""+SearchWord+"\"�� �˻� ��� "+WordSearch.get(SearchWord).size()+"���� �˻��Ǿ����ϴ�.");
	            	do {
	            		
	            		// �ܾ �� ������ Ž�������� �غ����
	            		name=WordSearch.get(SearchWord).removeFirst();
	            		target=new FileReader(ForderName+"\\"+name);
	            		inFile= new BufferedReader(target);
	            		System.out.println("("+search+") "+name);
	            		
	            		//���� ����ϴµ� ���� �� ���� null�̸� �׳� ��ž	
	            		for(int i=0;i<2;i++) {
	            			OneLine=inFile.readLine();
	            			if(OneLine==null)
	            				break;
	            			System.out.println(OneLine);
	            		}
        			
	            		//���� ����Ž������ ���� ��ȣ �÷��ֱ�
	            		System.out.println();
	            		search++;
        			
	            	}while(!WordSearch.get(SearchWord).isEmpty());
	            	
            		System.out.println("�˻��� �Ϸ�Ǿ����ϴ�.");
            		System.out.println("----------------------------------------");
	            }
	            
	            else { // �˻��� ���� �ʾҴٸ�
	            	System.out.println("----------------------------------------");
	            	System.out.println("\""+SearchWord+"\"�� �˻� ��� 0���� �˻��Ǿ����ϴ�.");
	            	System.out.println("----------------------------------------");
	            }
	       
	            target.close();
	            inFile.close();
			}
			
			
		}
		
		
		
		else // 1,2 �ܿ� �̻��� ������ �õ��� ��
			System.out.println("�ùٸ� ������ �ƴմϴ�. �ٽ� �������ּ���.");
		
		//�Ҵ� ����
		 fileinfo.clear();
		 WordCollect.clear();
		 WordSearch.clear();
		 sc.close();
	}
}
