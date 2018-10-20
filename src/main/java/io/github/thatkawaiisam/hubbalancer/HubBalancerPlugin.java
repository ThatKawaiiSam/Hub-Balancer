package io.github.thatkawaiisam.hubbalancer;

import io.github.thatkawaiisam.configs.BungeeConfigHelper;
import io.github.thatkawaiisam.hubbalancer.backend.IBalancerImpl;
import io.github.thatkawaiisam.hubbalancer.backend.impl.BungeeBalancerImpl;
import io.github.thatkawaiisam.hubbalancer.backend.impl.RedstoneBalancerImpl;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public class HubBalancerPlugin extends Plugin {

    @Getter private static IBalancerImpl balancerImplementation;
    @Getter private static BungeeConfigHelper balancerConfig;

    @Override
    public void onEnable() {
        balancerConfig = new BungeeConfigHelper(this, "config", getDataFolder().getAbsolutePath());
        registerBalancerImplementation();
        getProxy().getPluginManager().registerCommand(this, new HubBalancerCommand());
        getProxy().getPluginManager().registerListener(this, new HubBalancerListeners());
    }

    @Override
    public void onDisable() {
        getProxy().getPluginManager().unregisterListeners(this);
        getProxy().getPluginManager().unregisterCommands(this);
    }

    private void registerBalancerImplementation() {
        Configuration configuration = balancerConfig.getConfiguration();
        //Redstone
        if (configuration.getString("Balancer-Implementation").equalsIgnoreCase("Redstone")) {
            balancerImplementation = new RedstoneBalancerImpl();
        }else{
            balancerImplementation = new BungeeBalancerImpl();
        }
    }
}
