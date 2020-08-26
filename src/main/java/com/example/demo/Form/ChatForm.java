package com.example.demo.Form;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Entity;

import javax.validation.constraints.NotNull;

//@Entity
@Setter
@Getter
public class ChatForm {
    @NotNull
    private String fromname;

    @NotNull
    private String message;
}
