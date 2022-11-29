package dgx.albert.herobattle.vo;
import dgx.albert.herobattle.HeroBattle;
import dgx.albert.herobattle.utils.FileUtils;
import dgx.albert.herobattle.constant.StageEnum;

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
		FileUtils.printFile(HeroBattle.formatedStringTmp);

		double d10 = Math.random();
		if(d10<0.25){
			HeroBattle.formatedStringTmp = String.format("成功!%s血量剩下%s\r\n\r\n", enemy.name, enemy.getDeath());
			FileUtils.printFile(HeroBattle.formatedStringTmp);
		}else{
			HeroBattle.formatedStringTmp = String.format("不幸失敗了!\r\n\r\n");
			FileUtils.printFile(HeroBattle.formatedStringTmp);
		}
	}
	
	//stage impact
	public void stageImapct(StageEnum stage){
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
