package io.ejekta.sample.client

import io.ejekta.kambrikx.ext.vector.axialMask
import io.ejekta.kambrikx.ext.vector.flipMask
import io.ejekta.kambrikx.ext.vector.plus
import io.ejekta.kambrikx.ext.vector.times
import io.ejekta.sample.AuCourantMod
import io.ejekta.sample.MoveInputManager
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d

class AuCourantClientInit : ClientModInitializer {

    override fun onInitializeClient() {
        AuCourantMod.logger.info("Au Courant Client Says Hello!")


        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick {
            val act = MinecraftClient.getInstance().options.keyUse

            //println(act.isPressed)

            MoveInputManager.MovementDirection.values().forEach {
                it.update()
                if (it.hasPressedInTicks(2, 10)) {
                    println("Pressed '${it.name}' in the last few ticks!")
                    it.clear()

                    val player = MinecraftClient.getInstance().player ?: return@EndTick

                    if (player.isSneaking) {
                        return@EndTick
                    }

                    //player.velocity += (player.rotationVecClient)

                    var forwardVector = player.rotationVecClient.flipMask(Direction.UP).normalize()

                    val newVec2d = it.transform(forwardVector.x, forwardVector.z)
                    val groundVel = if (player.shouldSlowDown()) 10.0 else 0.0
                    val newVec3d = Vec3d(newVec2d.first, groundVel, newVec2d.second)

                    forwardVector = newVec3d



                    player.velocity += forwardVector

                }
            }



        })

    }

}