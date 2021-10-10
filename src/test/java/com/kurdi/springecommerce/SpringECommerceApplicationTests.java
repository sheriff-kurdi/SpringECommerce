package com.kurdi.springecommerce;

import com.kurdi.springecommerce.entities.Image;
import io.swagger.models.auth.In;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringECommerceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void hashSet() {
		Image i = Image.builder().name("image").url("image").build();
		Image ii = Image.builder().name("image").url("image").build();
		Assertions.assertTrue(i.equals(ii));

	}

}
