package ovinola.com.github.testePlotSquared.util

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.ChatColor

object CustomItemUtil {
    fun createCustomItem(material: Material, displayName: String, lore: List<String> = listOf()): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta ?: return item

        // Adicionar nome e lore ao item
        meta.displayName = ChatColor.translateAlternateColorCodes('&', displayName)
        meta.lore = lore.map { ChatColor.translateAlternateColorCodes('&', it) }

        item.itemMeta = meta
        return item
    }
}
