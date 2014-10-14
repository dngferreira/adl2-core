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

package org.openehr.adl.rm;

/**
 * RM model representing OpenEHR objects
 * @author markopi
 */
public class OpenEhrRmModel implements RmModel {
    private final RmTypeGraph rmTypeGraph;

    public OpenEhrRmModel() {
        rmTypeGraph = new RmTypeGraphBuilder().build();
    }

    public Class<?> getRmClass(String rmType) {
        return getRmType(rmType).getMainRmClass();
    }

    @Override
    public String getRmTypeName(Class<?> rmClass) {
        return getRmType(rmClass).getRmType();
    }

    public RmTypeNode getRmType(Class<?> rmClass) {
        RmTypeNode result = rmTypeGraph.tryGetNodeFromRmClass(rmClass);
        if (result == null) {
            throw new RmModelException("Unknown RM class: " + rmClass.getName());
        }
        return result;
    }

    @Override
    public boolean rmTypeExists(String rmType) {
        return rmTypeGraph.tryGetNodeFromRmType(rmType)!=null;
    }

    public RmTypeNode getRmType(String rmType) {
        RmTypeNode node = rmTypeGraph.tryGetNodeFromRmType(rmType);
        if (node == null) {
            throw new RmModelException("Unknown RM type: " + rmType);
        }
        return node;
    }

    private RmTypeAttribute getRmAttribute(RmTypeNode rmType, String attribute) {
        RmTypeAttribute result = rmType.getAttributes().get(attribute);
        if (result == null) {
            throw new RmModelException("No attribute %s on rm type %s", attribute, rmType.getRmType());
        }
        return result;
    }

    @Override
    public RmTypeAttribute getRmAttribute(String rmType, String attribute) {
        return getRmAttribute(getRmType(rmType), attribute);
    }


}
