package teamm.mods.plantarum;

import teamm.mods.plantarum.lib.PItems;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabPlantarum extends CreativeTabs
{
	public CreativeTabPlantarum(String par2Str) 
	{
		super(getNextID(), par2Str);
	}

    public int getTabIconItemIndex()
    {
    	return PItems.seedCorn.itemID;
    }
    
    public String getTranslatedTabLabel()
    {
    	return "Plantarum";
    }
}
