package com.spring.ws.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("id")
	@NotNull(message = "id不能为Null")
	@Column(name="id")
	private Integer id;


	@ApiModelProperty("名称")
	@NotNull(message = "名称不能为Null")
	@NotEmpty(message = "名称不能为空")
	@Column(name="name")
	private String name;


	@ApiModelProperty("")
	@Column(name="class_id")
	private Long classId;



}
