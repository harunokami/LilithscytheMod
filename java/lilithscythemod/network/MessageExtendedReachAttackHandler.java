package lilithscythemod.network;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageExtendedReachAttackHandler implements IMessageHandler<MessageExtendedReachAttack, IMessage> {
	
	   @Override
	    public IMessage onMessage(MessageExtendedReachAttack message, MessageContext ctx) {
	        EntityPlayer player = ctx.getServerHandler().playerEntity;
	        Entity targetEntity = message.getEntityFromId(player.worldObj);
	        player.attackTargetEntityWithCurrentItem(targetEntity);
	        return null;
	    }
}
