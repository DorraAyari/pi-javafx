/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author User
 */
public class ReponseReclamation {
    private int id;
    private String reponse;
    private int reclamation_id;
    private Reclamation reclamtion;

    public ReponseReclamation(int id, String reponse, int reclamation_id) {
        this.id = id;
        this.reponse = reponse;
        this.reclamation_id = reclamation_id;
    }

    public ReponseReclamation(String reponse, int reclamation_id) {
        this.reponse = reponse;
        this.reclamation_id = reclamation_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public Reclamation getReclamtion() {
        return reclamtion;
    }

    public void setReclamtion(Reclamation reclamtion) {
        this.reclamtion = reclamtion;
    }

    @Override
    public String toString() {
        return "ReponseReclamation{" + "id=" + id + ", reponse=" + reponse + ", reclamation_id=" + reclamation_id + ", reclamtion=" + reclamtion + '}';
    }
    
    
    
}
