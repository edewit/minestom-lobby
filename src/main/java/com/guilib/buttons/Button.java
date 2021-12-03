package com.guilib.buttons;

import com.guilib.events.ClickEvent;
import net.minestom.server.item.ItemStack;

public abstract class Button {

    public Button() {
    }

    public abstract ItemStack getItem();
    public abstract ClickEvent getEvent();
}
