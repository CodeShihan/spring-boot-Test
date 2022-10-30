package com.spring.ws.spring.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasePageRequestInfo implements Serializable {

    @Min(value = 1,message = "开始页数不能小于1")
    @NotNull(message = "开始页数不能为NULL")
    @ApiModelProperty("起始页数")
    private Integer page;

    @NotNull(message = "每页行数不能为NULL")
    @Min(value = 1,message = "每页行数不能小于1")
    @ApiModelProperty("每页行数")
    private Integer rows;

    @ApiModelProperty("关键字")
    private String searchText;

    @NotNull(message = "排序方式不能为NULL")
    @NotBlank(message = "排序方式不能为空")
    private String orderBy;
}
