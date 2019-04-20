package com.stock.dao.po;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public class AbstractBaseEntity {

    @CreationTimestamp
    @Column(name = "create_timestamp")
    private Timestamp createTimestamp = new Timestamp(System.currentTimeMillis());

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
