/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author dorraayari
 */
public class Coach {
     String nom , description , weight , height , occupation;
    int id;
    int age;
private String image;


    public Coach() {
    }

    public Coach(String nom, String description, String weight, String height, String occupation, int age, String image) {
        this.nom = nom;
        this.description = description;
        this.weight = weight;
        this.height = height;
        this.occupation = occupation;
        this.age = age;
          this.image = image;
            this.id = id;
    }

    public Coach(String nom, String description, String weight, String height, String occupation, int age, int id) {
        this.nom = nom;
        this.description = description;
        this.weight = weight;
        this.height = height;
        this.occupation = occupation;
        this.age = age;
        this.id = id;
    }

      public Coach(String nom, String description, String weight, String height, String occupation, int age, int id, String image) {
        this.nom = nom;
        this.description = description;
        this.weight = weight;
        this.height = height;
        this.occupation = occupation;
        this.age = age;
        this.id = id;
          this.image = image;
    }

   

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Coach{" + "nom=" + nom + ", description=" + description + ", weight=" + weight + ", height=" + height + ", occupation=" + occupation + ", age=" + age + ", id=" + id + ", image=" + image + '}';
    }



}
