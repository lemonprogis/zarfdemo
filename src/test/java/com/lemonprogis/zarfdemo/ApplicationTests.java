package com.lemonprogis.zarfdemo;

import com.lemonprogis.zarfdemo.model.Joke;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	void getRandomJokeTest() {
		Joke response = testRestTemplate.getForObject("/jokes/random", Joke.class);
		assertThat(response).isInstanceOf(Joke.class);
	}

}
