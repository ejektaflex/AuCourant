package io.ejekta.sample

import net.minecraft.client.MinecraftClient
import net.minecraft.client.options.KeyBinding
import net.minecraft.client.util.InputUtil

object MixinProxy {

    fun handleKeyPress(key: InputUtil.Key, keyBinding: KeyBinding?) {
        val opt = MinecraftClient.getInstance().options
        val dir = when (keyBinding) {
            opt.keyLeft -> MoveInputManager.MovementDirection.LEFT
            opt.keyRight -> MoveInputManager.MovementDirection.RIGHT
            opt.keyForward -> MoveInputManager.MovementDirection.FORWARD
            opt.keyBack -> MoveInputManager.MovementDirection.BACKWARD
            else -> null
        }

        //dir?.update()
    }

}