package com.zfx.supper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateDetail implements Serializable {

	private static final long serialVersionUID = -164567294469931676L;

	private String beanName;
	private List<BeanField> fields;

}
