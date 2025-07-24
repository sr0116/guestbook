package com.imchobo.guestbook.repository;

import com.imchobo.guestbook.entity.Guestbook;
import com.imchobo.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.LongStream;


@SpringBootTest
@Log4j2
public class GuestbookRepositoryTest {

  @Autowired
  private GuestbookRepository repository;

  @Test
  public  void testExists() {
    log.info(repository);
  }
  @Test
  public void testInsert() {
//    repository.save(Guestbook.builder().title("제목").content("내용").writer("작성자").build());
    LongStream.range(1, 100).forEach(i -> repository.save(Guestbook.builder().title("제목" + i % 10).content("내용" + (i+5) % 10).writer("작성자").build()));
  }


  @Test
  public  void testUpdate(){
    repository.save(Guestbook.builder().gno(1L) .title("수정").content("내용").writer("작성자").build());
  }

//  @Test
//  public  void testDelete(){
//
//    repository.delete();
//  }

  @Test
  public  void testFindByAll(){
    repository.save(Guestbook.builder().gno(1L) .title("수정").content("내용").writer("작성자").build());
  }

  @Test
  public  void testQuery() {
//    Pageable pageable = PageRequest.of(0, 20, Sort.by("gno").descending());
//    QGuestbook qGuestbook = QGuestbook.guestbook;
//    String keyword = "1";
//    BooleanBuilder builder = new BooleanBuilder();
//    BooleanExpression expression = qGuestbook.title.contains(keyword);
//
//    builder.and(expression);
//
//    Page<Guestbook> result = repository.findAll(builder, pageable);
//    result.stream().forEach(guestbook -> {
//      System.out.println(guestbook);
//    });
  }

  @Test
  public void testQuerydsl() {
    Pageable pageable = PageRequest.of(0, 10);

//    QGuestbook qGuestbook = QGuestbook.guestbook;
//
//    String keyword = "1";
//
//    BooleanBuilder builder = new BooleanBuilder();
//
//    BooleanExpression expression = qGuestbook.title.contains(keyword);
//
//    builder.and(expression);
//
//
//    Page<Guestbook> guestbooks = repository.findAll(builder, pageable);
//
//    log.info("{}", guestbooks);
  }

  @Test
  @DisplayName("복합  테스트")
  public void testQuery2() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
    QGuestbook guestbook = QGuestbook.guestbook;
    String keyword = "1";
    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression exTitle = guestbook.title.contains(keyword);
    BooleanExpression exContent = guestbook.content.contains(keyword);
    BooleanExpression exAll =  exTitle.or(exContent);

    builder.and(exAll);
    builder.and(guestbook.gno.gt(0));
    Page<Guestbook> result = repository.findAll(builder, pageable);

      System.out.println(result);

  }

}
