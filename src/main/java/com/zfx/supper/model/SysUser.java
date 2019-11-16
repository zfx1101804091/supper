package com.zfx.supper.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
/*
 * 功能描述:@EqualsAndHashCode注解是为了完美继承父类的基本属性 
 * 	
 * @Param: 
 * @Return: 
 * @Author: Administrator
 * @Date: 2019/11/17 0017 0:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity<Long> {
	private static final long serialVersionUID = -6525908145032868837L;
	private String username;
	private String password;
	private String nickname;
	private String headImgUrl;
	private String phone;
	private String telephone;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date birthday;
	private Integer sex;
	private Integer status;
	//private String intro;

	public interface Status {
		int DISABLED = 0;
		int VALID = 1;
		int LOCKED = 2;
	}
}
