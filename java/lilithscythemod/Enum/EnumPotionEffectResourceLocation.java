package lilithscythemod.Enum;

import lilithscythemod.ModCore;
import net.minecraft.util.ResourceLocation;


public enum EnumPotionEffectResourceLocation {
	Nothing(		 "nothing"		   ,new ResourceLocation(ModCore.MODTEXTUREDOMAIN,"textures/potionEffect/icon/PotionEffectBase.png")),
	BreakProtect(    "BreakProtect"    ,new ResourceLocation(ModCore.MODTEXTUREDOMAIN,"textures/potionEffect/icon/BreakProtect.png")),
	Erode(           "Erode"           ,new ResourceLocation(ModCore.MODTEXTUREDOMAIN,"textures/potionEffect/icon/Erode.png")),
	Percentagedamage("Percentagedamage",new ResourceLocation(ModCore.MODTEXTUREDOMAIN,"textures/potionEffect/icon/PercentageDamage.png"));
	
	public final String name;
	public final ResourceLocation Location;
	
	private EnumPotionEffectResourceLocation(String name, ResourceLocation Location)
	{
		this.name = name;
		this.Location = Location;

	}
	/**
	 * 追加ポーションのアイコンを返します
	 * @param par1:調査対象ポーションの名前
	 * @return 一致したポーションエフェクトのアイコンロケーション
	 */
    public static ResourceLocation getPotioneffectLocation(String modId,String par1)
    {
    	EnumPotionEffectResourceLocation[] enumArray = values();
    	String s ="";
        for(EnumPotionEffectResourceLocation enumA : enumArray)
        {
        	s= modId +":"+enumA.name;
            if(s.toString().equals(par1))
            {
                return enumA.Location;
            }
        }
        return Nothing.Location;
    }
}
