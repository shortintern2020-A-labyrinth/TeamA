package com.example.demo.entity;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.springframework.format.datetime.standard.DateTimeContext;

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
    @Size(min=30)
    private Integer userid;

    @NotNull
    @Size(min=50)
    private Integer friendid;

    private LocalDateTime create_at;

    @NotNull
    @Max(40)
    private String contents;


}