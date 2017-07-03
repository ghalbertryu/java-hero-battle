package dgx.albert.character;

import dgx.albert.herobattle.HeroBattle;
import dgx.albert.io.IOmethod;

public class Character {
	public static int charCount=0;
	//Attributes
	public int index;
	private int hp = 100;
	boolean alive = true;
	public boolean moved =false;
	public String name;
	protected String group;
	protected int atkInitial;	//6~18
	protected int defInitial;	//6~18
	protected int dexInitial;	//6~18
	protected int atkAdjust;	//6~18
	protected int defAdjust;	//6~18
	protected int dexAdjust;	//6~18

	//Setting
	public Character(){
		this.name = "";
		this.atkInitial = (int)(13*Math.random())+6; //6~18
		this.defInitial = (int)(13*Math.random())+6; //6~18
		this.dexInitial = (int)(13*Math.random())+6; //6~18
		this.atkAdjust = (int)(13*Math.random())+6; //6~18
		this.defAdjust = (int)(13*Math.random())+6; //6~18
		this.dexAdjust = (int)(13*Math.random())+6; //6~18
		index = charCount;
		charCount++;
	}
	public Character(String name, int atk, int def, int dex){
		this.name = name;
		this.atkInitial = atk; //6~18
		this.defInitial = def; //6~18
		this.dexInitial = dex; //6~18
		this.atkAdjust = atk;
		this.defAdjust = def; 
		this.dexAdjust = dex; 
		index = charCount;
		charCount++;
	}
	
	//info
	public void showChar(){
		HeroBattle.formatedStringTmp = String.format("Group:%s %s:HP%d\r\n", group, name, hp);
		IOmethod.printFile(HeroBattle.formatedStringTmp);
		
		HeroBattle.formatedStringTmp = String.format("ATK=%d DEF=%d DEX=%d\r\n\r\n", atkInitial, defInitial, dexInitial);
		IOmethod.printFile(HeroBattle.formatedStringTmp);
	}
	
	public void hpLeft(){
		HeroBattle.formatedStringTmp = String.format("%s HP還剩%d\r\n\r\n", name, hp);
		IOmethod.printFile(HeroBattle.formatedStringTmp);
	}
	
	public boolean getAlive(){
		if(alive){
			return true;
		}else{
			//System.out.print(name + "已陣亡 無法指定\n\n");
			return false;
		}
	}
	
	public String getGroup(){
		return group;
	}
	
	public int getHp(){
		return hp;
	}
	
	public int getAtk(){
		return atkAdjust;
	}
	
	public int getDef(){
		return defAdjust;
	}
	
	public int getDex(){
		return dexAdjust;
	}
	
	//Basic Skills
	public void hit(Character enemy){
		if(!enemy.getAlive()) return;
		
		HeroBattle.formatedStringTmp = String.format("%s對%s使出普通攻擊", name, enemy.name);
		IOmethod.printFile(HeroBattle.formatedStringTmp);
		
		int damage = atkAdjust -(int)((enemy.defAdjust) * (0.5* Math.random() + 0.5)); //atk - def*(0.5~1)
		if(damage > 0){
			HeroBattle.formatedStringTmp = String.format("造成%d點傷害\r\n", damage);
			IOmethod.printFile(HeroBattle.formatedStringTmp);
			enemy.getHurt(damage);
		}else{
			HeroBattle.formatedStringTmp = String.format("卻被防禦了!\r\n\r\n");
			IOmethod.printFile(HeroBattle.formatedStringTmp);
		}
	}
	
	public void getHurt(int damage){
		hp = hp - damage;
		if(hp<0){
			hp = 0;
			getDie();
		}else{
			hpLeft();
		}
	}
	
	private void getDie(){
		HeroBattle.formatedStringTmp = String.format("%s陣亡了!\r\n\r\n", name);
		IOmethod.printFile(HeroBattle.formatedStringTmp);
		alive = false;
	}
	
	protected void getHeal(int heal){
		hp = ((hp + heal)>100)? 100: hp+heal;
	}
	
	public int getDeath(){
		if(hp >10){
			hp /= 10;
			return hp;
		}else{
			hp = 1;
			return hp;
		}
	}
}
