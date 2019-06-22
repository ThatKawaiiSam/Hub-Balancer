package io.github.thatkawaiisam.hubbalancer.backend.impl;

import io.github.thatkawaiisam.hubbalancer.HubBalancerPlugin;
import io.github.thatkawaiisam.hubbalancer.backend.IBalancer;
import io.github.thatkawaiisam.redstone.shared.RedstoneSharedAPI;
import io.github.thatkawaiisam.redstone.shared.server.RedstoneServer;
import io.github.thatkawaiisam.redstone.shared.server.ServerState;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedstoneBalancerImpl implements IBalancer {

    private List<String> groups = new ArrayList<>();

    public RedstoneBalancerImpl() {
        this.groups.addAll(HubBalancerPlugin.getBalancerConfig().getConfiguration().getStringList("Redstone-Groups"));
    }

    @Override
    public ServerInfo getRandomServer() {
        List<RedstoneServer> redstoneServers = new ArrayList<>(RedstoneSharedAPI.getServersFromGroups(groups.toArray(new String[0])));
        if (redstoneServers.size() < 1) {
            return null;
        }
        redstoneServers.removeIf(server -> server.getData().getState() == ServerState.OFFLINE);
        Collections.shuffle(redstoneServers);
        if (redstoneServers.size() < 1) {
            return null;
        }
        return ProxyServer.getInstance().getServerInfo(redstoneServers.get(0).getServerID());
    }
}
