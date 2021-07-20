package me.listed.listedhack.mixins;

import io.netty.channel.ChannelHandlerContext;
import me.listed.listedhack.client.event.WurstplusEventBus;
import me.listed.listedhack.client.event.events.WurstplusEventPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetworkManager.class})
public class WurstplusMixinNetworkManager {
   @Inject(
      method = {"channelRead0"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void receive(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callback) {
      WurstplusEventPacket event_packet = new WurstplusEventPacket.ReceivePacket(packet);
      WurstplusEventBus.EVENT_BUS.post(event_packet);
      if (event_packet.isCancelled()) {
         callback.cancel();
      }

   }

   @Inject(
      method = {"sendPacket(Lnet/minecraft/network/Packet;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void send(Packet<?> packet, CallbackInfo callback) {
      WurstplusEventPacket event_packet = new WurstplusEventPacket.SendPacket(packet);
      WurstplusEventBus.EVENT_BUS.post(event_packet);
      if (event_packet.isCancelled()) {
         callback.cancel();
      }

   }

   @Inject(
      method = {"exceptionCaught"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void exception(ChannelHandlerContext exc, Throwable exc_, CallbackInfo callback) {
      if (exc_ instanceof Exception) {
         callback.cancel();
      }

   }
}
