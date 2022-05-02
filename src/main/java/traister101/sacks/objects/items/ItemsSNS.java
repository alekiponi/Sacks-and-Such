package traister101.sacks.objects.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import traister101.sacks.util.SackType;

public class ItemsSNS {

	// Makes an array that all the items go in
	public static final List<Item> ITEMS = new ArrayList();

	// Items
	public static Item THATCH_SACK = new ItemSack("thatch_sack", SackType.THATCH);
	public static Item LEATHER_SACK = new ItemSack("leather_sack", SackType.LEATHER);
	public static Item BURLAP_SACK = new ItemSack("burlap_sack", SackType.BURLAP);
	//public static Item MINERS_SACK = new ItemSack("miners_sack", SackType.MINERS);
	public static Item UNFINISHED_LEATHER_SACK = new ItemBase("unfinished_leather_sack");

}