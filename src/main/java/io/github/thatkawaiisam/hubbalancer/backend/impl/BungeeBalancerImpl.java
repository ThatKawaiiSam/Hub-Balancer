package io.github.thatkawaiisam.hubbalancer.backend.impl;

import io.github.thatkawaiisam.hubbalancer.HubBalancerPlugin;
import io.github.thatkawaiisam.hubbalancer.backend.IBalancerImpl;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class BungeeBalancerImpl implements IBalancerImpl {

    private List<String> hubServers = new ArrayList<>();

    public BungeeBalancerImpl() {
        hubServers.clear();
        hubServers.addAll(
                HubBalancerPlugin
                        .getBalancerConfig()
                        .getConfiguration()
                        .getStringList(
                                "Hub-Servers"
                        )
        );
    }

    @Override
    public ServerInfo getRandomServer() {
        for (String string : hubServers) {
            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(string);
            if (serverInfo == null) {
                continue;
            }
            return serverInfo;
        }
        return null;
    }
}
