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
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max=30)
    private Integer userid;

    @NotNull
    @Size(max=30)
    private Integer friendid;

    @NotNull
    @Size(max=30)
    private String name;

    @Max(30)
    private String latestemolog;

    private LocalDateTime updated_at;

    private Long lasttweetid;

}