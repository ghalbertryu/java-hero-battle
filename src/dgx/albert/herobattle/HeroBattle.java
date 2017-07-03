package dgx.albert.herobattle;
import java.io.*;

import dgx.albert.character.Character;
import dgx.albert.io.IOmethod;
import dgx.albert.stage.Stage;

public class HeroBattle {
	//print and write tool
	public static String formatedStringTmp =""; 
	public static FileWriter fw = IOmethod.createFile(".\\process.txt");
	//setting initial
	final static int numOfGroup = 10;
	
	public static void main(String[] args) throws IOException {
		//生成隊伍 n vs n
		Character[] hero = Operation.initial(numOfGroup);
		//set stage
		Stage stageNow = Operation.stageSet();
		Operation.stageImapct(stageNow, hero);
		
		int moveCount = 0;
		while(true){
			//檢查全滅
			if(Operation.checkLight(hero) || Operation.checkDark(hero)) break;
			//按照dex順序隨機施放技能
			Operation.randamSkill(Operation.nextMove(hero), hero);
			//每回合結束stage change
			if(moveCount == numOfGroup*2-1){
				stageNow = Operation.stageChange(stageNow);
				Operation.stageImapct(stageNow, hero);
				moveCount = 0;
				continue;
			}
			moveCount++;
		}
		formatedStringTmp = String.format("----------回合結束---------\r\n");
		IOmethod.printFile(HeroBattle.formatedStringTmp);
		//顯示結果及屬性
		for(int i =0 ;i < hero.length;i++)
		hero[i].showChar();
		//close FileWriter
		fw.close();
	}
}
