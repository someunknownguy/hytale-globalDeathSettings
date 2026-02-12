package unknownrek.hytalemodding;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.events.AddWorldEvent;
import com.hypixel.hytale.server.core.universe.world.events.StartWorldEvent;
import com.hypixel.hytale.server.core.util.Config;
import unknownrek.hytalemodding.config.PluginConfig;
import unknownrek.hytalemodding.events.OnWorldEvents;

import javax.annotation.Nonnull;

public class GlobalDeathSettingsPlugin extends JavaPlugin {

    private final Config<PluginConfig> config = this.withConfig("GlobalDeathSettings", PluginConfig.CODEC);

    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public GlobalDeathSettingsPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Loading GlobalDeathSettingsPlugin");
        config.save();
        setupCommands();
        setupEvents();
        setupEcs();

        LOGGER.atInfo().log("Finished loading GlobalDeathSettingsPlugin");
    }

    private void setupCommands() {
        var commandReg = this.getCommandRegistry();
    }

    private void setupEvents() {
        var eventReg = this.getEventRegistry();

        OnWorldEvents handlers = new OnWorldEvents(this);

        eventReg.registerGlobal(AddWorldEvent.class, handlers::onAddWorldHandler);
        eventReg.registerGlobal(StartWorldEvent.class, handlers::onStartWorldEvent);
    }

    private void setupEcs() {
        var ecsEventReg = this.getEntityStoreRegistry();
    }

    public PluginConfig getConfig() {
        return config.get();
    }
}