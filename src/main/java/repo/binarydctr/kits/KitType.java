package repo.binarydctr.kits;

import org.bukkit.entity.EntityType;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public enum KitType {

    ARCHER(new Archer(), EntityType.SKELETON),
    KNIGHT(new Knight(), EntityType.ZOMBIE),
    WARRIOR(new Warrior(), EntityType.ENDERMAN);

    public Kit kit;
    public EntityType type;

    KitType(Kit kit, EntityType type) {
        this.kit = kit;
        this.type = type;
    }

    public Kit getKit() {
        return kit;
    }

    public EntityType getType() {
        return type;
    }
}
