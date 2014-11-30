package neresources.api.messages;

import neresources.api.messages.utils.MessageHelper;
import neresources.api.messages.utils.MessageKeys;
import neresources.api.utils.DropItem;
import neresources.api.utils.Priority;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ModifyPlantMessage extends ModifyMessage
{
    private ItemStack plant;
    private DropItem[] addDrops = new DropItem[0];
    private ItemStack[] removeDrops = new ItemStack[0];

    private ModifyPlantMessage(ItemStack plant, Priority priority, boolean add)
    {
        super(priority,add);
        this.plant = plant;
    }

    private ModifyPlantMessage(ItemStack plant, Priority addPriority, Priority removePriority)
    {
        super(addPriority,removePriority);
        this.plant = plant;
    }

    public ModifyPlantMessage(DropItem... addDrops)
    {
        this(new ItemStack(Blocks.tallgrass),addDrops);
    }

    public ModifyPlantMessage(ItemStack plant, DropItem... addDrops)
    {
        this(plant,Priority.SECOND,addDrops);
    }

    public ModifyPlantMessage(ItemStack plant, Priority priority, DropItem... addDrops)
    {
        this(plant,priority,true,new ItemStack[0],addDrops);
    }

    public ModifyPlantMessage(ItemStack... removeDrops)
    {
        this(new ItemStack(Blocks.tallgrass),removeDrops);
    }

    public ModifyPlantMessage(ItemStack plant, ItemStack... removeDrops)
    {
        this(plant,Priority.SECOND,removeDrops);
    }

    public ModifyPlantMessage(ItemStack plant, Priority priority, ItemStack... removeDrops)
    {
        this(plant,priority,false,removeDrops,new DropItem[0]);
    }

    public ModifyPlantMessage(ItemStack plant, Priority priority, boolean add, ItemStack[] removeDrops, DropItem[] addDrops)
    {
        this(plant,priority,add);
        this.addDrops = addDrops;
        this.removeDrops = removeDrops;
    }

    public ModifyPlantMessage(ItemStack plant, ItemStack[] removeDrops, DropItem[] addDrops)
    {
        this(plant,removeDrops,addDrops,Priority.SECOND);
    }

    public ModifyPlantMessage(ItemStack plant, ItemStack[] removeDrops, DropItem[] addDrops, Priority priority)
    {
        this(plant,removeDrops,addDrops,priority,priority);
    }

    public ModifyPlantMessage(ItemStack plant, ItemStack[] removeDrops, DropItem[] addDrops, Priority addPriority, Priority removePriority)
    {
        this(plant,addPriority,removePriority);
        this.addDrops = addDrops;
        this.removeDrops = removeDrops;
    }

    public ModifyPlantMessage(NBTTagCompound tagCompound)
    {
        super(tagCompound);
        this.plant = ItemStack.loadItemStackFromNBT(tagCompound.getCompoundTag(MessageKeys.ore));
        this.addDrops = MessageHelper.getDropItems(tagCompound, MessageKeys.addDrops);
        this.removeDrops = MessageHelper.getItemStacks(tagCompound, MessageKeys.removeDrops);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setTag(MessageKeys.ore, plant.writeToNBT(new NBTTagCompound()));
        tagCompound.setTag(MessageKeys.addDrops,MessageHelper.getDropItemList(addDrops));
        tagCompound.setTag(MessageKeys.removeDrops,MessageHelper.getItemStackList(removeDrops));
        return tagCompound;
    }

    @Override
    public boolean hasAdd()
    {
        return addDrops.length>0;
    }

    @Override
    public boolean hasRemove()
    {
        return removeDrops.length>0;
    }

    @Override
    public boolean isValid()
    {
        return plant!=null && (hasAdd() || hasRemove());
    }
}
