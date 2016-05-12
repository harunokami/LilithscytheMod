package lilithscythemod.Skill;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

public class SkillNBTData implements IExtendedEntityProperties{
	public static final String NBTNAME = "lilithSkillData";
	public static final String Tag_State	= "State";
	public static final String Tag_SkillName ="SkillName";
	public static final String Tag_CoolTime	= "CoolTime";
	public static final String Tag_GlobalCoolDown ="GlobalCoolDown";
	public static final String Tag_ChargeTime ="ChargeTime";
	
	private byte stateByte = 0x01;
	private NBTTagList skillList = new NBTTagList();
	
	/**スキルの詠唱状態
	 * Ready:スキル発動可能状態
	 * 
	 * Chant:スキル詠唱中
	 * ChantEnd:スキル詠唱完了
	 * 
	 * GCD:グローバルクールダウン中
	 * CoolDown:クールダウン中*/
	public static final byte State_Ready		= 0x01;
	public static final byte State_Chant		= 0x02;
	public static final byte State_ChantEnd		= 0x03;
	public static final byte State_CoolDown		= 0x04;
	
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		 compound.setTag(NBTNAME, skillList);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		this.skillList = compound.getTagList(NBTNAME, Constants.NBT.TAG_COMPOUND);
	}
	protected void addSkillData(String skillName,int coolTime,float ChargeTime,byte stateByte){
		 NBTTagCompound nbt = new NBTTagCompound();
		 nbt.setByte(Tag_State, stateByte);
		 nbt.setString(Tag_SkillName, skillName);
		 nbt.setInteger(Tag_CoolTime, coolTime);
		 nbt.setFloat(Tag_ChargeTime, ChargeTime);
		 skillList.appendTag(nbt);
	}
	
	@Override
	public void init(Entity entity, World world) {}
	
    public static void register(EntityLivingBase entity) {
    	entity.registerExtendedProperties(NBTNAME, new SkillNBTData());
    }
    
    public static SkillNBTData get(EntityLivingBase entity){
        return (SkillNBTData)entity.getExtendedProperties(NBTNAME);
    }
    
	public NBTTagList getNBTList()
	{
		return skillList;
	}

	protected NBTTagCompound getCompoundFromList(int index)
	{
		return skillList.getCompoundTagAt(index);
	}

	protected static final SkillNBTData getNBT(EntityLivingBase entity)
	{
		return (SkillNBTData) entity.getExtendedProperties(NBTNAME);
	}

	protected void setNBTFloat(int index, String key, float newValue)
	{
		getNBTCompound(index).setFloat(key, newValue);
	}

	protected float getNBTFloat(int index, String key)
	{
		return getNBTCompound(index).getFloat(key);
	}
	
	protected void setNBTInt(int index,String key,int newValue)
	{
		getNBTCompound(index).setInteger(key, newValue);
	}

	protected int getNBTInt(int index, String key)
	{
		return getNBTCompound(index).getInteger(key);
	}
	
	protected void setNBTByte(int index,String key,byte newValue){
		getNBTCompound(index).setByte(key, newValue);
	}
	protected byte getNBTByte(int index,String key){
		return getNBTCompound(index).getByte(key);
	}
	protected String getNBTString(int index, String key)
	{
		return getNBTCompound(index).getString(key);
	}

	protected NBTTagCompound getNBTCompound(int index)
	{
		return getCompoundFromList(index);
	}


}
