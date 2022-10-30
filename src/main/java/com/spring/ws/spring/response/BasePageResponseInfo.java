package com.spring.ws.spring.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasePageResponseInfo <T> implements Serializable {

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private int page;

    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private int totalPage;

    /**
     * 总记录条数
     */
    @ApiModelProperty("总记录条数")
    private long records;

    /**
     * 返回内容
     */
    @ApiModelProperty("返回内容")
    private List<T> rows;

}
