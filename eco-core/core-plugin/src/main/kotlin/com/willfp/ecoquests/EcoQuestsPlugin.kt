package com.willfp.ecoquests

import com.willfp.ecoquests.quests.Quests
import com.willfp.ecoquests.tasks.Tasks
import com.willfp.libreforge.loader.LibreforgePlugin
import com.willfp.libreforge.loader.configs.ConfigCategory
import org.bukkit.Bukkit


class EcoQuestsPlugin : LibreforgePlugin() {
    override fun createTasks() {
        val scanInterval = this.configYml.getInt("scan-interval").toLong()
        this.scheduler.runTimer(scanInterval, scanInterval) {
            for (quest in Quests.values()) {
                for (player in Bukkit.getOnlinePlayers()) {
                    if (quest.shouldStart(player)) {
                        quest.start(player)
                    }
                }
            }
        }
    }

    override fun loadConfigCategories(): List<ConfigCategory> {
        return listOf(
            Tasks,
            Quests
        )
    }
}