package com.alvis.exam.domain;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "cashregister")
public class cashregister extends MessageRequestVM {
    @Column(name = "id")
    private Integer id;

    @Column(name = "shoopName")
    private String shoopName;

    @Column(name = "customerCode")
    private String customerCode;

    @Column(name = "saleNumber")
    private Integer saleNumber;

    @Column(name = "stripSmokeNumber")
    private Integer stripSmokeNumber;

    @Column(name = "wrapSmokeNumber")
    private Integer wrapSmokeNumber;


}
