/*
 * ADL2-core
 * Copyright (c) 2013-2014 Marand d.o.o. (www.marand.com)
 *
 * This file is part of ADL2-core.
 *
 * ADL2-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openehr.adl.serializer.constraints;

import org.openehr.adl.am.mixin.AmMixin;
import org.openehr.adl.am.mixin.AmMixins;
import org.openehr.adl.am.mixin.IntervalMixin;
import org.openehr.adl.serializer.ArchetypeSerializer;
import org.openehr.jaxb.am.CDuration;
import org.openehr.jaxb.rm.IntervalOfDuration;

import java.util.Objects;

/**
 * @author Marko Pipan
 */
public class CDurationSerializer extends ConstraintSerializer<CDuration> {
    public CDurationSerializer(ArchetypeSerializer serializer) {
        super(serializer);
    }

    @Override
    public void serialize(CDuration cobj) {
        boolean constrained=false;

        if (cobj.getPattern() != null) {
            builder.append(cobj.getPattern());
            constrained=true;
        }
        if (cobj.getRange() != null) {
            if (constrained) {
                builder.append("/");
            }
            builder.append("|").append(AmMixins.of(cobj.getRange()).toString()).append("|");
            constrained=true;
        }
        if (cobj.getAssumedValue() != null) {
            builder.append("; ").append(cobj.getAssumedValue());
            constrained=true;
        }

        if (!constrained) {
            builder.append("*");
        }
    }
}
