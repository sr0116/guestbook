package com.imchobo.guestbook.service;


import com.imchobo.guestbook.dto.GuestbookDTO;
import com.imchobo.guestbook.dto.PageRequestDTO;
import com.imchobo.guestbook.dto.PageResponseDTO;
import com.imchobo.guestbook.entity.Guestbook;
import com.imchobo.guestbook.entity.QGuestbook;
import com.imchobo.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@AllArgsConstructor
@Service
@Transactional// 클래스 내에 붙을 수도 있고 메서드 위에 붙을 수 있음(클래스 : 모든 메서드 , 메서드 : 하나)
public class GuestbookServiceImpl implements GuestbookService {
//  DDD : Domain Driven Development

  private final GuestbookRepository repository; // 프로토 타입 못하게 제한 시킴



  public Long write(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }
//public Long write(GuestbookWriteDTO dto) {
//  return repository.save(dto.toEntity()).getGno();
//} - 원래는 이렇게 여러개의 DTo를 생성해서 서비스 코드를 줄이고 책임을 분산시켜야 함 - 현재는 일단 간단한 방식으로

  @Transactional(readOnly = true) // 이렇게 메서드에 붙이면 이건 읽기 전용으로 바뀜
  public List<GuestbookDTO> readAll() {
    return repository.findAll(Sort.by( Sort.Direction.DESC, "gno")).stream().map(this::toDto).toList();
  }
  @Override
  public PageResponseDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
    PageResponseDTO<GuestbookDTO, Guestbook> responseDTO = null;
    return new PageResponseDTO<>( repository.findAll(getSearch(pageRequestDTO) ,pageRequestDTO.getpageable(Sort.by( Sort.Direction.DESC, "gno"))),this::toDto);
  }
//public List<GuestbookListDTO> readAll() {
//  return  repository.findAll().stream().map(GuestbookListDTO::new).toList();

//}

  public GuestbookDTO read(Long gno) {
    return toDto(repository.findById(gno).orElseThrow(() -> new RuntimeException("지정된 번호가 없습니다.")));
  }

  public void modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
  }

  public void remove(Long gno) {
      repository.deleteById(gno);
  }
  private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
    String type = requestDTO.getType();

    BooleanBuilder builder = new BooleanBuilder();

    QGuestbook qGuestbook = QGuestbook.guestbook;
    String keyword = requestDTO.getKeyword();
    builder.and(qGuestbook.gno.gt(0));

    if(type == null || type.trim().length() == 0){
      return builder;
    }

    BooleanBuilder conditionBuilder = new BooleanBuilder();

    if(type.contains("t")){
      conditionBuilder.or(qGuestbook.title.contains(keyword));
    }
    if(type.contains("c")){
      conditionBuilder.or(qGuestbook.content.contains(keyword));
    }
    if(type.contains("w")){
      conditionBuilder.or(qGuestbook.writer.contains(keyword));
    }
    builder.and(conditionBuilder);

    return builder;
  }

  public Guestbook toEntity(GuestbookDTO guestbookDTO) {
    return GuestbookService.super.toEntity(guestbookDTO);
  }


  public GuestbookDTO toDto(Guestbook guestbook) {
    return GuestbookService.super.toDto(guestbook);
  }
}
