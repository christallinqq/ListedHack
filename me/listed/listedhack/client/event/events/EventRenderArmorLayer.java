package me.listed.listedhack.client.event.events;

import me.listed.listedhack.client.event.MinecraftEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EventRenderArmorLayer extends MinecraftEvent {
   public EntityLivingBase Entity;

   public EventRenderArmorLayer(EntityLivingBase p_Entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn) {
      this.Entity = p_Entity;
   }
}
