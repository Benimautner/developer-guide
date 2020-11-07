import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class InventoryHelper {

    public static int count(Inventory inventory, Material material) {
        return Arrays.stream(inventory.getContents()).filter(itemStack -> itemStack != null && itemStack.getType().equals(material)).mapToInt(ItemStack::getAmount).sum();
    }

    public static boolean take(Inventory inventory, Material material, int count) {
        if (count > count(inventory, material)) {
            return false;
        }
        inventory.removeItem(new ItemStack(material, count));
        return true;
    }

    public static void takeAll(Inventory inventory, Material material) {
        take(inventory, material, count(inventory, material));
    }
}
