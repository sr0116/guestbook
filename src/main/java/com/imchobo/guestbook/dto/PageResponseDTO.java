package com.imchobo.guestbook.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.*;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO <DTO, Entity> {
  private List<DTO> list;
  private  int totalPage, page, size, start, end;
  private  boolean prev, next;
  private  List<Integer> pageList;



//  private  Runnable runnable = () -> {};
//  private Consumer <DTO> consumer = dto -> {};
//  private Supplier<DTO> supplier = () -> {return  null;};
//  private Predicate<DTO> predicate = dto -> true;
//  private Function<DTO, DTO> function =  dto -> dto;
//  private UnaryOperator<DTO> unaryOperator = dto -> dto;

  public  PageResponseDTO(Page<Entity> page, Function<Entity, DTO> mapper //  매퍼는 변환함수){
  ) {
//    this.dtos = page.stream().map(mapper).toList(); // 스트림에서 toList하면 리스트 타입으로 전환
    ;
    list = page.stream().map(mapper).toList();
    totalPage = page.getTotalPages();
    makePageList(page.getPageable());
  }
  private void makePageList(Pageable pageable){
    final int PAGE_VIEW_COUNT = 5;
    page =  pageable.getPageNumber() + 1;
    size = pageable.getPageSize();
    int tempEnd = (int)(Math.ceil(page/ 1d/  PAGE_VIEW_COUNT)) * PAGE_VIEW_COUNT;
    start = tempEnd - (PAGE_VIEW_COUNT -1);
    prev = start > 1;
    end = totalPage > tempEnd ? tempEnd : totalPage;
    next = totalPage > tempEnd;

    pageList = IntStream.rangeClosed(start, end).boxed().toList(); // box 인티저 스트림
  }
}
