package unknownrek.hytalemodding.events;

import com.hypixel.hytale.server.core.asset.type.gameplay.DeathConfig;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.events.AddWorldEvent;
import com.hypixel.hytale.server.core.universe.world.events.StartWorldEvent;
import unknownrek.hytalemodding.GlobalDeathSettingsPlugin;
import unknownrek.hytalemodding.config.PluginConfig;

import static unknownrek.hytalemodding.GlobalDeathSettingsPlugin.LOGGER;

public class OnWorldEvents {

    private final PluginConfig config;

    public OnWorldEvents(GlobalDeathSettingsPlugin plugin) {
        this.config = plugin.getConfig();
    }

    public void onAddWorldHandler(AddWorldEvent event) {
        LOGGER.atInfo().log("AddWorldEvent detected, applying death settings to world: %s", event.getWorld().getName());
        applySettings(event.getWorld());
    }

    public void onStartWorldEvent(StartWorldEvent event) {
        LOGGER.atInfo().log("StartWorldEvent detected, applying death settings to world: %s", event.getWorld().getName());
        applySettings(event.getWorld());
    }


    private void applySettings(World world) {

        var dConfig = world.getDeathConfig();
        var dConfigClass = DeathConfig.class;
        try {
            var itemLossModeField = dConfigClass.getDeclaredField("itemsLossMode");
            itemLossModeField.setAccessible(true);
            itemLossModeField.set(dConfig, config.getItemsLossMode());
            var itemsAmountLossPercentageField = dConfigClass.getDeclaredField("itemsAmountLossPercentage");
            itemsAmountLossPercentageField.setAccessible(true);
            itemsAmountLossPercentageField.set(dConfig, config.getItemsAmountLossPercentage());
            var itemsDurabilityLossPercentageField = dConfigClass.getDeclaredField("itemsDurabilityLossPercentage");
            itemsDurabilityLossPercentageField.setAccessible(true);
            itemsDurabilityLossPercentageField.set(dConfig, config.getItemsDurabilityLossPercentage());
        } catch (Exception e) {
            LOGGER.atSevere().log("Failed to use reflection to set deathConfig fields: %s", e.getStackTrace());
        }
    }
}
