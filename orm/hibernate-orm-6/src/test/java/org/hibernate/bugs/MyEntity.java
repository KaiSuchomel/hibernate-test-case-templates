package org.hibernate.bugs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PrePersist;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@IdClass(MyEntityPK.class)
public class MyEntity {

    @Id
    @Column(name = "NAME", updatable = false, nullable = false)
    private String name;

    @Id
    @Column(name = "PLANT_ID", updatable = false, nullable = false)
    private UUID plantId;

    @Column(name = "DESCRIPTION")
    @JdbcTypeCode(SqlTypes.JSON)
    private Translation description;
    
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(name = "HAS_PICTURES")
    private Boolean hasPictures = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public MyEntity setName(String name) {
        this.name = name;
        return this;
    }

    public UUID getPlantId() {
        return plantId;
    }

    public MyEntity setPlantId(UUID plantId) {
        this.plantId = plantId;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public MyEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public Boolean getHasPictures() {
        return hasPictures;
    }

    public MyEntity setHasPictures(Boolean hasPictures) {
        this.hasPictures = hasPictures;
        return this;
    }

    public Translation getDescription() {
        return description;
    }

    public MyEntity setDescription(Translation description) {
        this.description = description;
        return this;
    }
    
    @PrePersist
    protected void onCreateOrganizationUnitType() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
