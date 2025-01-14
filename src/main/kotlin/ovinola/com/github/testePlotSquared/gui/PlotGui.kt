package ovinola.com.github.testePlotSquared.gui

import com.intellectualcrafters.plot.api.PlotAPI
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import ovinola.com.github.testePlotSquared.util.CustomHeadUtil
import ovinola.com.github.testePlotSquared.util.CustomItemUtil

class PlotGui {

    companion object {
        var guiInventory: Inventory? = null
    }

    private val plotAPI = PlotAPI()

    fun openGui(player: Player) {
        val inventory: Inventory = Bukkit.createInventory(null, (9 * 4), "Gerenciamento de Plot")
        guiInventory = inventory

        val maxPlots = (1..5).firstOrNull { player.hasPermission("plotmanager.plots.$it") } ?: 1
        val ownedPlots = plotAPI.getPlayerPlots(player).size

        val base64heads = mapOf(
            "comprar" to "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA1NmJjMTI0NGZjZmY5OTM0NGYxMmFiYTQyYWMyM2ZlZTZlZjZlMzM1MWQyN2QyNzNjMTU3MjUzMWYifX19",
            "bloqueado" to "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWMxNDYwMGFjZTUwNjk1YzdjOWJjZjA5ZTQyYWZkOWY1M2M5ZTIwZGFhMTUyNGM5NWRiNDE5N2RkMzExNjQxMiJ9fX0=",
            "disponivel" to "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNkMDJjZGMwNzViYjFjYzVmNmZlM2M3NzExYWU0OTc3ZTM4YjkxMGQ1MGVkNjAyM2RmNzM5MTNlNWU3ZmNmZiJ9fX0="
        )

        for (i in 0 until 5) {
            val slot = 11 + i
            val base64 = when {
                i < ownedPlots -> base64heads["disponivel"]
                i < maxPlots -> base64heads["comprar"]
                else -> base64heads["bloqueado"]
            }
            val displayName = when {
                i < ownedPlots -> "&aTerreno Disponível #${i + 1}"
                i < maxPlots -> "&aTerreno #${i + 1}"
                else -> "&cTerreno Bloqueado"
            }
            val lore = when {
                i < ownedPlots -> listOf("&fClique com o botão direito para...", "&fClique com o botão esquerdo para...")
                i < maxPlots -> listOf("&fClique para comprar um terreno aleatório.")
                else -> listOf("&fVocê não tem permissão para este número de plots.")
            }

            val customHead = CustomHeadUtil.getCustomHead(base64!!, displayName, lore)
            inventory.setItem(slot, customHead)
        }

        val compass = CustomItemUtil.createCustomItem(
            material = Material.COMPASS,
            displayName = "&aVisitar Jogador",
            lore = listOf("", "&fClique para visitar o Terreno", "&fde algum Jogador.")
        )
        val armazem = CustomItemUtil.createCustomItem(
            material = Material.CHEST,
            displayName = "&aArmazém",
            lore = listOf("", "&fClique para acessar o armazenamento", "&fde plantações da plot atual.")
        )
        val mundo = CustomItemUtil.createCustomItem(
            material = Material.NETHER_STAR,
            displayName = "&aMundo de Terrenos",
            lore = listOf("", "&fClique aqui para teleportar-se", "&fpara o mundo de Terrenos.")
        )
        val ajuda = CustomItemUtil.createCustomItem(
            material = Material.MINECART,
            displayName = "&aAjuda/Comandos",
            lore = listOf("", "&fClique para receber a ajuda", "&fdos comandos no chat.")
        )

        val plot = plotAPI.getPlot(player.location)
        val infos = if (plot == null) {
            CustomItemUtil.createCustomItem(
                material = Material.ENDER_PEARL,
                displayName = "&aVocê não está em uma plot",
                lore = listOf("", "&fVocê não está dentro de uma plot.")
            )
        }
        else {
            val plotId = plot.id
            val ownerUUID = plot.owners.firstOrNull()
            if (ownerUUID == null) {
                CustomItemUtil.createCustomItem(
                    material = Material.ENDER_PEARL,
                    displayName = "&aVocê não está em uma plot",
                    lore = listOf("", "&fVocê não está dentro de uma plot.")
                )
            } else {
                val ownerName = Bukkit.getOfflinePlayer(ownerUUID).name ?: "N/A"
                val biome = player.location.block.biome.name
                val base64 = CustomHeadUtil.getBase64FromUUID(ownerUUID)
                if (base64 != null) {
                    CustomHeadUtil.getCustomHead(
                        base64 = base64,
                        displayName = "&aInformações desse Terreno",
                        lore = listOf("", "&fInformações do Terreno em que está:",
                            "",
                            "&fID:&7 $plotId",
                            "&fDono:&7 $ownerName",
                            "&fBioma:&7 $biome",
                            "",
                            "&fClique para receber mais informações.",
                        )
                    )
                } else {
                    CustomItemUtil.createCustomItem(
                        material = Material.SIGN,
                        displayName = "&aInformações desse Terreno",
                        lore = listOf("", "&fInformações do Terreno em que está:",
                            "",
                            "&fID:&7 $plotId",
                            "&fDono:&7 $ownerName",
                            "&fBioma:&7 $biome",
                            "",
                            "&fClique para receber mais informações.",
                        )
                    )
                }
            }
        }

        inventory.setItem(28, compass)
        inventory.setItem(30, ajuda)
        inventory.setItem(31, armazem)
        inventory.setItem(32, mundo)
        inventory.setItem(34, infos)
        player.openInventory(inventory)
    }
}