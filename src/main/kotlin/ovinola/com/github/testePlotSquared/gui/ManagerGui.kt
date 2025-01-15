package ovinola.com.github.testePlotSquared.gui

import com.intellectualcrafters.plot.api.PlotAPI
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.metadata.FixedMetadataValue
import ovinola.com.github.testePlotSquared.PlotManager
import ovinola.com.github.testePlotSquared.util.CustomHeadUtil
import ovinola.com.github.testePlotSquared.util.CustomItemUtil

class ManagerGui {

    companion object {
        var manageGui: Inventory? = null
    }

    fun openGui(player: Player, plotNumber: Int) {
        val inventory: Inventory = Bukkit.createInventory(null, (9 * 5), "GUI Secundário")
        manageGui = inventory

        player.setMetadata("plotNumber", FixedMetadataValue(PlotManager.instance, plotNumber))

        val addPlayer = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI1YjhlZWQ1YzU2NWJkNDQwZWM0N2M3OWMyMGQ1Y2YzNzAxNjJiMWQ5YjVkZDMxMDBlZDYyODNmZTAxZDZlIn19fQ==",
            "&aAdicionar Jogador",
            listOf(
                "",
                "&fClique para adicionar um jogador",
                "&fao seu &aTerreno #${plotNumber}.",
                "",
                "&e&l| &fEsse jogador poderá mexer no terreno",
                "&e&l| &fquando você estiver online."
            )
        )

        val trustPlayer = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg5YjkzZmQ2MTZlZDM2NzBjY2Y2NDdhMGY5MzgwMzk4YzBkNDYxNTYzNGYyZGVmZjQ2YzZlZGJkYzcxMjg4NSJ9fX0=",
            "&aConfiar Acesso",
            listOf(
                "",
                "&fClique para confiar um jogador",
                "&fao seu &aTerreno #${plotNumber}.",
                "",
                "&e&l| &fEsse jogador poderá mexer no terreno",
                "&e&l| &fquando você estiver online e offline."
            )
        )

        val banPlayer = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA5MGQwOWUxNzNlZTM0MTM4YzNiMDFiNDhlZTBiZTUzNGJiYjFhY2UwZGRmNWZmOThlNjZmN2IwMjExMzk5NSJ9fX0=",
            "&aBanir Jogador",
            listOf(
                "",
                "&fClique para banir um jogador",
                "&fao seu &aTerreno #${plotNumber}.",
                "",
                "&e&l| &fEsse jogadornão irá mais",
                "&e&l| &fconseguir entrar em seu terreno."
            )
        )

        val removePlayer = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjhkNDA5MzUyNzk3NzFhZGM2MzkzNmVkOWM4NDYzYWJkZjVjNWJhNzhkMmU4NmNiMWVjMTBiNGQxZDIyNWZiIn19fQ==",
            "&aRemover Jogador",
            listOf(
                "",
                "&fClique para remover um jogador",
                "&fdo seu &aTerreno #${plotNumber}.",
                "",
                "&e&l| &fEsse jogador não poderá mais",
                "&e&l| &fmexer no terreno."
            )
        )

        val resetTerreno = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTgwNmU2N2UzYzY0OWY4YTliN2M3ZDYyYzc2NTk5MjdkMmJkNGNjMDFjMjIwMzk3ZjExMjE1OWI2YTU5NWU1YiJ9fX0=",
            "&aResetar Terreno",
            listOf(
                "",
                "&fClique para resetar o terreno",
                "&fpara o estado inicial.",
                "",
                "&e&l| &cCuidado! Essa ação é &oirreversível."
            )
        )

        val deleteTerreno = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzZmNDI0ZmJkZjc1ZjY3NWU4ZDI2YmVmZDI2ZDY0YzkwNjY3YWU1NGFjYzZkZjk0ZWExYjg0NjMzZmMxZGVkYyJ9fX0=",
            "&aAbandonar Terreno",
            listOf(
                "",
                "&fClique para abandonar o terreno",
                "&fpermanentemente.",
                "",
                "&e&l| &cCuidado! Essa ação é &oirreversível."
            )
        )

        val setSpawn = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGRjMGY2ZjRiODFlZDc3YWJiYTVjODg1MjZmNTViNDZlNWE3ZDg4YjhjMDkzNWQ4ZGQyZWMyMjg3NTU4Nzk4MSJ9fX0=",
            "&aDefinir Spawn",
            listOf(
                "",
                "&fClique para setar o spawn",
                "&fdo terreno.",
                "",
                "&e&l| &fEsse será o local onde",
                "&e&l| &fos jogadores irão spawnar."
            )
        )

        val setBioma = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODI1MjE4YTM1ODI1MjAzYjdlNGRlNDY0ZmUyMmNkN2JiZDY0NzFiMWIyYmVmODZjNzkyOWYxZjgwNzI5OTZlIn19fQ==",
            "&aDefinir Bioma",
            listOf(
                "",
                "&fClique para setar o bioma",
                "&fdo terreno.",
                "",
                "&e&l| &fEsse será o bioma do terreno."
            )
        )

        val backArrow = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWY3ZGFkZjEwNjNiNGQ0NDE5ZWQ0YTVkOTAwNDU1NzU0OTg4YjIzNDE4YmFkOGNjYTJiZjc5NTBjMzA3MGFiZiJ9fX0=",
            "&aVoltar",
            listOf(
                "",
                "&fClique para voltar ao menu principal."
            )
        )

        val compass = CustomItemUtil.createCustomItem(
            Material.COMPASS,
            "&aTeleportar-se",
            listOf(
                "",
                "&fClique para teleportar-se",
                "&fpara o Terreno #${plotNumber}."
            )
        )

        val confirm = CustomHeadUtil.getCustomHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTc5YTVjOTVlZTE3YWJmZWY0NWM4ZGMyMjQxODk5NjQ5NDRkNTYwZjE5YTQ0ZjE5ZjhhNDZhZWYzZmVlNDc1NiJ9fX0=",
            "&aConfirmar",
            listOf(
                "",
                "&fClique para confirmar ações pendentes."
            )
        )

        val ajuda = CustomItemUtil.createCustomItem(
            material = Material.MINECART,
            displayName = "&aAjuda/Comandos",
            lore = listOf("", "&fClique para receber a ajuda", "&fdos comandos no chat.")
        )

        val plotAPI = PlotAPI()
        val plot = plotAPI.getPlot(player.location)
        val infos = if (plot == null) {
            CustomItemUtil.createCustomItem(
                material = Material.ENDER_PEARL,
                displayName = "&aVocê não está em uma plot",
                lore = listOf("", "&fVocê não está dentro de uma plot.")
            )
        } else {
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
                        lore = listOf(
                            "",
                            "&fInformações do Terreno em que está:",
                            "",
                            "&fID:&7 $plotId",
                            "&fDono:&7 $ownerName",
                            "&fBioma:&7 $biome",
                            "",
                            "&fClique para receber mais informações."
                        )
                    )
                } else {
                    CustomItemUtil.createCustomItem(
                        material = Material.SIGN,
                        displayName = "&aInformações desse Terreno",
                        lore = listOf(
                            "",
                            "&fInformações do Terreno $plotNumber",
                            "",
                            "&fID:&7 $plotId",
                            "&fDono:&7 $ownerName",
                            "&fBioma:&7 $biome",
                            "",
                            "&fClique para receber mais informações."
                        )
                    )
                }
            }
        }

        inventory.setItem(11, addPlayer)
        inventory.setItem(12, trustPlayer)
        inventory.setItem(13, banPlayer)
        inventory.setItem(14, removePlayer)
        inventory.setItem(15, resetTerreno)
        inventory.setItem(21, deleteTerreno)
        inventory.setItem(22, setSpawn)
        inventory.setItem(23, setBioma)
        inventory.setItem(36, backArrow)
        inventory.setItem(39, compass)
        inventory.setItem(40, confirm)
        inventory.setItem(41, ajuda)
        inventory.setItem(44, infos)

        player.openInventory(inventory)
    }
}
