package com.kramar.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringBootSampleDataJpaH2ApplicationTests {

	@Test
	public void contextLoads() {
		int[] array = {5, 89, 34, 78, 1, 56, 3};

		for (int i = 1; i < array.length; i++) {
			boolean sorted = true;
			for (int j = 0; j < array.length - i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = temp;
					sorted = false;
				}
			}
			if (sorted) break;
		}


		System.out.println(Arrays.toString(array));
	}

}
