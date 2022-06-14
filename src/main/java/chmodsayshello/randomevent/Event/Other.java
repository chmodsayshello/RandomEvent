package chmodsayshello.randomevent.Event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Other implements Listener {
    @EventHandler
    public void onjoin(PlayerJoinEvent e){
        if(!Event.any_event_running){
            e.getPlayer().sendMessage("[RandomEvent]"+ChatColor.GRAY+"There are no events running currently");
            return;
        } else if(Event.word_race_running){
            e.getPlayer().sendMessage("[RandomEvent]"+ ChatColor.GRAY+"There is a word race running currently, the word is: "+ChatColor.AQUA+Event.word_race_word);
        } else if(Event.eat_race_running){
            e.getPlayer().sendMessage("[RandomEvent]" + ChatColor.GRAY+"There is an eat race running currently, eat this food: "+ChatColor.AQUA+Event.eat_race_food.toString());
        } else if(Event.obtain_race_running){
            e.getPlayer().sendMessage("[RandomEvent]" + ChatColor.GRAY+"There is an obtain race running currently, pick up this item: "+ChatColor.AQUA+Event.obtain_race_material.toString());
        } else if(Event.kill_race_running){
            e.getPlayer().sendMessage("[RandomEvent]" + ChatColor.GRAY +"There is a kill race running currently, kill this mob: " + ChatColor.AQUA+Event.kill_race_entity.name());
        } else if(Event.potion_effect_race_running){
            e.getPlayer().sendMessage("[RandomEvent]" + ChatColor.GRAY+"There a potion effect race running currently, get this effect " + ChatColor.DARK_RED+"USING A POTION"+ChatColor.GRAY+":"+ChatColor.AQUA+Event.potion_effect_race_effect.getName());
        }
    }
}
