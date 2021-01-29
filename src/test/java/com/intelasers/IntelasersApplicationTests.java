package com.intelasers;

import com.intelasers.entity.TemplateEntity;
import com.intelasers.repository.TemplateRepository;
import com.intelasers.services.TemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntelasersApplicationTests {
    @Autowired
	TemplateService templateService ;

    @Autowired
	TemplateRepository templateRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void findTemplateById() {
		System.out.println(templateService.getTemplateById((1L)));
	}


	@Test
	void findTemplateByName() {
		System.out.println(templateRepository.getTemplateByName(("")));//模糊匹配，为空时所有模板都会输出
	}

}
