package ovinola.com.github.testePlotSquared

import org.bukkit.plugin.java.JavaPlugin
import ovinola.com.github.testePlotSquared.commands.PlotCommand
import ovinola.com.github.testePlotSquared.listeners.GuiClickListener

class TestePlotSquared : JavaPlugin() {

    companion object {
        lateinit var instance: TestePlotSquared
            private set
    }

    private fun regCmds() {
        getCommand("plot")?.executor = PlotCommand()
    }

    private fun regListeners() {
        server.pluginManager.registerEvents(GuiClickListener(), this)
    }

    override fun onEnable() {
        regCmds()
        regListeners()
    }

    override fun onDisable() {

    }

}
