package hello.core.singleton;

import hello.core.member.MemberService;
import hello.core.order.AppConfig;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        System.out.println("member1" + memberService1);
        System.out.println("member2" + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
