package com.zfx.supper;

import com.zfx.supper.mapper.UserMapper;
import com.zfx.supper.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SupperApplicationTests {
	
	@Autowired
	UserMapper userMapper;

	@Test
	void contextLoads() {
		SysUser userByName = userMapper.getUserByName("zfx");
		System.out.println(userByName);
	}

}
