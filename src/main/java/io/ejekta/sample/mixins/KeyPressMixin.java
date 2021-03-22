package io.ejekta.sample.mixins;


import io.ejekta.sample.MixinProxy;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.annotation.Nullable;

@Mixin(KeyBinding.class)
public abstract class KeyPressMixin {
    @Environment(EnvType.CLIENT)
    @Inject(method = "onKeyPressed", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void au_onKeyPressed(InputUtil.Key key, CallbackInfo ci, @Nullable KeyBinding keyBinding) {
        MixinProxy.INSTANCE.handleKeyPress(key, keyBinding);
    }
}
