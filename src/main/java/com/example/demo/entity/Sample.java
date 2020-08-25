package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
public class Sample {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    private Integer id;

    @NotNull
    @Size(min=30)
    private String name;

    @NotNull
    @Size(min=50)
    private String mail;

    private int filterlevel;
}