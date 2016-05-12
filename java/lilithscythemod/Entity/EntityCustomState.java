package lilithscythemod.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

public class EntityCustomState implements IExtendedEntityProperties{

	public static final String NBTNAME = "lilithEntityState";
	private NBTTagList entityCustomStateList = new NBTTagList();
	public static final byte State_GCDTime		= 0x10;
	public static final byte State_GCDFree		= 0x20;
	public static EntityCustomState get(EntityLivingBase entity){

		return (EntityCustomState)entity.getExtendedProperties(NBTNAME);
	 }

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setTag(NBTNAME, entityCustomStateList);
	}
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		this.entityCustomStateList = compound.getTagList(NBTNAME, Constants.NBT.TAG_COMPOUND);
	}

	@Override
	public void init(Entity entity, World world) {
	}
	protected void addstate(String dataName, byte state)
	{
		NBTTagCompound entityInfo = new NBTTagCompound();
		entityInfo.setString("dataName",dataName);
		entityInfo.setByte("state",state);
		entityCustomStateList.appendTag(entityInfo);
	}

	protected NBTTagList getNBTList()
	{
		return entityCustomStateList;
	}

	protected NBTTagCompound getCompoundFromList(int index)
	{
		return entityCustomStateList.getCompoundTagAt(index);
	}

	public static final EntityCustomState getNBT(EntityLivingBase entity)
	{
		return (EntityCustomState) entity.getExtendedProperties(NBTNAME);
	}

	public static void register(EntityLivingBase entity)
	{
		entity.registerExtendedProperties(NBTNAME, new EntityCustomState());
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

