package ovinola.com.github.testePlotSquared.listeners

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.inventory.ItemStack
import ovinola.com.github.testePlotSquared.PlotManager
import ovinola.com.github.testePlotSquared.gui.ManagerGui
import ovinola.com.github.testePlotSquared.gui.PlotGui
import java.util.*

class GuiClickListener : Listener {
    private val awaitingPlayers = mutableMapOf<UUID, (Player, String) -> Unit>()
    private val awaitingPlotIds = mutableMapOf<UUID, (Player, String, String) -> Unit>()

    @EventHandler
    fun onGuiClick(event: InventoryClickEvent) {
        val player = event.whoClicked as? Player ?: return
        val clickedItem: ItemStack? = event.currentItem

        if (event.inventory == PlotGui.guiInventory) {
            event.isCancelled = true

            val slot = event.slot
            if (slot in 11..15) {
                val plotNumber = slot - 10

                val displayName = clickedItem?.itemMeta?.displayName

                if (event.isLeftClick) {
                    if (displayName != null && displayName.contains("Disponível")) {
                        player.closeInventory()
                        player.performCommand("plots h $plotNumber")
                    } else if (displayName != null && displayName.contains("Bloqueado")) {
                        player.closeInventory()
                        player.sendMessage("§cVocê não tem permissão para este número de plots.")
                    } else if (displayName != null && displayName.contains("Terreno")) {
                        player.closeInventory()
                        player.performCommand("plots auto")
                    }
                    return
                } else if (event.isRightClick) {
                    if (displayName != null && displayName.contains("Disponível")) {
                        val managerGui = ManagerGui()
                        managerGui.openGui(player, plotNumber)
                    } else {
                        return
                    }
                }
            }

            if (clickedItem != null && clickedItem.hasItemMeta()) {
                when (ChatColor.stripColor(clickedItem.itemMeta?.displayName)) {
                    "Armazém" -> {
                        player.closeInventory()
                        player.performCommand("armazem")
                    }
                    "Mundo de Terrenos" -> {
                        player.closeInventory()
                        player.performCommand("warp terrenos")
                    }
                    "Ajuda/Comandos" -> {
                        player.closeInventory()
                        player.performCommand("plots")
                    }
                    "Informações desse Terreno" -> {
                        player.closeInventory()
                        player.performCommand("plots info")
                    }
                    "Visitar Jogador" -> {
                        player.closeInventory()
                        player.sendMessage("")
                        player.sendMessage("")
                        player.sendMessage("§fPor favor, digite " +
                                "o §anick §fdo jogador que deseja visitar no chat ou " +
                                "§ccancelar §fpara cancelar a operação.")
                        player.sendMessage("")
                        player.sendMessage("")
                        awaitingPlayers[player.uniqueId] = { _, target ->
                            player.sendMessage("§fPor favor, digite " +
                                    "o §aid §fda plot que deseja visitar no chat ou " +
                                    "§ccancelar §fpara cancelar a operação.")
                            awaitingPlotIds[player.uniqueId] = { _, _, plotId ->
                                player.performCommand("plots visit $target $plotId")
                            }
                        }
                    }
                }
            }
        }
        else if (event.inventory == ManagerGui.manageGui) {
            event.isCancelled = true

            val displayName = clickedItem?.itemMeta?.displayName

            if (displayName != null) {
                when {
                    displayName.contains("Adicionar Jogador") -> {
                        player.closeInventory()
                        player.sendMessage("")
                        player.sendMessage("")
                        player.sendMessage("§fPor favor, digite " +
                                "o §anick §fdo jogador que deseja adicionar no chat ou " +
                                "§ccancelar §fpara cancelar a operação.")
                        player.sendMessage("")
                        player.sendMessage("")
                        awaitingPlayers[player.uniqueId] = { _, targetPlayerName ->
                            player.performCommand("plots trust $targetPlayerName")
                        }
                    }

                    displayName.contains("Confiar Acesso") -> {
                        player.closeInventory()
                        player.sendMessage("")
                        player.sendMessage("")
                        player.sendMessage("§fPor favor, digite " +
                                "o §anick §fdo jogador que deseja confiar no chat ou" +
                                " §ccancelar §fpara cancelar a operação.")
                        player.sendMessage("")
                        player.sendMessage("")
                        awaitingPlayers[player.uniqueId] = { _, targetPlayerName ->
                            player.performCommand("plots trust $targetPlayerName")
                        }
                    }

                    displayName.contains("Banir Jogador") -> {
                        player.closeInventory()
                        player.sendMessage("")
                        player.sendMessage("")
                        player.sendMessage("§fPor favor, digite " +
                                "o §anick §fdo jogador que deseja banir no chat ou" +
                                " §ccancelar §fpara cancelar a operação.")
                        player.sendMessage("")
                        player.sendMessage("")
                        awaitingPlayers[player.uniqueId] = { _, targetPlayerName ->
                            player.performCommand("plots deny $targetPlayerName ban")
                        }
                    }

                    displayName.contains("Remover Jogador") -> {
                        player.closeInventory()
                        player.sendMessage("")
                        player.sendMessage("")
                        player.sendMessage("§fPor favor, digite " +
                                "o §anick §fdo jogador que deseja remover no chat ou" +
                                " §ccancelar §fpara cancelar a operação.")
                        player.sendMessage("")
                        player.sendMessage("")
                        awaitingPlayers[player.uniqueId] = { _, targetPlayerName ->
                            player.performCommand("plots remove $targetPlayerName remove")
                        }
                    }

                    displayName.contains("Resetar Terreno") -> {
                        player.closeInventory()
                        player.performCommand("plots clear")
                    }

                    displayName.contains("Abandonar Terreno") -> {
                        player.closeInventory()
                        player.performCommand("plots delete")
                    }

                    displayName.contains("Definir Spawn") -> {
                        player.closeInventory()
                        player.performCommand("plots sethome")
                    }

                    displayName.contains("Definir Bioma") -> {
                        player.closeInventory()
                        player.performCommand("plots biome")
                        player.sendMessage("")
                        player.sendMessage("§fPor favor, digite " +
                                "o §abioma §fque deseja definir em sua plot no chat ou " +
                                "§ccancelar §fpara cancelar a operação.")
                        player.sendMessage("")
                        player.sendMessage("")
                        awaitingPlayers[player.uniqueId] = { _, biomeName ->
                            if (biomeName.equals("cancelar", ignoreCase = true)) {
                                player.sendMessage("§cOperação cancelada.")
                                awaitingPlayers.remove(player.uniqueId)
                            } else {
                                player.performCommand("plots biome $biomeName")
                                player.sendMessage("§aBioma definido com sucesso para: §f$biomeName")
                            }
                        }
                    }

                    displayName.contains("Voltar") -> {
                        player.closeInventory()
                        val plotGui = PlotGui()
                        plotGui.openGui(player)
                    }
                }
            }
        }
    }

    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        val player = event.player

        awaitingPlayers[player.uniqueId]?.let { action ->
            event.isCancelled = true
            val targetPlayerName = event.message

            if (targetPlayerName.equals("cancelar", ignoreCase = true)) {
                player.sendMessage("§cOperação cancelada.")
                awaitingPlayers.remove(player.uniqueId)
                return
            }

            Bukkit.getScheduler().runTask(PlotManager.instance) {
                action(player, targetPlayerName)
            }

            awaitingPlayers.remove(player.uniqueId)
            event.recipients.clear()
        }

        awaitingPlotIds[player.uniqueId]?.let { action ->
            event.isCancelled = true
            val plotId = event.message
            val targetPlayerName = event.message

            if (plotId.equals("cancelar", ignoreCase = true)) {
                player.sendMessage("§cOperação cancelada.")
                awaitingPlotIds.remove(player.uniqueId)
                return
            }

            Bukkit.getScheduler().runTask(PlotManager.instance) {
                action(player, targetPlayerName, plotId)
            }

            awaitingPlotIds.remove(player.uniqueId)
            event.recipients.clear()
        }
    }
}