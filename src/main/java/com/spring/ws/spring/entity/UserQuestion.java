package com.spring.ws.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuestion {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("用户id")
	@NotNull(message = "用户id不能为Null")
	@Column(name="user_id")
	private Long userId;


	@ApiModelProperty("内容")
	@Column(name="content")
	private String content;


	@ApiModelProperty("")
	@Column(name="created_at")
	private LocalDateTime createdAt;


	@ApiModelProperty("")
	@Column(name="updated_at")
	private LocalDateTime updatedAt;


	@ApiModelProperty("")
	@Column(name="deleted_at")
	private LocalDateTime deletedAt;



}
