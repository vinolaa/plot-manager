package ovinola.com.github.testePlotSquared.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ovinola.com.github.testePlotSquared.gui.PlotGui

class PlotCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) {
            sender.sendMessage("Apenas jogadores podem usar este comando!")
            return true
        }

        val gui = PlotGui()
        gui.openGui(sender)

        return true
    }
}