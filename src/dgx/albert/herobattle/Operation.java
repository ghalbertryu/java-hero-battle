package dgx.albert.herobattle;

import java.io.IOException;

import dgx.albert.character.Character;
import dgx.albert.chardark.CharDark;
import dgx.albert.charlight.CharLight;
import dgx.albert.io.IOmethod;
import dgx.albert.stage.Stage;

public class Operation {
	//初始化 隨機生成num vs num 隨機名字不重複的角色
	public static Character[] initial(int num) throws IOException{
		Character[] hero = new Character[num*2];
		String [] prefixDark = IOmethod.fileToArray(HeroBattle.prefixDarkPath);
		String [] prefixLight = IOmethod.fileToArray(HeroBattle.prefixLightPath);
		String [] suffix = IOmethod.fileToArray(HeroBattle.suffixNamePath);

		for(int i = 0, n = num; i<n; i++)
			hero[i] = new CharLight(IOmethod.ranName(prefixLight, suffix));
		for(int i = num, n = num*2; i<n; i++)
			hero[i] = new CharDark(IOmethod.ranName(prefixDark, suffix));
		return hero;
	}
	//Stage Impact
	public static void stageImapct(Stage stage, Character[] hero){
		for(int i=hero.length/2, n=hero.length; i<n; i++){
			CharDark dark = (CharDark)(hero[i]);
			dark.stageImapct(stage);
		}
		switch(stage){
		case Day:
			HeroBattle.formatedStringTmp = String.format("-----曙光乍現!! 黑暗的力量削弱了-----\r\n\r\n");
			IOmethod.printFile(HeroBattle.formatedStringTmp);
			break;
		case Night:
			HeroBattle.formatedStringTmp = String.format("-----黑夜降臨!! 黑暗的力量增強了-----\r\n\r\n");
			IOmethod.printFile(HeroBattle.formatedStringTmp);
			break;
		}
	}
	//隨機施展技能
	public static void randamSkill(Character character, Character[] hero){
		switch(character.getGroup()){
		case "Light":
			CharLight light = (CharLight)character;
			double ran = Math.random();
			if(ran<0.5) light.hit(Operation.chooseChar(hero, "Dark"));
			else if(ran<0.8) light.heal((CharLight)(Operation.chooseChar(hero, "Light")));
			else if(ran<1) light.hollyStrike((CharDark)(Operation.chooseChar(hero, "Dark")));
			break;
		case "Dark":
			CharDark dark = (CharDark)character;
			ran = Math.random();
			if(ran<0.5) dark.hit(Operation.chooseChar(hero, "Light"));
			else if(ran<1) dark.death((CharLight)Operation.chooseChar(hero, "Light"));
			break;
		}
	}
	//依照dex選擇下一個行動角色
	public static Character nextMove(Character[] hero){
		int ptr = -1;
		int ptrDex = 0;
		for(int i=0; i< Character.charCount; i++){
			if(!hero[i].moved && hero[i].getDex() > ptrDex){
				ptrDex = hero[i].getDex();
				ptr = i;
			}
		}
		
		if(ptr != -1){
			hero[ptr].moved=true;
			return hero[ptr];
		}else{
			for(int i=0; i<Character.charCount; i++){
				hero[i].moved = false;
			}
			return Operation.nextMove(hero);
		}
	}
	//自訂陣營 隨機抽取一個角色
	public static Character chooseChar(Character[] hero, String group){
		int index = -1;
		switch(group){
		//0~num-1
		case "Light":
			do{
				index = (int)((hero.length/2)*Math.random());
			}while(!hero[index].getAlive());
			return hero[index];
		//num~2*num-1
		case "Dark":
			do{
				index = (int)((hero.length/2)*Math.random())+(hero.length/2);
			}while(!hero[index].getAlive());
			return hero[index];
		}
		return null;
	}
	//檢查全滅
	public static boolean checkLight(Character[] hero){
		for(int i=0, n=hero.length/2; i<n; i++){
			if(hero[i].getAlive())	return false;
		}
		HeroBattle.formatedStringTmp = String.format("Light陣營全滅...\r\n\r\n");
		IOmethod.printFile(HeroBattle.formatedStringTmp);
		return true;
	}
	public static boolean checkDark(Character[] hero){
		for(int i=hero.length/2, n=hero.length; i<n; i++){
			if(hero[i].getAlive())	return false;
		}
		HeroBattle.formatedStringTmp = String.format("Dark陣營全滅...\r\n\r\n");
		IOmethod.printFile(HeroBattle.formatedStringTmp);
		return true;
	}
	
	public static Stage stageSet(){
		double d2 = Math.random();
		if(d2<0.5) return Stage.Day;
		else return Stage.Night;
	}
	
	public static Stage stageChange(Stage stage){
		if(stage == Stage.Day) return Stage.Night;
		else return Stage.Day;
	}
}
