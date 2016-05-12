package lilithscythemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabLilithscythe extends CreativeTabs
{
    public TabLilithscythe(String type)
    {
        super(type);
    }

    public Item getTabIconItem()
    {
        return ItemRegistry.Lilithscythe;
    }

    public String func_78024_c()
    {
        return "LilithscytheMod";
    }
}
