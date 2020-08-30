package com.example.demo.entity;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Yuta Takayama
 */
@Entity
@Data
@Getter
@Setter
@Table(name = "emolog")
public class Emolog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    Integer id;

    @NotNull

    @Size(max=30)
    private Integer userid;

    @NotNull
    @Size(max=50)
    private Integer friendid;

    @NotNull
    @Max(40)
    private String contents;

    private LocalDateTime created_at;
}