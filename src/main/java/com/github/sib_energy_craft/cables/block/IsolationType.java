package com.github.sib_energy_craft.cables.block;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @since 0.0.1
 * @author sibmaks
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum IsolationType {
    NOT_ISOLATED(0.0625f, true),
    ISOLATED(0.125f, false);

    public final float radius;
    public final boolean damage;
}
