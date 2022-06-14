package chmodsayshello.randomevent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import chmodsayshello.randomevent.Event.*;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RandomEvent extends JavaPlugin {
    Random random = new Random();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Event(), this);
        getServer().getPluginManager().registerEvents(new Other(), this);
        getServer().getScheduler().scheduleSyncDelayedTask(this, (Runnable) new BukkitRunnable() {
            @Override
            public void run() {
                if(!Event.any_event_running){
                    int event_type = random.nextInt(6-1)+1;
                    Event.any_event_running = true;
                    if (event_type == 1) {//word race
                        List words = new ArrayList<String>(
                                Arrays.asList("Pneumonoultramicroscopicsilicovolcanoconiosis", "Hippopotomonstrosesquippedaliophobia", "Supercalifragilisticexpialidocious", "Pseudopseudohypoparathyroidism", "Floccinaucinihilipilification", "Antidisestablishmentarianism", "Spectrophotofluorometrically", "Hepaticocholangiogastrostomy", "Honorificabilitudinitatibus", "Psychoneuroendocrinological","Radioimmunoelectrophoresis","Pneumoencephalographically","Psychophysicotherapeutics","Immunoelectrophoretically","short","Dichlorodifluoromethane","Minecraft","Incomprehensibilities","Otorhinolaryngological","Xenotransplantation","ThisIsn'tEvenAWordLolAndIt'sExtremlyHardToTypeDon'tAskMeWhyIAddedThisxD","i"));
                        int list_index = random.nextInt((words.size()-1)-1) + 1;
                        Event.word_race_running = true;
                        Event.word_race_word = words.get(list_index).toString();
                        for (Player p: Bukkit.getOnlinePlayers()){
                            p.sendMessage("[RandomEvents]"+ ChatColor.GRAY +"Say this word in chat! " + ChatColor.AQUA +Event.word_race_word);
                        }
                    }else if(event_type == 2){//eat/food race
                        List Food = new ArrayList<Material>(
                                Arrays.asList(Material.APPLE,Material.BAKED_POTATO,Material.BEETROOT,Material.BEETROOT_SOUP,Material.BREAD,Material.CARROT,Material.CHORUS_FRUIT,Material.COOKED_CHICKEN,Material.COOKED_COD,Material.COOKED_MUTTON,Material.COOKED_RABBIT,Material.COOKED_SALMON,Material.COOKIE,Material.DRIED_KELP,Material.ENCHANTED_GOLDEN_APPLE,Material.GOLDEN_APPLE,Material.GLOW_BERRIES,Material.GOLDEN_CARROT,Material.HONEY_BOTTLE,Material.MELON_SLICE,Material.MUSHROOM_STEW,Material.POISONOUS_POTATO,Material.POTATO,Material.PUFFERFISH,Material.PUMPKIN_PIE,Material.RABBIT_STEW,Material.BEEF,Material.CHICKEN,Material.COD,Material.MUTTON,Material.PORKCHOP,Material.RABBIT,Material.SALMON,Material.ROTTEN_FLESH,Material.SPIDER_EYE,Material.COOKED_BEEF,Material.SUSPICIOUS_STEW,Material.SWEET_BERRIES,Material.TROPICAL_FISH));
                        int list_index = random.nextInt((Food.size()-1)-1) + 1;
                        Event.eat_race_running=true;
                        Event.eat_race_food =(Material) Food.get(list_index);
                        for (Player p: Bukkit.getOnlinePlayers()){
                            p.sendMessage("[RandomEvents]"+ChatColor.GRAY+" Be the fastest player to eat this food item: "+ChatColor.AQUA+Event.eat_race_food.toString()+ChatColor.GRAY+"!");
                        }
                    }else if(event_type ==3){//obtain race
                        Material[] materials = Material.values();
                        Material randomMaterial = materials[new Random(System.currentTimeMillis()).nextInt(materials.length)];
                        List unobtainable= new ArrayList<Material>(
                                Arrays.asList(Material.TALL_SEAGRASS,Material.AIR, Material.CAVE_AIR,Material.VOID_AIR,Material.BEDROCK, Material.CREEPER_WALL_HEAD, Material.DRAGON_WALL_HEAD, Material.PISTON_HEAD,Material.PLAYER_WALL_HEAD,Material.ZOMBIE_WALL_HEAD,Material.PLAYER_HEAD,Material.MUSHROOM_STEM,Material.BROWN_MUSHROOM_BLOCK,Material.RED_MUSHROOM_BLOCK,Material.BARRIER,Material.SPAWNER,Material.REDSTONE_WALL_TORCH,Material.REDSTONE_WIRE,Material.END_PORTAL,Material.NETHER_PORTAL,Material.END_PORTAL_FRAME,Material.DRAGON_EGG,Material.TRIPWIRE,Material.COMMAND_BLOCK,Material.COMMAND_BLOCK_MINECART,Material.CHAIN_COMMAND_BLOCK,Material.COMMAND_BLOCK_MINECART,Material.STRUCTURE_BLOCK,Material.STRUCTURE_VOID,Material.CARROTS));
                        if(randomMaterial.name().matches("potted") || randomMaterial.name().matches("legacy")){
                            Event.any_event_running = false;
                            return;
                        }
                        for (int i =0; i <= unobtainable.size()-1;i++){
                            if(randomMaterial.equals((Material) unobtainable.get(i))){
                                Event.any_event_running = false;
                                return;
                            }
                        }
                        Event.obtain_race_running = true;
                        Event.obtain_race_material = randomMaterial;

                        for (Player p : Bukkit.getOnlinePlayers()){
                            p.sendMessage("[RandomEvent]"+ChatColor.GRAY+" Be the fastest player to pick this Item up: "+ChatColor.AQUA+Event.obtain_race_material.toString());
                        }
                    }else if(event_type == 4){ // kill race
                        int list_index = random.nextInt((EntityType.values().length-1)-1) + 1;
                        EntityType entityType= EntityType.values()[list_index];//in the if statement below, afterwards I think an ArrayList would have been better...
                        List invalid_entities = new ArrayList<EntityType>(
                            Arrays.asList(EntityType.AREA_EFFECT_CLOUD,EntityType.ARMOR_STAND,EntityType.ARROW,EntityType.CHEST_BOAT,EntityType.MINECART_CHEST,EntityType.MINECART_COMMAND,EntityType.EGG,EntityType.ENDER_CRYSTAL,EntityType.ENDER_PEARL,EntityType.EVOKER_FANGS,EntityType.THROWN_EXP_BOTTLE,EntityType.EXPERIENCE_ORB,EntityType.ENDER_SIGNAL,EntityType.FALLING_BLOCK,EntityType.FIREBALL,EntityType.FIREWORK,EntityType.MINECART_FURNACE,EntityType.GLOW_ITEM_FRAME,EntityType.MINECART_HOPPER,EntityType.DROPPED_ITEM,EntityType.ITEM_FRAME,EntityType.LEASH_HITCH,EntityType.LIGHTNING,EntityType.MARKER,EntityType.MINECART,EntityType.PAINTING,EntityType.SPLASH_POTION,EntityType.SMALL_FIREBALL,EntityType.SNOWBALL,EntityType.MINECART_MOB_SPAWNER,EntityType.SPECTRAL_ARROW,EntityType.MINECART_TNT,EntityType.PRIMED_TNT,EntityType.TRIDENT,EntityType.WITHER_SKULL));
                        for (int i=0;i<invalid_entities.size();i++){
                            if(entityType.equals(invalid_entities.get(i))){
                                Event.any_event_running = false;
                                return;
                            }
                        }
                        Event.kill_race_entity = entityType;
                        Event.kill_race_running = true;
                        for (Player p : Bukkit.getOnlinePlayers()){
                            p.sendMessage("[RandomEvent]"+ChatColor.GRAY+" Be the fastest player to kill this entity: "+ChatColor.AQUA+Event.kill_race_entity.name());
                        }
                    }else if(event_type == 5){//potion effect race
                        List potioneffects = new ArrayList<PotionEffectType>(
                                Arrays.asList(PotionEffectType.FIRE_RESISTANCE,PotionEffectType.HEAL,PotionEffectType.SLOW,PotionEffectType.NIGHT_VISION,PotionEffectType.REGENERATION,PotionEffectType.SLOW_FALLING,PotionEffectType.HARM,PotionEffectType.SPEED,PotionEffectType.WEAKNESS,PotionEffectType.JUMP,PotionEffectType.INCREASE_DAMAGE,PotionEffectType.INVISIBILITY,PotionEffectType.WATER_BREATHING,PotionEffectType.POISON));
                        int list_index = random.nextInt((potioneffects.size()-1)-1) + 1;
                        Event.potion_effect_race_running = true;
                        Event.potion_effect_race_effect=(PotionEffectType) potioneffects.get(list_index);
                        for (Player p : Bukkit.getOnlinePlayers()){
                            p.sendMessage("[RandomEvent]"+ChatColor.GRAY+" Be the first one to get this effect "+ChatColor.DARK_RED+"USING A POTION "+ChatColor.GRAY+":"+ChatColor.AQUA+Event.potion_effect_race_effect.getName());
                        }
                    }
                }
            }

        }.runTaskTimer(this, 27600,27600)); //26*60*20=27600
        getLogger().info("Random Events started");
        // Plugin startup logic
    }


    @Override
    public void onDisable() {
        getLogger().info("Random Events shut down");
        // Plugin shutdown logic
    }
}
