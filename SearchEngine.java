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
M 파일량 N1 파일 내 단어량 , N2 전체 파일에 대한 단어량

해쉬맵에 단어정리까지의 시간
O(M*N1) = O(N2) 
+
하나씩 받아오면서 인덱스에작성하는 시간
O(N2)
+
인덱스에서 검색하는 시간
O(N2)
--> index를 해쉬맵에 넣는건  O(N2)을 소모하나, 프로그램이 종료되지 않아서 해쉬맵이 유지되는 경우 O(I) 
--> 여기선 프로그램이 종료되지만, 종료되지않고 계속 켜져있는 시스템이면 탐색시 한번 설정 후 O(I)까지 가능  
 */
public class SearchEngine {
	
	public static void main(String[] args) throws Exception {
		
		//FolderNmae의 경로 설정이 필요합니다. Fordername의 경로만 재설정 해주시면 됩니다
		//실제 탐색할 폴더와 index파일을 access하기 위한 과정입니다.
		String ForderName = "C:\\Users\\김규태\\Desktop\\test";
		String FileName = ForderName+"\\index.txt";
		
		// 폴더, 폴더 내 txt파일들, index.txt의 File variable
		File TargetForder = new File(ForderName);
		File IndexFile = new File(FileName); 
		File TargetList[] = TargetForder.listFiles();
		
		//여러 파일들을 읽어올때 사용 예정
		FileReader target = null;

		
		HashMap<String, LinkedList<wordlist>> WordCollect = new HashMap(); // 단어들을 저장할 해쉬맵
		HashMap<String, LinkedList<String>> WordSearch = new HashMap(); // 단어들을 저장할 해쉬맵
		LinkedList<wordlist> fileinfo = new LinkedList<wordlist>(); // 연결리스트, 한 노드에 텍스트경로와 단어 개수 저장
		
		// 인덱싱과 질의문 단계입력, 입력값을 저장할 매개변수
		Scanner sc = new Scanner(System.in);
		int input;

		// print 되는 문장을 보고 input을 입력하는 단계
		System.out.println("1:인덱싱 단계           //      2:질의문 단계");
		input=sc.nextInt();
		
		if(input==1){ // 인덱싱 단계 선택
			FileWriter index = new FileWriter(IndexFile,true); // 인덱싱 하기위한 FileWriter
			
			 for(File f: TargetList){ // 타겟파일들을 하나씩 읽어온다.
				 	target = new FileReader(f);
		            BufferedReader inFile = new BufferedReader(target);
		            
		            String path = f.getPath();
		            String name = f.getName();
		            String OneLine = "";
		            String word;
		            int plus=1;
		            
		            while((OneLine = inFile.readLine()) != null) { // 버퍼를 이용해 파일의 한줄을 끝까지 받아온다.
		            	StringTokenizer tokenizer = new StringTokenizer(OneLine); // 받아온 한줄에서도 띄어쓰기를 위해 토큰에 넣는다.
		            	
		            	do { // 한줄이 끝날때까지 한 단어씩 받아온다.
		            		word=tokenizer.nextToken();
		            		
		                	if(WordCollect.containsKey(word)) { // 단어가 해쉬맵에 존재한다면
		                		
		                		//연결리스트의 헤드의 파일과 현재진행중인 파일이 같은 파일인지 확인한다.
		                		if(WordCollect.get(word).getFirst().gettp()!=path) {  // 단어는 같은데다른파일이라면, 리스트에 새로운 wordlist 추가
		                			fileinfo=WordCollect.get(word);
				            		wordlist wl=new wordlist(path,name,plus);
				            		fileinfo.add(wl);
				            		WordCollect.put(word,fileinfo);
		                		}
		                		
		                		else // 단어같고 같은파일이면 개수 증가만 하면된다.
		                			WordCollect.get(word).getFirst().numberplus();
		                		
		                	}
		                	
		                	else { //그단어가 없으면 새로운 연결리스트를 할당받고 해쉬맵에 넣는다.
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
			 
			 //모든파일의 설정이 끝나고 index.txt에 쓰는 과정이다.
			 for(String key:WordCollect.keySet()) { // 해쉬맵 내에 있는 모든 key를 호출한다.
				 //단어, 이 단어가 있는 파일의 개수, 이 단어가 검색된 개수, (파일명, 이파일에서 검색된 개수 ,.,.,.,. )
				 int sum=0;
				 index.write(key+" "); 
				 index.write(WordCollect.get(key).size()+" ");
				 for(wordlist wlist:WordCollect.get(key))
					 sum+=wlist.getsc();
				 index.write(sum+" ");
				 for(wordlist wlist:WordCollect.get(key))
					 index.write(wlist.getname()+" "+wlist.getsc()+" ");
				 index.write("\r\n"); // 한 단어 끝나면 다음 줄
				 
			 }
			 index.close();
		}
		
		
		else if(input==2){ // 질의문 탐색
			
			String SearchWord; // 검색단어
			
			if(!IndexFile.exists()) // 타겟파일이 존재하지 않을 경우
				System.out.println("인덱싱단계 진행 후 접근이 가능합니다. 다시 실행하세요.");
			
			else {
				System.out.println("질의문을 입력해주세요.");
				SearchWord=sc.next(); // 탐색단어 입력
				
				// 타겟은 index.txt
			 	target = new FileReader(IndexFile);
	            BufferedReader inFile = new BufferedReader(target);
	            
	            String OneLine="";
	            String Word;
	            String name;
	            int search=1; // 탐색된 경로의 번호 매김 
	            
	            StringTokenizer tok = null;
	            
	            //index의 단어들을 파일이름만 포함해서 해시맵에 올림
	            while((OneLine = inFile.readLine()) != null) { // 한줄 받아오기
	            	tok = new StringTokenizer(OneLine); // 한줄의 스트링토큰
	            	
	            	//SearchCollect 해쉬맵의 연결리스트는 스트링
	            	LinkedList<String> names=new LinkedList<String>();
	            	Word=tok.nextToken(); // 단어
	            	
	            	//검색 단어 뒤에 숫자 두개 생략 위해
	            	tok.nextToken(); 
	            	tok.nextToken();
	            	
	            	do {
		            	name=tok.nextToken(); // 파일 이름
		            	tok.nextToken(); // 개수 생략
		            	names.add(name);
	            	}while(tok.hasMoreTokens());
	            	
	            	WordSearch.put(Word,names);
	            }
	            
	            //검색결과 찾는 단어가 있다면
	            if(WordSearch.containsKey(SearchWord)) {
	            	System.out.println("----------------------------------------");
	            	System.out.println("\""+SearchWord+"\"의 검색 결과 "+WordSearch.get(SearchWord).size()+"건이 검색되었습니다.");
	            	do {
	            		
	            		// 단어가 들어간 파일의 탐색을위한 준비과정
	            		name=WordSearch.get(SearchWord).removeFirst();
	            		target=new FileReader(ForderName+"\\"+name);
	            		inFile= new BufferedReader(target);
	            		System.out.println("("+search+") "+name);
	            		
	            		//두줄 출력하는데 만약 그 줄이 null이면 그냥 스탑	
	            		for(int i=0;i<2;i++) {
	            			OneLine=inFile.readLine();
	            			if(OneLine==null)
	            				break;
	            			System.out.println(OneLine);
	            		}
        			
	            		//다음 파일탐색위해 띄우고 번호 올려주기
	            		System.out.println();
	            		search++;
        			
	            	}while(!WordSearch.get(SearchWord).isEmpty());
	            	
            		System.out.println("검색이 완료되었습니다.");
            		System.out.println("----------------------------------------");
	            }
	            
	            else { // 검색이 되지 않았다면
	            	System.out.println("----------------------------------------");
	            	System.out.println("\""+SearchWord+"\"의 검색 결과 0건이 검색되었습니다.");
	            	System.out.println("----------------------------------------");
	            }
	       
	            target.close();
	            inFile.close();
			}
			
			
		}
		
		
		
		else // 1,2 외에 이상한 접근을 시도할 시
			System.out.println("올바른 접근이 아닙니다. 다시 실행해주세요.");
		
		//할당 해제
		 fileinfo.clear();
		 WordCollect.clear();
		 WordSearch.clear();
		 sc.close();
	}
}
