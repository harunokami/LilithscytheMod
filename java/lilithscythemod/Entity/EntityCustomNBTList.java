package lilithscythemod.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

public class EntityCustomNBTList implements IExtendedEntityProperties{
	
	public static final String NBTNAME = "lilithEntityData";
	private NBTTagList entityCustomDataList = new NBTTagList();
	public static EntityCustomNBTList get(EntityLivingBase entity){
		
		return (EntityCustomNBTList)entity.getExtendedProperties(NBTNAME);
	 }

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setTag(NBTNAME, entityCustomDataList);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		this.entityCustomDataList = compound.getTagList(NBTNAME, Constants.NBT.TAG_COMPOUND);
	}

	@Override
	public void init(Entity entity, World world) {
	}
	protected void addData(String dataName, double dataValue)
	{
		NBTTagCompound entityInfo = new NBTTagCompound();
		entityInfo.setString("dataName",dataName);
		entityInfo.setDouble("dataValue",dataValue);
		entityCustomDataList.appendTag(entityInfo);
	}
	
	protected void addstate(String dataName, byte state)
	{
		NBTTagCompound entityInfo = new NBTTagCompound();
		entityInfo.setString("dataName",dataName);
		entityInfo.setByte("state",state);
		entityCustomDataList.appendTag(entityInfo);
	}
	
	protected NBTTagList getNBTList()
	{
		return entityCustomDataList;
	}

	protected NBTTagCompound getCompoundFromList(int index)
	{
		return entityCustomDataList.getCompoundTagAt(index);
	}
	
	public static final EntityCustomNBTList getNBT(EntityLivingBase entity)
	{
		return (EntityCustomNBTList) entity.getExtendedProperties(NBTNAME);
	}

	public static void register(EntityLivingBase entity)
	{
		entity.registerExtendedProperties(NBTNAME, new EntityCustomNBTList());
	}
	
	protected double getNBTDouble(int index, String key)
	{
		return getNBTCompound(index).getDouble(key);
	}
	
	protected void setNBTDouble(int index, String key, double newValue)
	{
		getNBTCompound(index).setDouble(key, newValue);
	}
	
	protected void setNBTFloat(int index, String key, float newValue)
	{
		getNBTCompound(index).setFloat(key, newValue);
	}
	
	protected float getNBTFloat(int index, String key)
	{
		return getNBTCompound(index).getFloat(key);
	}

	protected int getNBTInt(int index, String key)
	{
		return getNBTCompound(index).getInteger(key);
	}
	
	protected void setNBTByte(int index, String key, byte newValue)
	{
		getNBTCompound(index).setByte(key, newValue);
	}
	protected byte getNBTByte(int index, String key){
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
