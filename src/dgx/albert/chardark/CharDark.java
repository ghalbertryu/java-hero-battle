package dgx.albert.chardark;
import dgx.albert.character.Character;
import dgx.albert.charlight.CharLight;
import dgx.albert.herobattle.HeroBattle;
import dgx.albert.io.IOmethod;
import dgx.albert.stage.Stage;

public class CharDark extends Character{
	//
	public static int count = 0;
	//setting
	public CharDark(){
		super();
		name = "Dark"+count;
		group = "Dark";
		count++;
	}
	public CharDark(String name){
		super();
		this.name = name;
		group = "Dark";
		count++;
	}
	public CharDark(String name, int atk, int def, int dex){
		super(name, atk, def, dex);
		group = "Dark";
		count++;
	}
	
	//DarkSkill
	public void death(CharLight enemy){
		if(!enemy.getAlive()) return;
		
		HeroBattle.formatedStringTmp = String.format("%s對%s施展了致死術", name, enemy.name);
		IOmethod.printFile(HeroBattle.formatedStringTmp);

		double d10 = Math.random();
		if(d10<0.25){
			HeroBattle.formatedStringTmp = String.format("成功!%s血量剩下%s\r\n\r\n", enemy.name, enemy.getDeath());
			IOmethod.printFile(HeroBattle.formatedStringTmp);
		}else{
			HeroBattle.formatedStringTmp = String.format("不幸失敗了!\r\n\r\n");
			IOmethod.printFile(HeroBattle.formatedStringTmp);
		}
	}
	
	//stage impact
	public void stageImapct(Stage stage){
		switch(stage){
		case Day:
			atkAdjust=(atkInitial == (int)(atkInitial * 0.9))? atkInitial-1:(int)(atkInitial * 0.9);
			defAdjust=(defInitial == (int)(defInitial * 0.9))? defInitial-1:(int)(defInitial * 0.9);
			dexAdjust=(dexInitial == (int)(dexInitial * 0.9))? dexInitial-1:(int)(dexInitial * 0.9);
			break;
		case Night:	
			atkAdjust=(atkInitial == (int)(atkInitial * 1.1))? atkInitial+1:(int)(atkInitial * 1.1);
			defAdjust=(defInitial == (int)(defInitial * 1.1))? defInitial+1:(int)(defInitial * 1.1);
			dexAdjust=(dexInitial == (int)(dexInitial * 1.1))? dexInitial+1:(int)(dexInitial * 1.1);
			break;
		}
	}
}
