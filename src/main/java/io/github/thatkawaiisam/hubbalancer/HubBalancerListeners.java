package io.github.thatkawaiisam.hubbalancer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class HubBalancerListeners implements Listener {

    @EventHandler
    public void onInitalConnect(ServerConnectEvent event) {
        final ProxiedPlayer player = event.getPlayer();
        final ServerInfo originalTarget = event.getTarget();

        //TODO make target server configurable
        if (!originalTarget.getName().equalsIgnoreCase("hub-connector")) {
            return;
        }

        final ServerInfo serverInfo = HubBalancerPlugin.getBalancerImplementation().getRandomServer();

        if (serverInfo == null) {
            //TODO maybe make this a message instead of a kick
            player.disconnect(
                    TextComponent.fromLegacyText(
                            ChatColor.translateAlternateColorCodes(
                                    '&',
                                    "&cUnable to connect you to a hub server!"
                            )
                    )
            );
            return;
        }


        //Send message on which server it is getting transferred to.
        player.sendMessage(
                TextComponent.fromLegacyText(
                        ChatColor.translateAlternateColorCodes(
                                '&',
                                "You are being connected to " + serverInfo.getName() + "!"
                        )
                )
        );

        //Set Target
        event.setTarget(serverInfo);
    }
}
