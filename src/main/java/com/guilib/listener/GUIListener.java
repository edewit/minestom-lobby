package com.guilib.listener;

import com.guilib.buttons.Button;
import com.guilib.events.ButtonClickEvent;
import com.guilib.gui.GUI;
import com.guilib.gui.GUIManager;

import net.minestom.server.event.inventory.InventoryPreClickEvent;

public class GUIListener {

    /**
     * Get the Event callback that needs to be registered
     * in the main class
     *
     * @return the event callback
     */
    public static void preClickEvent(InventoryPreClickEvent e) {
    	
    	GUI gui = GUIManager.getGui(e.getInventory());
    	
    	if (gui == null)
    		return;

    	int slot = e.getSlot();
    	
        Button btn = gui.getButton(slot);
        
        if (btn == null)
        	return;
        
        e.setCancelled(gui.isReadOnly());
        
        if (btn.getEvent() != null) {
            btn.getEvent().clickEvent(new ButtonClickEvent(e));
        }
    }
}
