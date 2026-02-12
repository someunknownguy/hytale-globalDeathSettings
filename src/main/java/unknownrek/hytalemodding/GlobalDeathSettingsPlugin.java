package unknownrek.hytalemodding;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import unknownrek.hytalemodding.config.PluginConfig;
import unknownrek.hytalemodding.systems.ApplyDeathConfigSystem;

import javax.annotation.Nonnull;

public class GlobalDeathSettingsPlugin extends JavaPlugin {

    private final Config<PluginConfig> config = this.withConfig("PersistentWorldDeathSettings", PluginConfig.CODEC);

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
    }

    private void setupEcs() {
        var ecsEventReg = this.getEntityStoreRegistry();
        ecsEventReg.registerSystem(new ApplyDeathConfigSystem(this));
    }

    public PluginConfig getConfig() {
        return config.get();
    }
}