package com.imchobo.guestbook.service;


import com.imchobo.guestbook.dto.GuestbookDTO;
import com.imchobo.guestbook.dto.PageRequestDTO;
import com.imchobo.guestbook.dto.PageResponseDTO;
import com.imchobo.guestbook.entity.Guestbook;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
// 인터페이스에 서비스 어노테이션을 하면 지금 중복으로 두개가 빈 주입이 되어버리는 것이라 오류 발생 가능성 있음
public interface GuestbookService {
  Long write(GuestbookDTO guestbookDTO); // 서비스를 나중에 컨트롤러가 호출
  GuestbookDTO read(Long gno); // 선언에서는 엔티티가 보이면 안되니까 디티오로
  List<GuestbookDTO> readAll();
  PageResponseDTO <GuestbookDTO, Guestbook> getList( PageRequestDTO PageRequestDTO);
  void modify (GuestbookDTO guestbookDTO);
  void remove (Long gno);

  default Guestbook toEntity(GuestbookDTO guestbookDTO) {
   return Guestbook.builder().gno(guestbookDTO.getGno())
      .gno(guestbookDTO.getGno())
      .title(guestbookDTO.getTitle())
      .content(guestbookDTO.getContent())
      .writer(guestbookDTO.getWriter())
      .build();
  }

  default GuestbookDTO toDto(Guestbook  guestbook) {
    return guestbook == null ? null : GuestbookDTO.builder()
      .gno(guestbook.getGno())
      .title(guestbook.getTitle())
      .content(guestbook.getContent())
      .writer(guestbook.getWriter())
      .regDate(guestbook.getRegDate())
      .modDate(guestbook.getModDate())
      .build();
  }

}
