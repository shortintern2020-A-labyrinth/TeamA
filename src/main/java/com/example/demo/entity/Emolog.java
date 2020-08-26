package com.example.demo.entity;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Data
@Getter
@Setter
public class Emolog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull

    @Size(max=30)
    private Integer userid;

    @NotNull
    @Size(max=50)
    private Integer friendid;

    @NotNull
    @Max(40)
    private String contents;

    private LocalDateTime create_at;
}