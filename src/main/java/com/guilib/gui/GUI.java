package com.guilib.gui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.guilib.buttons.Button;
import com.guilib.buttons.StaticButton;

import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;

public class GUI {
    private Inventory inv;
    private Map<Integer, Button> buttons = new HashMap<>();
    private String id;
    private boolean readOnly = false;

    /**
     *
     * @param type  the inventory type
     * @param title the inventory title
     */
    public GUI(InventoryType type, Component title) {
        new GUI(type, title, String.valueOf(title.hashCode()));
    }

    /**
     * Creates a basic GUI.
     *
     * @param type  the inventory type
     * @param title the inventory title
     * @param id    the gui ID
     */
    public GUI(InventoryType type, Component title, String id) {
        inv = new Inventory(type, title);
        this.id = id;
    }

    /**
     * Creates a basic GUI.
     *
     * @param type      the inventory type
     * @param title     the inventory title
     * @param emptyItem the item being shown in blank slots
     */
    public GUI(InventoryType type, Component title, ItemStack emptyItem) {
        new GUI(type, title, emptyItem, String.valueOf(title.hashCode()));
    }

    /**
     * Creates a basic GUI.
     *
     * @param type      the inventory type
     * @param title     the inventory title
     * @param emptyItem the item being shown in blank slots
     * @param id        the gui ID
     */
    public GUI(InventoryType type, Component title, ItemStack emptyItem, String id) {
        inv = new Inventory(type, title);
        this.id = id;

        for (int i = 0; i < inv.getSize(); i++) {
            buttons.put(i, new StaticButton(emptyItem));
            inv.setItemStack(i, emptyItem);
        }
    }

    /**
     * Add a Button to a GUI
     *
     * @param slot   slot for the button
     * @param button the actual button
     */
    public void addButton(int slot, Button button) {
        buttons.put(slot, button);
        inv.setItemStack(slot, button.getItem());
    }
    
    /**
     * Add a Button to a GUI on multiple slots
     *
     * @param slots  the slots for the button
     * @param button the actual button
     */
    public void addButton(Collection<Integer> slots, Button button) {
        for (int slot : slots) {
        	addButton(slot, button);
        }
    }

    /**
     * Get A Button from a certain slot,
     * null if no button exists.
     *
     * @param slot slot for the button
     * @return     the button at the slot
     */
    public Button getButton(int slot) {
        return buttons.get(slot);
    }

    /**
     * Sets whether this inventory is read-only.
     * 
     * @param readOnly
     */
    public void setReadOnly(boolean readOnly) {
    	this.readOnly = readOnly;
    }
    
    /**
     * Get the ID of the GUI
     *
     * @return id of the gui
     */
    public String getId() {
        return id;
    }

    /**
     * Get the Title of the GUI
     *
     * @return title of the gui
     */
    public Component getTitle() {
        return inv.getTitle();
    }

    /**
     * Get the entire Inventory of the GUI
     *
     * @return inventory of the gui
     */
    public Inventory getInventory() {
        return inv;
    }

    /**
     * Open the GUI for a player
     *
     * @param p player to open the gui
     */
    public void open(Player p) {
        p.openInventory(inv);
    }

    /**
     * Gets whether this inventory is read-only
     * 
     * @return readOnly
     */
	public boolean isReadOnly() {
		return readOnly;
	}
}
