package com.shop.Entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public abstract class BaseTime {

    @CreatedDate    //최초 생성 시간을 저장하는 애러테이션
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate   //생성된 항목의 수정 시간을 저장하는 애러테이션
    private LocalDateTime updateTime;


}
