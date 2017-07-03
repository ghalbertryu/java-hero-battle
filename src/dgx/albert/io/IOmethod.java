package dgx.albert.io;

import java.io.*;

import dgx.albert.character.Character;
import dgx.albert.herobattle.HeroBattle;

public class IOmethod {
	//printFlie
	public static void printFile(String string){
		System.out.print(string);
		try {
			HeroBattle.fw.write(string);
		} catch (IOException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
	}
	
	//CreateFile
	public static FileWriter createFile(String filePath){
		File process = new File(filePath);
		if(!process.exists()){
			try {
				process.createNewFile();
			} catch (IOException e) {
				// TODO 自動產生的 catch 區塊
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(process);
			return fw;
		} catch (IOException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
		return null;
	}
	
	//txt裡面的字串讀進array
	public static String[] fileToArray(String filePath) throws IOException{
		StringBuilder content = new StringBuilder("");
		
		File txtFile = new File(filePath);
		FileReader frtxtFile = new FileReader(txtFile);
		BufferedReader bftxtFile = new BufferedReader(frtxtFile);
		
		String str = "";
		while((str=bftxtFile.readLine())!=null){
			content.append(str);
		}
		
		bftxtFile.close();
		frtxtFile.close();
		
		String tmp = content.substring(0, content.length());
		String[] array = tmp.split(",\\s*");
		return array;
	}

	//兩個字串陣列隨機組合一組新的全名
	public static String ranName(String[] prefix, String[] suffix, Character[] hero, int n){
		String fullName="";
		fullName = fullName.concat(prefix[(int)(Math.random()*prefix.length)]);
		fullName = fullName.concat(suffix[(int)(Math.random()*suffix.length)]);

		//檢查重複命名
		for(int i = 0; i<n ;i++){
			if(fullName.equals(hero[i].name)){
				return ranName(prefix, suffix, hero, n);
			}
		}
		return fullName;	
	}
	
}
