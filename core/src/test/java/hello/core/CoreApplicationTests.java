package hello.core;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

@SpringBootTest
class CoreApplicationTests {

	@Test
	void contextLoads() {
		ApplicationContext ac = new
				GenericXmlApplicationContext("appConfig.xml");

		MemberService memberService = ac.getBean("memberService",
				MemberService.class);
		Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
	}

}
