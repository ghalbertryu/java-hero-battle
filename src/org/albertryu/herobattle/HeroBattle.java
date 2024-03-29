package org.albertryu.herobattle;
import java.io.*;

import org.albertryu.herobattle.constant.StageEnum;
import org.albertryu.herobattle.service.Operation;
import org.albertryu.herobattle.vo.Character;
import org.albertryu.herobattle.utils.FileUtils;

public class HeroBattle {
	//print and write tool & initial parameter
	public static String formatedStringTmp =""; 
	public static final String processPath = "./resources/process.txt";
	public static final String prefixDarkPath = "./resources/prefixDark.txt";
	public static final String prefixLightPath = "./resources/prefixLight.txt";
	public static final String suffixNamePath = "./resources/suffixName.txt";
	final static int numOfGroup = 10;
	//setting
	public static FileWriter fw = FileUtils.createFile(processPath);
	
	public static void main(String[] args) throws IOException {
		//生成隊伍 n vs n
		Character[] hero = Operation.initial(numOfGroup);
		//set stage
		StageEnum stageNow = Operation.stageSet();
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
		FileUtils.printFile(HeroBattle.formatedStringTmp);
		//顯示結果及屬性
		for(int i =0 ;i < hero.length;i++)
		hero[i].showChar();
		//close FileWriter
		fw.close();
	}
}
