package eu.rukes.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Test extends JavaPlugin implements Listener{
    
    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);
        
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        new ActionBarAPI(version);
    }
    @Override
    public void onDisable(){}
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        ActionBarAPI.send(e.getPlayer(), "§5Woooo, §b§nwelcome§4!");
    }
}
