package com.imchobo.guestbook.service;


import com.imchobo.guestbook.dto.GuestbookDTO;
import com.imchobo.guestbook.entity.Guestbook;
import com.imchobo.guestbook.repository.GuestbookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class GuestbookServiceImpl implements GuestbookService {
  private GuestbookRepository repository;

  public Long write(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }

  public List<GuestbookDTO> readAll() {
    return repository.findAll().stream().map(this::toDto).toList();
  }

  public GuestbookDTO read(Long gno) {
    return toDto(repository.findById(gno).orElseThrow(() -> new RuntimeException("지정된 번호가 없습니다.")));
  }

  public int modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
   return  repository.existsById(guestbookDTO.getGno()) ? 1 : 0;

  }


  public int remove(Long gno) {
//     repository.deleteById(gno);
//     return repository.existsById(gno) ? 0 : 1;
    try {
      repository.deleteById(gno);
      return 1;
    } catch (Exception e) {
      return 0;
    }


  }

  public Guestbook toEntity(GuestbookDTO guestbookDTO) {
    return GuestbookService.super.toEntity(guestbookDTO);
  }


  public GuestbookDTO toDto(Guestbook guestbook) {
    return GuestbookService.super.toDto(guestbook);
  }
}
