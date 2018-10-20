package io.github.thatkawaiisam.hubbalancer.algoritium;

import net.md_5.bungee.api.config.ServerInfo;

public interface IBalancerImpl {

    ServerInfo getRandomServer();
}
