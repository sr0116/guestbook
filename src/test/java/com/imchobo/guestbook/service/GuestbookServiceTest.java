package com.imchobo.guestbook.service;

import com.imchobo.guestbook.dto.GuestbookDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
@Log4j2
public class GuestbookServiceTest {

  @Autowired
  private GuestbookService guestbookService; // 인터페이스지만 찾으러 감

  @Test
  public  void testExists() {
    log.info(guestbookService);
  }
  @Test
  public  void testWrite(){
    GuestbookDTO guestbookDTO = GuestbookDTO.builder()
      .title("테스트용")
      .content("내용")
      .writer("작성자")
      .build();

    guestbookService.write(guestbookDTO);
  }

  @Test
  public  void testModify(){
    GuestbookDTO guestbookDTO = GuestbookDTO.builder()
      .gno(508L)
      .title("수정")
      .content("수정")
      .writer("수정")
      .build();
    guestbookService.modify(guestbookDTO);
  }

  @Test
  public  void testDelete(){
    guestbookService.remove(505L);
  }

  @Test
  public  void testRead(){
    guestbookService.read(509L);
    log.info(guestbookService.read(509L));
  }

  @Test
  public  void testList(){
    guestbookService.readAll();

    log.info( guestbookService.readAll());
  }
}
