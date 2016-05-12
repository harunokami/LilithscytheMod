package lilithscythemod;

import java.awt.Color;

import lilithscythemod.Armors.ArtemisEar;
import lilithscythemod.Armors.LilithArmorWing;
import lilithscythemod.Items.SmollHorunEgg;
import lilithscythemod.Weapons.ItemArtemisBook;
import lilithscythemod.Weapons.ItemDarkNightSword;
import lilithscythemod.Weapons.ItemLilithScythe;
import lilithscythemod.Weapons.ItemViviRod;
import lilithscythemod.Weapons.LilithscytheWeapons;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {
	//Item登録
	public static void registerItem(){
		//リリス
		 Lilithscythe = new ItemLilithScythe(ModCore.LilithMaterial);
		 GameRegistry.registerItem(Lilithscythe, "Lilithscythe");
		 Lilithplate3  = new LilithArmorWing(ModCore.LilithArmorMaterial3, 0, 1);
		 Lilithplate3.setUnlocalizedName("LilithScythemod:Lilithplate3").setCreativeTab(ModCore.LilithscytheTab);
		 GameRegistry.registerItem( Lilithplate3, "LilithArmorWing");
		 //ヴィヴィ
		 ViviRod = new ItemViviRod(ModCore.ViviMaterial);
		 GameRegistry.registerItem(ViviRod, "ViviRod");
		 //アルテミス
		 ArtemisBook = new ItemArtemisBook(ModCore.ArtemisMaterial);
		 GameRegistry.registerItem(ArtemisBook, "ArtemisBook");
		 ArtemisEar = new ArtemisEar(ModCore.ArtemisArmorMaterial,0,0);
		 ArtemisEar.setUnlocalizedName("LilithScythemod:ArtemisEar").setCreativeTab(ModCore.LilithscytheTab);
		 GameRegistry.registerItem(ArtemisEar, "ArtemisEar");
		 //ダークナイト
		 DarkNightSword = new ItemDarkNightSword(ModCore.DarkNightMaterial);
		 GameRegistry.registerItem(DarkNightSword,"DarkNightSword");
		 //コア、武具の素材追加
		 LilithscytheCore = new Item();
		 LilithscytheCore.setUnlocalizedName("LilithscytheCore");
		 LilithscytheCore.setTextureName("lilithscythemod:LilithscytheCore");
	     LilithscytheCore.setCreativeTab(ModCore.LilithscytheTab);
	     GameRegistry.registerItem(LilithscytheCore, "LilithscytheCore");
	     ViviRodCore = new Item();
	     ViviRodCore.setUnlocalizedName("VivirodCore");
	     ViviRodCore.setTextureName("lilithscythemod:ViviRodCore");
	     ViviRodCore.setCreativeTab(ModCore.LilithscytheTab);
		 GameRegistry.registerItem(ViviRodCore,"VivirodCore");

		 //その他特殊アイテム
		 SmollHorunEgg = new SmollHorunEgg(Color.GREEN.getRGB() , Color.MAGENTA.getRGB());
		 // スポーンエッグを追加
	     GameRegistry.registerItem(SmollHorunEgg, "SmollHorunEgg");
	}
	//レシピ登録
	public static void registerItemRecipe(){
		 //リリスサイズレシピ作成
		 GameRegistry.addRecipe(new ItemStack(Lilithscythe),"DDC","  G","  G",'G',Items.gold_ingot,'C',LilithscytheCore,'D',Items.diamond_sword);
		 //ヴィヴィロッドレシピ
		 GameRegistry.addRecipe(new ItemStack(ViviRod),"DDD","DVD","DBD",'B',Items.bed,'V',ViviRodCore,'D',Blocks.diamond_block);
	     //コアレシピ作成
		 GameRegistry.addRecipe(new ItemStack(ViviRodCore,1),"IEI","EVE","IEI",'V',Items.nether_star,'I',Blocks.ice,'E',Items.ender_eye);
		 GameRegistry.addRecipe(new ItemStack(LilithscytheCore,1),"SBS","BNB","SBS",'N',Items.nether_star,'B',Items.ender_eye,'S',Blocks.diamond_block);


	}
	 //武器
	 public static LilithscytheWeapons Lilithscythe;
	 public static LilithscytheWeapons ViviRod;
	 public static LilithscytheWeapons DarkNightSword;
	 public static LilithscytheWeapons ArtemisBook;
	 //防具
	 public static Item Lilithplate3;
	 public static Item ArtemisEar;
	 //素材・または中間素材
	 public static Item LilithscytheCore;
	 public static Item ViviRodCore;
	 //特殊アイテム
	 public static Item SmollHorunEgg;




}
