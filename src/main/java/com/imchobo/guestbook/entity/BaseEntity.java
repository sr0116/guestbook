package com.imchobo.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter

public class BaseEntity {
  @CreatedDate // 이게 중요
  @Column(updatable = false, name = "regdate")
  private LocalDateTime regDate;
  
  @LastModifiedDate
  @Column(name = "moddate") // 기본 값이 트루라서 생략
  private  LocalDateTime modDate;
}
