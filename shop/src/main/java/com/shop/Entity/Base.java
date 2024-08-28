package com.shop.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public class Base extends BaseTime{
    @CreatedBy
    @Column(updatable = false)
    private String createBy;
        //데이터 최초 등록한 사람을 찾기

    @LastModifiedBy
    private String modifiedBy;
        //데이터를 수정한 사람을 찾기

}
