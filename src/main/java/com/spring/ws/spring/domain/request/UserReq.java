package com.spring.ws.spring.domain.request;

import com.spring.ws.spring.request.BasePageRequestInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("用户请求表")
public class UserReq extends BasePageRequestInfo implements Serializable {
    @NotNull(message = "name不能为NULL")
    @NotBlank(message = "name不能为空")
    private String name;
}
