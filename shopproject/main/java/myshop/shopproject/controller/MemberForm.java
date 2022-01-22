package myshop.shopproject.controller;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberForm {

    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordValid;
    @NotEmpty
    private String cellPhone;


    @NotEmpty
    private String name;
    @NotEmpty
    private String city;
    @NotEmpty
    private String street;
    @NotEmpty
    private String zipcode;




}
