package lilithscythemod.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;

public class EntityDataManager {
	;
	public static void EntityCustomData(EntityLivingBase target , String dataName, double par1)
	{
			EntityCustomNBTList list = EntityCustomNBTList.getNBT(target);
			checkEffectOverwrite(list.getNBTList(), dataName);
			list.addData(dataName,par1);

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
	public static double getData(EntityLivingBase target,String dataName){
		double data=0;
		EntityCustomNBTList activeData = EntityCustomNBTList.getNBT(target);
	    NBTTagList dataList = activeData.getNBTList();
	    for (int x = 0; x < dataList.tagCount(); x++)
		{
	    	if(activeData.getNBTString(x,"dataName").equals(dataName)){
	    		data = activeData.getNBTDouble(x,"dataValue");
	    		return data;
	    	}
		}

	    return data;
	}
	public static Boolean activeData(EntityLivingBase target, String dataName)
	{
		EntityCustomNBTList activeData = EntityCustomNBTList.getNBT(target);
		NBTTagList dataList = activeData.getNBTList();
		Boolean activeBool = false;

		for (int x = 0; x < dataList.tagCount(); x++)
		{
			if(activeData.getNBTString(x,"dataName").equals(dataName))
			{
				return true;
			}
		}

		return false;
	}

	public static void updateGCD(EntityLivingBase target, String dataName,double newValue){
		EntityCustomNBTList activeData = EntityCustomNBTList.getNBT(target);
		NBTTagList dataList = activeData.getNBTList();

		 for (int x = 0; x < dataList.tagCount(); x++)
			{
		    	if(activeData.getNBTString(x,"dataName").equals(dataName)){
		    		activeData.setNBTDouble(x,"dataValue",newValue);
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
