package ovinola.com.github.testePlotSquared.gui

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import ovinola.com.github.testePlotSquared.util.CustomHeadUtil

class ManagerGui {

    companion object {
        var manageGui: Inventory? = null
    }

    fun openGui(player: Player, plotNumber: Int) {
        val inventory: Inventory = Bukkit.createInventory(null, (9 * 5), "GUI Secundário")
        manageGui = inventory

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

        val removePlayer = CustomHeadUtil.getCustomHead(
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

        inventory.setItem(11, addPlayer)
        inventory.setItem(12, removePlayer)

        player.openInventory(inventory)
    }
}
