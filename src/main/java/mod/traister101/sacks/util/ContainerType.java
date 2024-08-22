package mod.traister101.sacks.util;

import net.dries007.tfc.common.capabilities.size.Size;

import mod.traister101.sacks.common.items.ContainerItem;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;

public interface ContainerType extends StringRepresentable {

	/**
	 * @return If the passed {@link ItemStack} supports auto pickup
	 */
	@SuppressWarnings("BooleanMethodIsAlwaysInverted")
	static boolean canDoItemPickup(final ItemStack itemStack) {
		if (!(itemStack.getItem() instanceof ContainerItem containerItem)) return false;

		if (!containerItem.getType().doesAutoPickup()) return false;

		return NBTHelper.isAutoPickup(itemStack);
	}

	/**
	 * @return If the passed {@link ItemStack} supports voiding
	 */
	@SuppressWarnings("BooleanMethodIsAlwaysInverted")
	static boolean canDoItemVoiding(final ItemStack itemStack) {
		if (!(itemStack.getItem() instanceof ContainerItem containerItem)) return false;

		if (!containerItem.getType().doesVoiding()) return false;

		return NBTHelper.isAutoVoid(itemStack);
	}

	/**
	 * @return The amount of slots this {@link ContainerType}
	 */
	int getSlotCount();

	/**
	 * @return The slot capacity of this {@link ContainerType}
	 */
	int getSlotCapacity();

	/**
	 * @return If this {@link ContainerType} supports item pickup
	 */
	boolean doesAutoPickup();

	/**
	 * @return If this {@link ContainerType} supports item voiding
	 */
	boolean doesVoiding();

	/**
	 * @return If this {@link ContainerType} allows you to tranasfer items via the inventory
	 */
	@SuppressWarnings("BooleanMethodIsAlwaysInverted")
	boolean doesInventoryInteraction();

	/**
	 * @return The largest allowed {@link Size} inside the sack
	 */
	Size getAllowedSize();

	/**
	 * Abstracted out to allow custom overrides without the need of a new item class. Called by {@link ContainerItem#getSize(ItemStack)}
	 *
	 * @param itemStack The {@link ContainerItem} instance
	 *
	 * @return Size for the stack
	 */
	Size getSize(final ItemStack itemStack);
}