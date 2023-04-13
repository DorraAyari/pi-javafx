/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author EMNA
 */
public class Blog {
    String nom , description , moredescription , slogan ;
    int id;
    private String image;
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMoredescription() {
        return moredescription;
    }

    public void setMoredescription(String moredescription) {
        this.moredescription = moredescription;
    }

    @Override
    public String toString() {
        return "Blog{" + "nom=" + nom + ", description=" + description + ", moredescription=" + moredescription + ", slogan=" + slogan + ", id=" + id + ", image=" + image + '}';
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Blog(String nom, String description, String moredescription, String slogan, int id) {
        this.nom = nom;
        this.description = description;
        this.moredescription = moredescription;
        this.slogan = slogan;
        this.id = id;
    }

    public Blog(String nom, String description, String moredescription, String slogan, String image) {
        this.nom = nom;
        this.description = description;
        this.moredescription = moredescription;
        this.slogan = slogan;
        this.image = image;
    }

    public Blog(String nom, String description, String moredescription, String slogan, int id, String image) {
        this.nom = nom;
        this.description = description;
        this.moredescription = moredescription;
        this.slogan = slogan;
        this.id = id;
        this.image = image;
    }

}
