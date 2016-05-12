package lilithscythemod.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
public class PacketHandler {
	 /*MOD固有のSimpleNetworkWrapperを取得。
	    * 文字列は他のMODと被らないようにMOD_IDを指定しておくと良い*/
	    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("LilithscytheMod");

	    public static void init() {
	        /*Messageクラスの登録。*/
	     
	    	/**ReachAttack*/
	        INSTANCE.registerMessage(MessageExtendedReachAttackHandler.class, MessageExtendedReachAttack.class, 0, Side.SERVER);
	        
	        /**SkillNBTData*/
	        INSTANCE.registerMessage(MessageEntityPropertiesHandler.class, MessageEntityProperties.class, 1, Side.CLIENT);
	        INSTANCE.registerMessage(MessageEntityJoinInAnoucementHandler.class, MessageEntityJoinInAnnouncement.class, 2, Side.SERVER);
	        
	    }
}
