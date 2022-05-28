package mod.traister101.sacks.objects.inventory.slot;

import javax.annotation.Nonnull;

import mod.traister101.sacks.objects.inventory.capability.SackHandler;
import mod.traister101.sacks.objects.inventory.capability.VesselHandler;
import mod.traister101.sacks.objects.items.ItemSack;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class VesselSlot extends SlotItemHandler {
	
	private final VesselHandler handler;
	private final int index;
	
	public VesselSlot(@Nonnull IItemHandler inventory, int idx, int x, int y, @Nonnull VesselHandler handler, int index) {
        super(inventory, idx, x, y);
        this.handler = handler;
        this.index = index;
    }
	
	@Override
	public int getSlotStackLimit() {
		return Math.min(handler.getSlotLimit(getSlotIndex()), super.getSlotStackLimit());
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return handler.getSlotLimit(slotNumber);
	}
	
	@Override
	public void putStack(@Nonnull ItemStack stack) {
		getItemHandler().setStackInSlot(index, stack);
		onSlotChanged();
	}
	
	@Override
	public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_) {
		getItemHandler().onContentsChanged(index);
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		return !getItemHandler().extractItem(index, 1, true).isEmpty();
	}
	
	@Override
	public VesselHandler getItemHandler() {
		return handler;
	}
	
	@Override
	public boolean isItemValid(@Nonnull ItemStack stack) {
		if (stack.isEmpty()) return false;
		if (!stack.isStackable()) return false;
		// Stack is a sack, no sack-ception
		if (stack.getItem() instanceof ItemSack) return false;
		// If the item is larger than normal
		
		
		
		ItemStack currentStack = getItemHandler().getStackInSlot(index);
		getItemHandler().setStackInSlot(index, ItemStack.EMPTY);
		ItemStack remainder = getItemHandler().insertItem(index, stack, true);
		getItemHandler().setStackInSlot(index, currentStack);
		
		return remainder.isEmpty() || remainder.getCount() < stack.getCount();
	}
	
	@Override
	@Nonnull
	public ItemStack getStack() {
		return getItemHandler().getStackInSlot(index);
	}
	
	@Override
	@Nonnull
	public ItemStack decrStackSize(int amount) {
		return getItemHandler().extractItem(index, amount, false);
	}
	
	@Override
	public boolean isSameInventory(Slot other) {
		return other instanceof VesselSlot && ((VesselSlot) other).getItemHandler() == handler;
	}
}