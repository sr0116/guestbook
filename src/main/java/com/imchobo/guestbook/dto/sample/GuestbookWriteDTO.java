package com.imchobo.guestbook.dto.sample;

import com.imchobo.guestbook.entity.Guestbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GuestbookWriteDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;

  // 여기서 유효성 먼저 체크하면 서비스 코드 짧아짐
  public GuestbookWriteDTO(Guestbook guestbook) {
//   나중에 조회시
//    this.gno = guestbook.getGno();
//    this.title = guestbook.getTitle();
//    this.content = guestbook.getContent();
//    this.writer = guestbook.getWriter();
  }

  public Guestbook toEntity(){
    return  Guestbook.builder().title(title).content(content).writer(writer).build();
  }
}
