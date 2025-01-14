package ovinola.com.github.testePlotSquared.util

import com.google.gson.JsonParser
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.net.URI
import java.util.*

object CustomHeadUtil {
    fun getCustomHead(base64: String, displayName: String, lore: List<String> = listOf()): ItemStack {
        val skull = ItemStack(Material.valueOf("SKULL_ITEM"), 1)
        skull.durability = 3 // Configuração necessária para cabeças de jogador na versão 1.8.8

        val meta = skull.itemMeta as? SkullMeta ?: return skull

        // Criar GameProfile com textura
        val profile = GameProfile(UUID.randomUUID(), "CustomHeadPlugin")
        profile.properties.put("textures", Property("textures", base64))

        // Usar Reflection para injetar o GameProfile na SkullMeta
        try {
            val profileField = meta.javaClass.getDeclaredField("profile")
            profileField.isAccessible = true
            profileField.set(meta, profile)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Configurar o nome do item
        meta.displayName = ChatColor.translateAlternateColorCodes('&', displayName)
        meta.lore = lore.map { ChatColor.translateAlternateColorCodes('&', it) }

        skull.itemMeta = meta

        return skull
    }

    fun getBase64FromUUID(uuid: UUID): String? {
        val uri = URI("https://sessionserver.mojang.com/session/minecraft/profile/$uuid?unsigned=false")
        val url = uri.toURL()
        val connection = url.openConnection()
        val parser = JsonParser()
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        connection.connect()

        val response = connection.getInputStream().bufferedReader().use { it.readText() }
        val json = parser.parse(response).asJsonObject
        val properties = json.getAsJsonArray("properties")
        for (property in properties) {
            val propertyObj = property.asJsonObject
            if (propertyObj.get("name").asString == "textures") {
                return propertyObj.get("value").asString
            }
        }
        return null
    }
}