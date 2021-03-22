package io.ejekta.sample

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import java.util.*

object DodgeManager {

    data class DodgeTiming(val start: Long, val length: Long) {
        fun isInFrame(ticks: Long) = ticks in (start..(start + length))
    }

    private val timings = mutableMapOf<String, DodgeTiming>()

    fun isDodging(playerEntity: PlayerEntity): Boolean {
        return timings[playerEntity.uuidAsString]?.isInFrame(playerEntity.world.time) ?: false
    }

    fun updateTiming(playerEntity: PlayerEntity, length: Long) {
        timings[playerEntity.uuidAsString] = DodgeTiming(playerEntity.world.time, length)
    }

}