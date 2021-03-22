package io.ejekta.sample

import io.ejekta.kambrik.api.network.server.ServerMsg
import io.ejekta.kambrik.api.network.server.ServerMsgHandler
import kotlinx.serialization.Serializable
import net.minecraft.util.Identifier

/*
@Serializable
class FrameMessage(val ticks: Int) : ServerMsg<FrameMessage>(Handler) {

    override fun onServerReceived(ctx: ServerMsgContext) {
        println("Got frame ticks: $ticks")
    }

    companion object {
        val Handler = ServerMsgHandler(Identifier(AuCourantMod.ID, "frame") to FrameMessage.serializer())
    }
}

 */