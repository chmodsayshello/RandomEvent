package chmodsayshello.randomevent.Event;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.potion.PotionEffectType;


public class Event implements Listener {
    public static boolean any_event_running = false;
    public static boolean word_race_running = false;
    public static boolean eat_race_running = false;
    public static boolean obtain_race_running = false;
    public static boolean kill_race_running = false;
    public static boolean potion_effect_race_running = false;
    public static String word_race_word;
    public static Material eat_race_food;
    public static Material obtain_race_material;
    public static EntityType kill_race_entity;
    public static PotionEffectType potion_effect_race_effect;


    @EventHandler
    public void onPlayerMsg(PlayerChatEvent e){
        if(!word_race_running){
            return;
        }
        if(e.getMessage().equals(word_race_word)){
            word_race_running = false;
            any_event_running = false;
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(ChatColor.AQUA+e.getPlayer().getDisplayName()+ChatColor.WHITE+" was the first one to say the word in chat! ('"+word_race_word+"')");
                p.playSound(p.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 15,1);
            }
        }
    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent e){
        if(!eat_race_running){
            return;
        }
        if(e.getItem().getType().equals(eat_race_food)){
            eat_race_running = false;
            any_event_running = false;
            e.getPlayer().getInventory().addItem(e.getItem());
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(ChatColor.AQUA+e.getPlayer().getDisplayName()+ChatColor.WHITE+" was the first one to eat "+ChatColor.GOLD+eat_race_food.toString()+ChatColor.WHITE+"! As a reward, he got the item back...");
                p.playSound(p.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 15,1);
            }
        }
    }

    @EventHandler
    public void onPlayerPickUp(PlayerPickupItemEvent e){
        if(!obtain_race_running){
            return;
        }
        if(e.getItem().getItemStack().getType().equals(obtain_race_material)){
            obtain_race_running = false;
            any_event_running = false;
            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(ChatColor.AQUA+e.getPlayer().getDisplayName()+ChatColor.WHITE+" was the first one to pick "+ChatColor.GOLD+obtain_race_material.toString()+ChatColor.WHITE+" up!");
                p.playSound(p.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 15,1);
            }
        }
    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent e){
        if(!kill_race_running){
            return;
        }
        if(e.getEntity().getKiller() instanceof Player){
            if(e.getEntity().getType().equals(kill_race_entity)){
                kill_race_running = false;
                any_event_running = false;
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.sendMessage(ChatColor.AQUA+e.getEntity().getKiller().getDisplayName()+ChatColor.WHITE+" was the first one to kill "+ChatColor.GOLD+e.getEntity().getName()+ChatColor.WHITE+"!");
                    p.playSound(p.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 15,1);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerPotionDrink(EntityPotionEffectEvent e){
        if(!potion_effect_race_running){
            return;
        }
        if(e.getCause().equals(EntityPotionEffectEvent.Cause.POTION_DRINK)){
            if(e.getNewEffect().getType().equals(potion_effect_race_effect) && e.getEntity() instanceof Player){
                potion_effect_race_running = false;
                any_event_running = false;
                for (Player p: Bukkit.getOnlinePlayers()){
                    p.sendMessage(ChatColor.AQUA+((Player) e.getEntity()).getDisplayName()+ChatColor.WHITE+" was the first one to get this effect using a potion: "+ChatColor.GOLD+potion_effect_race_effect.getName()+ChatColor.WHITE+"!");
                    p.playSound(p.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 15,1);
                }
            }
        }
    }
}
