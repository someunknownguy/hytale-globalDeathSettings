package unknownrek.hytalemodding.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.validation.Validators;
import com.hypixel.hytale.server.core.asset.type.gameplay.DeathConfig;
import com.hypixel.hytale.server.core.asset.type.gameplay.respawn.RespawnController;

public class PluginConfig {

    public static final BuilderCodec<PluginConfig> CODEC = BuilderCodec.builder(PluginConfig.class, PluginConfig::new)
            .appendInherited(new KeyedCodec<>("ItemsLossMode", DeathConfig.LOSS_MODE_CODEC),
                    PluginConfig::setItemsLossMode,
                    PluginConfig::getItemsLossMode,
                    (o, p) -> o.setItemsLossMode(p.getItemsLossMode())).add()
            .appendInherited(new KeyedCodec<>("ItemsAmountLossPercentage", Codec.DOUBLE),
                    PluginConfig::setItemsAmountLossPercentage,
                    PluginConfig::getItemsAmountLossPercentage,
                    (o, p) -> o.setItemsAmountLossPercentage(p.getItemsAmountLossPercentage()))
            .addValidator(Validators.range((double)0.0F, (double)100.0F)).add()
            .appendInherited(new KeyedCodec<>("ItemsDurabilityLossPercentage", Codec.DOUBLE),
                    PluginConfig::setItemsDurabilityLossPercentage,
                    PluginConfig::getItemsDurabilityLossPercentage,
                    (o, p) -> o.setItemsDurabilityLossPercentage(p.getItemsDurabilityLossPercentage()))
            .addValidator(Validators.range((double)0.0F, (double)100.0F)).add()
            .build();

    private DeathConfig.ItemsLossMode itemsLossMode = DeathConfig.ItemsLossMode.CONFIGURED;
    private double itemsAmountLossPercentage = 20.0f;
    private double itemsDurabilityLossPercentage = 0.0f;

    public double getItemsAmountLossPercentage() {
        return itemsAmountLossPercentage;
    }

    public void setItemsAmountLossPercentage(double itemsAmountLossPercentage) {
        this.itemsAmountLossPercentage = itemsAmountLossPercentage;
    }

    public double getItemsDurabilityLossPercentage() {
        return itemsDurabilityLossPercentage;
    }

    public void setItemsDurabilityLossPercentage(double itemsDurabilityLossPercentage) {
        this.itemsDurabilityLossPercentage = itemsDurabilityLossPercentage;
    }

    public DeathConfig.ItemsLossMode getItemsLossMode() {
        return itemsLossMode;
    }

    public void setItemsLossMode(DeathConfig.ItemsLossMode itemsLossMode) {
        this.itemsLossMode = itemsLossMode;
    }
}
