package org.example.asm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "san_pham")
@Entity
public class SanPham {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "ma_san_pham")
    private String maSanPham ;

    @Column(name =  "ten_san_pham")
    private String tenSanPham;

    @Column(name = "trang_thai")
    private String trangThai ;

    @Column(name = "ngay_tao")
    private Date ngayTao ;

    @Column(name = "ngay_sua")
    private Date ngaySua ;

    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    private DanhMuc danhMuc ;
}
