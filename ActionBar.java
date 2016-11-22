package eu.rukes.testplugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.Player;

public class ActionBar {
    private static String version;
    public ActionBar(String v){
        version = v;
    }
    
    public static void send(Player player, String message){
        try{
            Constructor<?> constructor = getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"), byte.class);
            Object icbc = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\""+message.replace("\"", "")+"\"}");
            Object packet = constructor.newInstance(icbc, (byte) 2);
            Object entityPlayer= player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | InstantiationException e){
            Main.log("Error while trying to execute ActionBar packet!");
        }
    }
    private static Class<?> getNMSClass(String name) {
        try{
            return Class.forName("net.minecraft.server."+version+"."+name);
        }catch(ClassNotFoundException e){
            Main.log("Error while trying to execute ActionBar packet ("+name+")!");
        return null;
        }
    }
}
