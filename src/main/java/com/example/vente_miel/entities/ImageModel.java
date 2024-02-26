package com.example.vente_miel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Table(name = "image_model")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String name;
    private  String type;
    @Column(length = 50000000)
    private byte[] picByte;

    public ImageModel(String name, String type, byte[] picByte) {
        this.name=name;
        this.type=type;
        this.picByte=picByte;
    }
}
