package com.spring.ws.spring.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasePageSumRowResponseInfo<T,V> implements Serializable {
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


    /**
     *汇总行
     */
    @ApiModelProperty("汇总数据")
    private V sumRow;
}
