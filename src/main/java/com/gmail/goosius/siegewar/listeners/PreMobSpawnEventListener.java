package com.gmail.goosius.siegewar.listeners;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import static com.gmail.goosius.siegewar.SiegeWarAPI.isLocationInActiveSiegeZone;

//pm.registerEvents(new PreMobSpawnEventListener(), this); -- listener register

public class PreMobSpawnEventListener implements Listener {

    /*List of mobs defined to be not allowed to spawn in an active siege zone.*/
    private static final EntityType[] prohibitedMobs = {
            EntityType.ZOMBIE,
            EntityType.SKELETON,
            EntityType.SPIDER,
            EntityType.CREEPER,
            EntityType.DROWNED,
            EntityType.WITCH,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.CAVE_SPIDER,
            EntityType.PHANTOM,
    };

    /*Checks if a mob type is on the list of prohibited mobs
    * Function is working
    * */
    private static boolean isProhibitedMob(EntityType mob) {
        for (int x = 0; x < prohibitedMobs.length; x++) {
            if (mob == prohibitedMobs[x]) {
                return true;
            }
        }
        return false;
    }


    @EventHandler
    public void onSiegeZonePreCreatureSpawnEvent(PreCreatureSpawnEvent event) {

        //If it's not one of the prohibited spawn mobs in siege zone, ignore.
        if(!isProhibitedMob(event.getType())){
            return;
        }
        //If the location of the mob is in an active Siege Zone, cancel the spawn event.
        if (isLocationInActiveSiegeZone(event.getSpawnLocation())) {
            event.setCancelled(true);
        }
    }
}