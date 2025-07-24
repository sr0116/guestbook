package com.imchobo.guestbook.service;

import com.imchobo.guestbook.dto.GuestbookDTO;
import com.imchobo.guestbook.dto.PageRequestDTO;
import com.imchobo.guestbook.dto.PageResponseDTO;
import com.imchobo.guestbook.entity.Guestbook;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


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
//    GuestbookDTO guestbookDTO = GuestbookDTO.builder()
//      .title("테스트용")
//      .content("내용")
//      .writer("작성자")
//      .build();
//
//    guestbookService.write(guestbookDTO);
   Long gno =  guestbookService.write(GuestbookDTO.builder()
      .title("테스트용")
      .content("내용")
      .writer("작성자")
      .build());
    Assertions.assertNotNull(gno);
  }

  @Test
  public  void testModify(){
//    GuestbookDTO guestbookDTO = GuestbookDTO.builder()
//      .gno(509L)
//      .title("메서드 변경 후 다시 재 수정")
//      .content("수정")
//      .writer("수정")
//      .build();
//    guestbookService.modify(guestbookDTO);
    Long gno = 509L;
    GuestbookDTO dto = guestbookService.read(gno);
    dto.setContent("또수정");
    guestbookService.modify(dto);
  }

  @Test
  public  void testDelete(){
    guestbookService.remove(505L);
  }

  @Test
  public  void testRead(){
//    guestbookService.read(509L);
//    log.info(guestbookService.read(509L));
    Long gno = 509L;
    GuestbookDTO dto = guestbookService.read(gno);
    GuestbookDTO expect = GuestbookDTO.builder()
      .title("메서드 변경 후 다시 재 수정")
      .content("수정")
      .writer("수정")
      .gno(gno)
      .build();

    Assertions.assertEquals(expect.getTitle(),dto.getTitle());
    Assertions.assertEquals(expect.getContent(),dto.getContent());
    Assertions.assertEquals(expect.getWriter(),dto.getWriter());

  }

  @Test
  public  void testReadAll(){
    guestbookService.readAll().forEach(log::info);
  }

  @Test
  public  void testList(){
    guestbookService.readAll();

    log.info( guestbookService.readAll());
  }

  @Test
  public  void testListDTO(){
  guestbookService.getList(PageRequestDTO.builder().page(2).size(5).build()).getList().forEach(log::info);
  }
  
  @Test
  public  void testListDTO2(){ // dto 정의하고
    PageResponseDTO<?,?> dto = guestbookService.getList(PageRequestDTO.builder().page(8).size(5).build());
    log.info(dto);

  }
  @Test
  public  void testGetListWith(){ // dto 정의하고
    guestbookService.getList(PageRequestDTO.builder().type("tc").keyword("0").build()).getList().forEach(log::info);


  }

}
