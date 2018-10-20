package io.github.thatkawaiisam.hubbalancer.backend.impl;

import io.github.thatkawaiisam.hubbalancer.backend.IBalancer;
import io.github.thatkawaiisam.redstone.bungee.RedstoneBungeeAPI;
import io.github.thatkawaiisam.redstone.shared.RedstoneSharedAPI;
import io.github.thatkawaiisam.redstone.shared.server.RedstoneServer;
import io.github.thatkawaiisam.redstone.shared.server.ServerState;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.Collections;
import java.util.List;

public class RedstoneBalancer implements IBalancer {

    @Override
    public ServerInfo getRandomServer() {
        List<RedstoneServer> redstoneServers = Collections.synchronizedList(
                RedstoneSharedAPI.getServersFromCriteria(
                        RedstoneBungeeAPI.getCurrentServerRegion(),
                        "hubs"
                )
        );
        if (redstoneServers.size() < 1) {
            return null;
        }
        for (RedstoneServer server : redstoneServers) {
            if (server.getData().getState() == ServerState.OFFLINE) {
                redstoneServers.remove(server);
            }
        }
        //TODO check if the player is whitelisted
        Collections.shuffle(redstoneServers);
        return ProxyServer.getInstance().getServerInfo(redstoneServers.get(0).getFullName());
    }
}
