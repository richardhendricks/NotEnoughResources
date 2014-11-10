package neiresources.registry;

import neiresources.drop.DropItem;
import neiresources.utils.LightLevel;
import net.minecraft.entity.EntityHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class MobRegistryEntry
{
    private EntityLivingBase entity;
    private List<DropItem> drops = new ArrayList<DropItem>();
    private LightLevel lightLevel;
    private List<String> biomes = new ArrayList<String>();

    public MobRegistryEntry(EntityLivingBase entity, LightLevel level, String[] biomes, DropItem... drops)
    {
        this.entity = entity;
        this.lightLevel = lightLevel;
        for (String biome : biomes)
            this.biomes.add(biome);
        for (DropItem drop : drops)
            this.drops.add(drop);
    }

    public MobRegistryEntry(EntityLivingBase entity, LightLevel level, DropItem... drops)
    {
        this.entity = entity;
        this.lightLevel = lightLevel;
        this.biomes.add("all");
        for (DropItem drop : drops)
            this.drops.add(drop);
    }

    public EntityLivingBase getEntity()
    {
        return entity;
    }

    public String getName()
    {
        return EntityHelper.getEntityName(this.entity);
    }

    public List<DropItem> getDrops()
    {
        return drops;
    }

    public List<String> getBiomes()
    {
        return biomes;
    }

    public String getLightLevel()
    {
        return lightLevel.getString();
    }

    public int getExperience()
    {
        if (entity instanceof EntityLiving)
            return EntityHelper.getExperience((EntityLiving) entity);
        return 0;
    }

    public boolean dropsItem(Item item)
    {
        for(DropItem dropItem : drops)
            if (dropItem.item == item) return true;
        return false;
    }
}