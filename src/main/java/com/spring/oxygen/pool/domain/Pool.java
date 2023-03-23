package com.spring.oxygen.pool.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import io.hypersistence.utils.hibernate.type.array.IntArrayType;

@Entity
@Table(name="pool")
@TypeDef(name = "int-array", typeClass = IntArrayType.class)
public class Pool {
    @Id
    @Column(name="id")
    private int id;

    @Type(type = "int-array")
    @Column(name = "poolValues",
    columnDefinition = "integer[]"    
    )
    private int[] values;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int[] getValues() {
		return values;
	}
	public void setValues(int[] values) {
		this.values = values;
	}
	
// create table pool (id int4 not null, pool_values INTEGER ARRAY , primary key (id));
}
