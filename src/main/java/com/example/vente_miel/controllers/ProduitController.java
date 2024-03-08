package com.example.vente_miel.controllers;

import com.example.vente_miel.entities.ImageModel;
import com.example.vente_miel.entities.Produit;
import com.example.vente_miel.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value ="/products" )
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @GetMapping({"/getAllProducts"})
    public List<Produit> getAllProduits() {
        return this.produitService.getAllProduits();
    }

    @GetMapping(value = "/getProductById/{id}")
    public Produit getProduitByID(@PathVariable("id") long id) {
        return this.produitService.getProduitByID(id);
    }

    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Produit addNewProduit(@RequestPart("produit") Produit produit,
                                 @RequestPart("imageFile")MultipartFile[] file) {
        //return this.produitService.addNewProduit(produit);
        try {
             Set<ImageModel> images= uploadImage(file);
             produit.setImageProduit(images);
             return produitService.addNewProduit(produit);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping(value = "/{id}")
    public Produit updateProduit(@PathVariable("id") long id, @RequestBody Produit produit) {
        return produitService.updateProduitByID(id, produit);
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public void deleteProduit(@PathVariable("id") long id) {
        this.produitService.deleteProduitById(id);
    }

    @GetMapping({ "/getProductDetails/{isSingleProductCheckout}/{id}"})
    public List<Produit> getProduitDetails( @PathVariable(name="isSingleProductCheckout") Boolean isSingleProductCheckout ,@PathVariable(name="id")  long id) {
        return this.produitService.getProduitDetails(isSingleProductCheckout,id);
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels=new HashSet<>();
        for (MultipartFile file:multipartFiles){
            ImageModel imageModel=new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
}

}
