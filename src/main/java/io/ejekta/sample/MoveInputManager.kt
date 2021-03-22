package io.ejekta.sample

import net.minecraft.client.MinecraftClient
import net.minecraft.client.options.KeyBinding

object MoveInputManager {
    enum class MovementDirection(
        val wrapped: () -> KeyBinding,
        val transform: (x: Double, y: Double) -> Pair<Double, Double>,
        private var recorded: Boolean = false,
        private val recent: MutableList<Long> = mutableListOf()
    ) {
        FORWARD({ MinecraftClient.getInstance().options.keyForward }, { x, y -> x to y }),
        BACKWARD({ MinecraftClient.getInstance().options.keyBack }, { x, y -> -x to -y }),
        LEFT({ MinecraftClient.getInstance().options.keyLeft }, { x, y -> y to -x }),
        RIGHT({ MinecraftClient.getInstance().options.keyRight }, { x, y -> -y to x });

        private fun push() {
            if (recent.size >= CAPACITY) {
                recent.removeLast()
            }
            recent.add(0, localTime)
        }

        fun update() {
            val isPressed = wrapped().isPressed
            //println("Is pressed?: $isPressed, $recorded")
            if (isPressed && !recorded) {
                recorded = true
                push()
            }
            if (!isPressed && recorded) {
                recorded = false
            }
        }

        fun clear() {
            recent.clear()
        }

        fun hasPressedInTicks(num: Int, inTicks: Int): Boolean {
            if (recent.size < num) {
                return false
            }
            return recent.slice(0 until num).all { it + inTicks > localTime }
        }

        companion object {
            const val CAPACITY = 10
            val localTime: Long
                get() = MinecraftClient.getInstance().world?.time ?: 0
        }

    }

    init {
        MovementDirection.BACKWARD
    }


}