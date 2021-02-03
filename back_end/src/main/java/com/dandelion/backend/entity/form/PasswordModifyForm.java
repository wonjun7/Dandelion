package com.dandelion.backend.entity.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordModifyForm {

    private String current_password;

    private String new_password;

    private String confirm_new_password;
}
