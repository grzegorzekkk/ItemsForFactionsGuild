package pl.stillcraft.grzegorzekkk.itemsforfactionsguild.listeners;

import com.massivecraft.factions.event.EventFactionsCreate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.utils.ConfigStorage;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

/**
 * Listener class for factions Guild Create event
 */
public class FactionCreateListener implements Listener {

    /**
     * Main listener function
     *
     * @param e Catched factions create event
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onFactionCreate(EventFactionsCreate e) {

        // Sender of command is not player so leave
        if (!(e.getSender() instanceof Player)) return;

        // Are there any required items to create a guild?
        List<ItemStack> requiredItems = ConfigStorage.getRequiredItems();
        if (requiredItems == null || requiredItems.isEmpty()) return;


        // Assume player can create new Guild
        boolean canCreate = true;

        // Message constructors
        StringBuilder sb = new StringBuilder();
        StringJoiner sj = new StringJoiner(", ");

        sb.append(ConfigStorage.getInsufficientRsrcsMsg());

        Player p = (Player) e.getSender();
        PlayerInventory playerInventory = p.getInventory();

        // Check for required items
        for (ItemStack reqItem : requiredItems) {
            String reqItemName = reqItem.getData().toString().substring(0, reqItem.getData().toString().length() - 3);

            int invItemAmount = sumMaterial(playerInventory.all(reqItem.getType()), reqItem.getData());

            // Player does not have one of required items in his inventory
            if (!playerInventory.containsAtLeast(reqItem, reqItem.getAmount())) {
                sj.add(reqItem.getAmount() - invItemAmount + " " + reqItemName);
                canCreate = false;
            }
        }

        // Construct not enough items info
        sb.append(sj.toString());

        if (!canCreate) {  // User cant create new Guild
            p.sendMessage(ConfigStorage.getMsgFormat(sb.toString()));
            e.setCancelled(true);
        } else {  // User can create new Guild
            requiredItems.stream().forEach(playerInventory::removeItem);
            p.updateInventory();
        }
    }

    /**
     * @param itemMap HashMap containing slot ids and itemstacks of one Material type from inventory
     * @param md      MaterialData of ItemStack
     * @return int sum of specified in arguments Material from inventory
     */
    private int sumMaterial(final HashMap<Integer, ? extends ItemStack> itemMap, final MaterialData md) {
        int sum = 0;
        for (final ItemStack itemstack : itemMap.values()) {
            if (itemstack.getData().getData() == md.getData()) {
                sum += itemstack.getAmount();
            }
        }
        return sum;
    }
}
