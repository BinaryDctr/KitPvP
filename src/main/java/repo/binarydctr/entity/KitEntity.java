package repo.binarydctr.entity;

import org.bukkit.entity.EntityType;
import repo.binarydctr.kits.Kit;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class KitEntity {

    public String name;
    public Kit kit;
    public EntityType type;

    public KitEntity(String name, Kit kit, EntityType type) {
        this.name = name;
        this.kit = kit;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Kit getKit() {
        return kit;
    }

    public EntityType getType() {
        return type;
    }
}
