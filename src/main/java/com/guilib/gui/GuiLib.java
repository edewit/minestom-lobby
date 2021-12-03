package com.guilib.gui;

import com.guilib.listener.GUIListener;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.inventory.InventoryPreClickEvent;

public class GuiLib {

    private static boolean initialized = false;

    public static void init() {
        if (initialized) {
            throw new IllegalStateException("GuiLib already initialized!");
        } else {
            initialized = true;

            MinecraftServer.getGlobalEventHandler().addEventCallback(InventoryPreClickEvent.class, GUIListener::preClickEvent);
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
