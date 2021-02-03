package com.dandelion.backend.entity.form;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberModifyForm {

    private String pw;

    private String new_pw;

    private String name;

    private String phone;

    private String address;

}
