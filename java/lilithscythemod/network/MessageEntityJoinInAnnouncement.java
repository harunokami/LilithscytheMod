package lilithscythemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageEntityJoinInAnnouncement implements IMessage{
	 private int entityId;
    
    public MessageEntityJoinInAnnouncement(){}
 
    public MessageEntityJoinInAnnouncement(EntityLivingBase target) {
    	this.entityId = target.getEntityId();
    }
 
    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
    }
 
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entityId);
    }
 
    public EntityLivingBase getEntityFromId(World world) {
        return (EntityLivingBase) world.getEntityByID(this.entityId);
    }

}
