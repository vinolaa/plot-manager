package ovinola.com.github.testePlotSquared.listeners

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import ovinola.com.github.testePlotSquared.gui.PlotGui

class GuiClickListener : Listener {

    @EventHandler
    fun onGuiClick(event: InventoryClickEvent) {
        val player = event.whoClicked as? Player ?: return
        val clickedItem: ItemStack? = event.currentItem

        if (event.inventory == PlotGui.guiInventory) {
            event.isCancelled = true
        }

        if (clickedItem != null && clickedItem.type == Material.CHEST) {
            if (clickedItem.hasItemMeta() && clickedItem.itemMeta?.displayName == ChatColor.translateAlternateColorCodes(
                    '&',
                    "&aArmazém"
                )
            ) {
                player.closeInventory()
                player.performCommand("armazem")
            }
            return
        }

        if (clickedItem != null && clickedItem.type == Material.NETHER_STAR) {
            if (clickedItem.hasItemMeta() && clickedItem.itemMeta?.displayName == ChatColor.translateAlternateColorCodes(
                    '&',
                    "&aMundo de Terrenos"
                )
            ) {
                player.closeInventory()
                player.performCommand("warp terrenos")
            }
            return
        }

        if (clickedItem != null && clickedItem.type == Material.MINECART) {
            if (clickedItem.hasItemMeta() && clickedItem.itemMeta?.displayName == ChatColor.translateAlternateColorCodes(
                    '&',
                    "&aAjuda/Comandos"
                )
            ) {
                player.closeInventory()
                player.performCommand("plots")
            }
            return
        }

        if (clickedItem != null && clickedItem.type == Material.SIGN) {
            if (clickedItem.hasItemMeta() && clickedItem.itemMeta?.displayName == ChatColor.translateAlternateColorCodes(
                    '&',
                    "&aInformações desse Terreno"
                )
            ) {
                player.closeInventory()
                player.performCommand("plots info")
            }
            return
        }
    }

}