package lilithscythemod.Entity;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;

public class EntityCustomStateManager {

	public static void EntityCustomState(EntityLivingBase target , String dataName, byte par1){
		EntityCustomNBTList list = EntityCustomNBTList.getNBT(target);
		checkEffectOverwrite(list.getNBTList(), dataName);
		list.addstate(dataName,par1);
	}
	public static void removeDatas(EntityLivingBase target, String dataName, boolean blackList)
	{
		EntityCustomNBTList activeData= EntityCustomNBTList.getNBT(target);
		NBTTagList dataList = activeData.getNBTList();

		for (int x = 0; x < dataList.tagCount(); x++)
		{
			if (blackList == true)
			{
				if (!activeData.getNBTString(x,"dataName").equals(dataName))
					dataList.removeTag(x);
			} else
			{
				if (activeData.getNBTString(x,"dataName").equals(dataName))
					dataList.removeTag(x);
			}
		}
	}
	public static void removeAllDatas(EntityLivingBase target)
	{
		EntityCustomNBTList activeData = EntityCustomNBTList.getNBT(target);
		for (int x = 0; x < activeData.getNBTList().tagCount(); x++){
			activeData.getNBTList().removeTag(x);
		}

	}

	public static byte getstate(EntityLivingBase target,String dataName){
		byte state=0;
		EntityCustomNBTList activeData = EntityCustomNBTList.getNBT(target);
	    NBTTagList dataList = activeData.getNBTList();
	    for (int x = 0; x < dataList.tagCount(); x++)
		{
	    	if(activeData.getNBTString(x,"dataName").equals(dataName)){
	    		state = activeData.getNBTByte(x,"state");
	    		return state;
	    	}
		}
	    return 0;
	}
	public static void updateState(EntityLivingBase target, String dataName,byte newstate){
		EntityCustomNBTList activeData = EntityCustomNBTList.getNBT(target);
		NBTTagList dataList = activeData.getNBTList();
		 for (int x = 0; x < dataList.tagCount(); x++)
			{
		    	if(activeData.getNBTString(x,"dataName").equals(dataName)){
		    		activeData.setNBTByte(x,"state",newstate);
		    	}
			}
	}
	
	public static void checkEffectOverwrite(NBTTagList list, String dataName)
	{
		for (int x = 0; x < list.tagCount(); x++){
			if (list.getCompoundTagAt(x).getString("dataName").equals(dataName)){
				list.removeTag(x);
			}
		}
	}
}
