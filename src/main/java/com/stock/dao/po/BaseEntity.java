package com.stock.dao.po;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author yanweijin
 * @date 2017/5/30
 */
@MappedSuperclass
public class BaseEntity extends AbstractBaseEntity {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
