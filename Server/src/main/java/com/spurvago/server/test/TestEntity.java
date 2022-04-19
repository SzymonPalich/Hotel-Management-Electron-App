package com.spurvago.server.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
public class TestEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String stringField;

    @Getter
    @Setter
    private int intField;

    protected TestEntity() { }

    public TestEntity(String stringField, int intField) {
        this.stringField = stringField;
        this.intField = intField;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", stringField='" + stringField + '\'' +
                ", intField=" + intField +
                '}';
    }

    public void map(TestEntity source) {
        this.setIntField(source.getIntField());
        this.setStringField(source.getStringField());
    }
}
