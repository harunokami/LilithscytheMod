package lilithscythemod;


import java.util.UUID;

import lilithscythemod.Enchantment.EnchantmentHpBoost;
import lilithscythemod.Enchantment.EnchantmentKnockbackProtection;
import lilithscythemod.Entity.EntityAcidBreak;
import lilithscythemod.Entity.EntityBloodCross;
import lilithscythemod.Entity.EntityDreamyNight;
import lilithscythemod.Entity.EntityLoveHeart;
import lilithscythemod.Entity.EntityMob.EntitySmallHorun;
import lilithscythemod.Entity.EntityRenderer.EntityHorunRenderer;
import lilithscythemod.Event.EntityCustomDataEventHook;
import lilithscythemod.Event.HpBoostEventHook;
import lilithscythemod.Event.KnockbackProtectionEventHook;
import lilithscythemod.Event.PotionEffectEventHook;
import lilithscythemod.Potion.PotionEventHandler;
import lilithscythemod.Skill.SkillEventHook;
import lilithscythemod.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
@Mod(modid="LilithscytheMod", name="LilithscytheMod", version="1.0")
public class ModCore {

	   public ModCore()
	    {

	    }
	   //MODのIDなどを定義
		public static final String MODID = "Lilithscythemod";
		public static final String MODTEXTUREDOMAIN = "lilithscythemod";
		public static final String MODNAME = "Lilithscythemod";
		public static final String VERSION = "1.0.0";
		//当Mod用UUID
		public static final UUID HealthBoostUUID = UUID.fromString("ABC-BBA-ABB-AB45-AB777");
		public static final UUID knockbackResistanceBoostUUID = UUID.fromString("ABC-BBA-ABB-AB45-AB778");
		//クリエイティブタブ追加
		public static final CreativeTabs LilithscytheTab= new TabLilithscythe(MODID);
		

		@SidedProxy(clientSide = "lilithscythemod.ClientProxy", serverSide = "lilithscythemod.CommonProxy")
		 public static CommonProxy proxy;

		@Mod.Instance("ModCore")
		public static ModCore instance;

		private Gui gui = new Gui();
		private Minecraft mc = FMLClientHandler.instance().getClient();

		@Mod.EventHandler
	    public void mainLoading(FMLPreInitializationEvent event)
	    {

	    }
		@Mod.EventHandler
		  public void init(FMLInitializationEvent event)
		    {
				//ExtendsReachItemのイベント登録
	        	FMLCommonHandler.instance().bus().register(proxy);
			    //エンティティモブの登録
	        	EntityRegistry.registerModEntity(EntitySmallHorun.class, "SmallHorun", 23, this, 250, 1, false);
	        	EntityRegistry.addSpawn(EntitySmallHorun.class, 20, 1, 50,EnumCreatureType.monster, BiomeGenBase.forest);
	        	if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
	        		RenderingRegistry.registerEntityRenderingHandler(EntitySmallHorun.class, new EntityHorunRenderer());
		        }
				//エンティティの登録。
				EntityRegistry.registerModEntity(EntityLoveHeart.class, "LoveHeart",170, this, 128, 5, true);
				EntityRegistry.registerModEntity(EntityDreamyNight.class,"DreamyNight",171,this,128,5,true);
				EntityRegistry.registerModEntity(EntityBloodCross.class,"BloodCross",172,this,128,5,true);
				EntityRegistry.registerModEntity(EntityAcidBreak.class,"AcidBreak",173,this,128,5,true);

				//プロキシを通して、クライアントサイドのみでエンティティのモデル・レンダーの登録を行う。
			    ClientProxy client =  new ClientProxy();
			    client.registerRenderers();
		    }

@Mod.EventHandler
public void preInit(FMLPreInitializationEvent event)
{
	//Messageの登録呼び出し
    PacketHandler.init();

	//コンフィグ
	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	config.load();
	//HPBoost
	addHpBoost = config.get(Configuration.CATEGORY_GENERAL, "addHpBoost", true, "add HpBoost Enchantment").getBoolean(true);
	idHpBoost = config.get(Configuration.CATEGORY_GENERAL, "idHpBoost", 47, "HpBoost Enchantment Id").getInt();
	//KnockbackProtection
	addKnockbackProtection= config.get(Configuration.CATEGORY_GENERAL, "KnockbackProtection", true, "add KnockbackProtection Enchantment").getBoolean(true);
	idKnockbackProtection=config.get(Configuration.CATEGORY_GENERAL, "idKnockbackProtection", 46, "KnockbackProtection Enchantment Id").getInt();
	config.save();


	//ポーション関連
    proxy.registerLilithPotions();
    FMLCommonHandler.instance().bus().register(new PotionEventHandler());
    FMLCommonHandler.instance().bus().register(new PotionEffectEventHook());
    //EntityCustomData
    MinecraftForge.EVENT_BUS.register(new EntityCustomDataEventHook());
    MinecraftForge.EVENT_BUS.register(new SkillEventHook());
    //スキル登録
    proxy.registerSkills();
	//武器・防具の追加
     ItemRegistry.registerItem();
     //レシピ登録
     ItemRegistry.registerItemRecipe();


	}
@Mod.EventHandler
public void load(FMLInitializationEvent event)
{
 //AddEnchantment
 if(addHpBoost){
	 HpBoost = new EnchantmentHpBoost(idHpBoost, 0).setName("HpBoost");
	 MinecraftForge.EVENT_BUS.register(new HpBoostEventHook());
 }
 if(addKnockbackProtection){
	 KnockbackProtection = new EnchantmentKnockbackProtection(idKnockbackProtection, 1).setName("KnockbackProtection");
	 MinecraftForge.EVENT_BUS.register(new KnockbackProtectionEventHook());
 }
}

//材質
public static final net.minecraft.item.Item.ToolMaterial LilithMaterial;
public static final ArmorMaterial LilithArmorMaterial3;
public static final net.minecraft.item.Item.ToolMaterial ViviMaterial;
public static final net.minecraft.item.Item.ToolMaterial DarkNightMaterial;
public static final net.minecraft.item.Item.ToolMaterial ArtemisMaterial;
public static final ArmorMaterial ArtemisArmorMaterial;
public static final net.minecraft.item.Item.ToolMaterial AliceMaterial;

static
   {
 //("マテリアルネーム", 採掘LV, 耐久, 採掘速度, 攻撃力, エンチャント補正Lv);
	//Lilith
	LilithMaterial = EnumHelper.addToolMaterial("Lilith_Size_material", 8, 20000, 50.0F,26.0F, 500);
	LilithArmorMaterial3 = EnumHelper.addArmorMaterial("LilithArmor3",4000,new int[]{30, 60, 60, 30}, 500);
	
	//Vivi
	 ViviMaterial = EnumHelper.addToolMaterial("Vivi_Rod_material", 4, 15000, 10.0F, 7, 70);
	 
	//darkNight
	 DarkNightMaterial = EnumHelper.addToolMaterial("DarkNightMaterial", 10, 5000, 100.0F, 150.0F, 500);
	 
	 //Artemis
	 ArtemisMaterial = EnumHelper.addToolMaterial("ArtemisBookMaterial", 4, 10000, 10, 12, 50);	 
	 ArtemisArmorMaterial = EnumHelper.addArmorMaterial("LilithArmor3",4000,new int[]{50, 80, 80, 50}, 500);
	 
	 //Alice
	 AliceMaterial = EnumHelper.addToolMaterial("AliceMaterial", 5, 15000, 10, 18F, 500);

   }


	 //エンチャント
	 public static Enchantment HpBoost;
	 public static Enchantment KnockbackProtection;
	 public static boolean addHpBoost;
	 public static boolean addKnockbackProtection;
	 public static int idHpBoost;
	 public static int idKnockbackProtection;
	 //デバック用
	 public static byte TestState = 0x01;
	 public static int Actives=0;
}
