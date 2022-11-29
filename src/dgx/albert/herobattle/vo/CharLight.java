package dgx.albert.herobattle.vo;
import dgx.albert.herobattle.HeroBattle;
import dgx.albert.herobattle.utils.FileUtils;

public class CharLight extends Character {
	//
	public static int count = 0;
	//Setting
	public CharLight(){
		super();
		name = "Light"+count;
		group = "Light";
		count++;
	}
	public CharLight(String name){
		super();
		this.name = name;
		group = "Light";
		count++;
	}
	public CharLight(String name, int atk, int def, int dex){
		super(name, atk, def, dex);
		group = "Light";
		count++;
	}
	
	//LightSkill
	public void heal(CharLight partner){
		if(!partner.getAlive()) return;
		
		HeroBattle.formatedStringTmp = String.format("%s對%s施展治療術", name, partner.name);
		FileUtils.printFile(HeroBattle.formatedStringTmp);
		
		int healHp = (int)(21*Math.random())+10; //10~30
		partner.getHeal(healHp);
		HeroBattle.formatedStringTmp = String.format("%s回復了%d點血量\r\n", partner.name, healHp);
		FileUtils.printFile(HeroBattle.formatedStringTmp);
		partner.hpLeft();
	}
	
	public void hollyStrike(CharDark enemy){
		if(!enemy.getAlive()) return;
		
		HeroBattle.formatedStringTmp = String.format("%s對%s施展聖光衝擊!!!", name, enemy.name);
		FileUtils.printFile(HeroBattle.formatedStringTmp);

		int damage = (int)(atkAdjust*(2*Math.random()+1)-(enemy.getDef())*(0.5* Math.random() + 0.5)); //atk*(1~3) - def*(0.5~1)
		if(damage > 0){
			HeroBattle.formatedStringTmp = String.format("造成%d點傷害\r\n", damage);
			FileUtils.printFile(HeroBattle.formatedStringTmp);
			enemy.getHurt(damage);	
		}else{
			HeroBattle.formatedStringTmp = String.format("卻被防禦了!\r\n\r\n");
			FileUtils.printFile(HeroBattle.formatedStringTmp);
		}
	}
}
