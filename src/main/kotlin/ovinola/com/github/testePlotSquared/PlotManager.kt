package ovinola.com.github.testePlotSquared

import org.bukkit.plugin.java.JavaPlugin
import ovinola.com.github.testePlotSquared.commands.PlotCommand
import ovinola.com.github.testePlotSquared.listeners.GuiClickListener

class PlotManager : JavaPlugin() {

    companion object {
        lateinit var instance: PlotManager
            private set
    }

    private fun regCmds() {
        getCommand("plot")?.executor = PlotCommand()
        getCommand("plotme")?.executor = PlotCommand()
        getCommand("p")?.executor = PlotCommand()
    }

    private fun regListeners() {
        server.pluginManager.registerEvents(GuiClickListener(), this)
    }

    override fun onEnable() {
        instance = this

        regCmds()
        regListeners()
    }

    override fun onDisable() {

    }

}
