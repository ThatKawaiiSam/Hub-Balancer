package io.github.thatkawaiisam.hubbalancer.backend.impl;

import io.github.thatkawaiisam.hubbalancer.HubBalancerPlugin;
import io.github.thatkawaiisam.hubbalancer.backend.IBalancer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class BungeeBalancerImpl implements IBalancer {

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
        //List<ServerInfo> servers = new ArrayList<>();
        for (String string : hubServers) {
            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(string);
            if (serverInfo == null) {
                continue;
            }
            //servers.add(serverInfo);
            return serverInfo;
        }
        return null;
    }
}
