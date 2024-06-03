package org.example.asm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "danh_muc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DanhMuc {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "ma_danh_muc")
    private String maDanhMuc ;

    @Column(name = "ten_danh_muc")
    private String tenDanhMuc ;

    @Column(name = "trang_thai")
    private String trangThai ;

    @Column(name = "ngay_tao")
    private Date ngayTao ;

    @Column(name = "ngay_sua")
    private Date ngaySua ;

}
