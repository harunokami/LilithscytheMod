package lilithscythemod.network;

import io.netty.buffer.ByteBuf;
import lilithscythemod.Skill.SkillNBTData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageEntityProperties implements IMessage{
	
	  public NBTTagCompound data;
	  public int entityId;
	  
	    public MessageEntityProperties(){}
	 
	    public MessageEntityProperties(EntityLivingBase entity) {
	        this.data = new NBTTagCompound();
	    	this.entityId = entity.getEntityId();
	        SkillNBTData.get(entity).saveNBTData(data);
	    }
	 
	    @Override
	    public void fromBytes(ByteBuf buf) {
	    	 data = ByteBufUtils.readTag(buf);
	        this.entityId = buf.readInt();
	    }

	    @Override
	    public void toBytes(ByteBuf buf) {
	    	 ByteBufUtils.writeTag(buf, data);
	        buf.writeInt(this.entityId);
	    }

		public EntityLivingBase getEntityFromId(World worldObj) {

			return (EntityLivingBase) worldObj.getEntityByID(this.entityId);
		}
}
