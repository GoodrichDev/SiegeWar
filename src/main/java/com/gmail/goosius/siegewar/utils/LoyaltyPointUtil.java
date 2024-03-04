package com.gmail.goosius.siegewar.utils;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.metadata.IntegerDataField;
import com.palmergames.bukkit.towny.utils.MetaDataUtil;

public class LoyaltyPointUtil {
    private final static String pointsMetadataKey = "nation_points";
    public final static IntegerDataField pointsSDF = new IntegerDataField(pointsMetadataKey, 0);

    public static void incrementPointsForNationResidents(Nation nation) {
        if(nation.isNeutral()) { // Skip if the nation is peaceful
            return;
        }

        for(Town town : nation.getTowns()) {
            for(Resident resident : town.getResidents()) {
                int currentPoints = MetaDataUtil.getInt(resident, pointsSDF);
                if(currentPoints < 7) {
                    MetaDataUtil.setInt(resident, pointsSDF, currentPoints + 1, true);
                }
            }
        }
    }

    public static void removePointsForResident(Resident resident) {
        MetaDataUtil.setInt(resident, pointsSDF, 0, true);
    }


}
