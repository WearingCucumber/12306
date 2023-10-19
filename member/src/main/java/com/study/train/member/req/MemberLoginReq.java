package com.study.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberLoginReq {
    @NotBlank
    @Pattern(regexp = "^1\\d{10}$",message = "手机号格式错误")
    private String mobile;
    @NotBlank
    private String code;
}
