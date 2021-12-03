package com.guilib.events;

import net.minestom.server.entity.Player;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.click.ClickType;

public class ButtonClickEvent {

    private final InventoryPreClickEvent event;

    public ButtonClickEvent(InventoryPreClickEvent event) {
        this.event = event;
    }

    /**
     * Get the player who clicked on the button
     *
     * @return the player who clicked the button
     */
    public Player getPlayer() {
        return event.getPlayer();
    }

    /**
     * Set if the event is cancelled
     * true = Player can't take the item
     * false = Player can take the item
     *
     * @param cancelled if the event is cancelled
     */
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /**
     * Get the clicked inventory
     *
     * @return clicked inventory
     */
    public Inventory getInventory() {
        return event.getInventory();
    }

    /**
     * Get the Minestom InventoryPreClickEvent
     *
     * @return minestoms InventoryPreClickEvent
     */
    public InventoryPreClickEvent getEvent() {
        return event;
    }
}
