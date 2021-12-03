package com.github.offby0point5.minestom.command;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.guilib.buttons.ActionButton;
import com.guilib.buttons.CloseButton;
import com.guilib.events.ButtonClickEvent;
import com.guilib.gui.GUI;
import com.guilib.gui.GUIItem;
import com.guilib.gui.GUIManager;
import com.guilib.gui.GuiLib;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public final class CreateCommand extends Command implements CommandExecutor {
  private static final String GUI_NAME = "create";
  private static final Logger LOGGER = LoggerFactory.getLogger(CreateCommand.class);
  private HttpClient client = HttpClient.newHttpClient();

  public CreateCommand() {
    super("create");
    setCondition(this::condition);
    setDefaultExecutor(this);
    GuiLib.init();

    GUI gui = new GUI(InventoryType.CHEST_1_ROW, Component.text("Select a server type"), GUI_NAME);
    ItemStack spongeServer = GUIItem.itemWithName(Material.SPONGE, Component.text("Sponge server"));
    gui.addButton(8, new CloseButton());
    gui.addButton(3, new ActionButton(spongeServer, (ButtonClickEvent e) -> {
      LOGGER.info("sending request to create minecraft server instance...");
      Player p = e.getPlayer();
      try {
        sendRequest();
        p.showTitle(Title.title(Component.text("Creating server"), Component.text("wait for it...")));
      } catch (IOException | InterruptedException error) {
        p.sendMessage(
            Component.text("Could not create a server due to: " + error.getMessage(), NamedTextColor.RED));
      } finally {
        p.closeInventory();
      }
    }));
    GUIManager.registerGUI(gui);

  }

  private boolean condition(final CommandSender sender, final String command) {
    // TODO some sort of permissions here perhaps
    return sender.isPlayer();
  }

  @Override
  public void apply(@NotNull CommandSender sender, @NotNull CommandContext context) {
    Player player = sender.asPlayer();
    player.openInventory(GUIManager.getGui(GUI_NAME).getInventory());
  }

  private void sendRequest() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        // TODO configure this endpoint
        .uri(URI.create("http://localhost:8080/api/cluster"))
        .header("Content-Type", "application/json")
        .POST(BodyPublishers.ofString("{}"))
        .build();
    client.send(request, BodyHandlers.ofString());
  }
}
