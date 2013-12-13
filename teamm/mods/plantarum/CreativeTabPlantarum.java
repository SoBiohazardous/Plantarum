package teamm.mods.plantarum;

import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabPlantarum extends CreativeTabs
{
	public CreativeTabPlantarum(String par2Str) 
	{
		super(getNextID(), par2Str);
	}

    public int getTabIconItemIndex()
    {
    	return 0;
    }
    
    public String getTranslatedTabLabel()
    {
    	return "Plantarum";
    }
}
