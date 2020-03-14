package com.zfx.supper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanField implements Serializable {

	private static final long serialVersionUID = 4279960350136806659L;

	private String columnName;

	private String columnType;

	private String columnComment;

	private String columnDefault;

	private String name;

	private String type;

}
