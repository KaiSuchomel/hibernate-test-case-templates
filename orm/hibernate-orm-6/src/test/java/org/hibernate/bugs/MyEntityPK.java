package org.hibernate.bugs;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class MyEntityPK implements Serializable {

    private String name;
    private UUID plantId;

    public MyEntityPK() {
        //default-constructor for JPA
    }

    //<editor-fold defaultstate="collapsed" desc="getter & setter">
    public String getName() {
        return name;
    }
    
    public MyEntityPK setName(String aName) {
        this.name = aName;
        return this;
    }
    
    public UUID getPlantId() {
        return plantId;
    }
    
    public MyEntityPK setPlantId(UUID aPlantId) {
        this.plantId = aPlantId;
        return this;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode, equals & toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.plantId);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MyEntityPK other = (MyEntityPK) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.plantId, other.plantId);
    }
    
    @Override
    public String toString() {
        return "OrganizationUnitTypePK{" + "name=" + name + ", plantId=" + plantId + '}';
    }
    //</editor-fold>
}
