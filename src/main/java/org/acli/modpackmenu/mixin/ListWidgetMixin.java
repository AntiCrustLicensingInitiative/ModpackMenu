package org.acli.modpackmenu.mixin;

import com.mojang.blaze3d.systems.RenderSystem;

import org.acli.modpackmenu.ModpackMenuMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ListWidget;
import net.minecraft.util.Identifier;

@Mixin(ListWidget.class)
public abstract class ListWidgetMixin {
    @Shadow
    protected MinecraftClient minecraft;
    
    @Inject(method = "render(IIF)V", at = @At(value = "INVOKE", target = "net/minecraft/client/render/BufferBuilder.begin(ILnet/minecraft/client/render/VertexFormat;)V"))
    private void changeBackDirtBackground(CallbackInfo info) {
        this.minecraft.getTextureManager().bindTexture(new Identifier(ModpackMenuMod.config.backgroundTextureIdentifier));
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
    
    @Inject(method = "renderHoleBackground(IIII)V", at = @At(value = "INVOKE", target = "net/minecraft/client/render/BufferBuilder.begin(ILnet/minecraft/client/render/VertexFormat;)V"))
    private void changeFrontDirtBackground(CallbackInfo info) {
        this.minecraft.getTextureManager().bindTexture(new Identifier(ModpackMenuMod.config.backgroundTextureIdentifier));
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}