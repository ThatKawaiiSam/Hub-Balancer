package io.github.thatkawaiisam.hubbalancer.backend;

import net.md_5.bungee.api.config.ServerInfo;

public interface IBalancer {

    ServerInfo getRandomServer();

}
