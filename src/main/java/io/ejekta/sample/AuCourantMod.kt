package io.ejekta.sample

import io.ejekta.kambrik.Kambrik
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient

class AuCourantMod : ModInitializer {

    internal companion object {
        const val ID = "aucourant"
        val logger = Kambrik.Logging.createLogger(ID)
    }


    override fun onInitialize() {
        logger.info("Au Courant Says Hello!")

    }


}