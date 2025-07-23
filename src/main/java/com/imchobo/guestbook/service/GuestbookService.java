package com.imchobo.guestbook.service;


import com.imchobo.guestbook.dto.GuestbookDTO;
import com.imchobo.guestbook.entity.Guestbook;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public interface GuestbookService {
  Long write(GuestbookDTO guestbookDTO); // 서비스를 나중에 컨트롤러가 호출
  GuestbookDTO read(Long gno); // 선언에서는 엔티티가 보이면 안되니까 디티오로
  List<GuestbookDTO> readAll();
  int modify (GuestbookDTO guestbookDTO); // 인트는 된거 개수
  int remove (Long gno); // 인트는 된거 개수

  default Guestbook toEntity(GuestbookDTO guestbookDTO) {
   return Guestbook.builder().gno(guestbookDTO.getGno())
      .gno(guestbookDTO.getGno())
      .title(guestbookDTO.getTitle())
      .content(guestbookDTO.getContent())
      .writer(guestbookDTO.getWriter())
      .build();
  }

  default GuestbookDTO toDto(Guestbook guestbook) {
  return GuestbookDTO.builder()
    .gno(guestbook.getGno())
    .title(guestbook.getTitle())
    .content(guestbook.getTitle())
    .content(guestbook.getContent())
    .writer(guestbook.getWriter())
    .regDate(guestbook.getRegDate())
    .modDate(guestbook.getModDate())
    .build();
  }

}
