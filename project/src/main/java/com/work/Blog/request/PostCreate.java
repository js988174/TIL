package com.work.Blog.request;

import com.work.Blog.domain.Post;
import com.work.Blog.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.")
    private final String title;

    @NotBlank(message = "콘텐츠를 입력해주세요.")
    private final String content;

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostCreate changeTitle(String title) {
        return PostCreate.builder()
                .title(title)
                .content(content)
                .build();
    }

    public void validate() {
        if (title.contains("")) {
            throw new InvalidRequest();
        }
    }

    // 빌더의 장점
    // 1. 가독성이 좋다
    // 2. 필요한 값만 받을 수 있다.
}
