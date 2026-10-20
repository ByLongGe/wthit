package mcp.mobius.waila.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import mcp.mobius.waila.mixed.IClientMixinService;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {

    // Minecraft 26.3 receives SDL-compatible action values: released=0, pressed=1.
    private static final int RELEASE = 0;
    private static final int PRESS = 1;

    @Inject(method = "keyPress", at = @At("HEAD"))
    private void wthit_onKeyPress(long l, int action, KeyEvent event, CallbackInfo ci) {
        if (action != PRESS && action != RELEASE) return;

        var key = InputConstants.getKey(event);
        IClientMixinService.INSTANCE.onButton(key, action == PRESS);
    }

}
