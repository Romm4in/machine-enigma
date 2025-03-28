import java.util.HashMap;
import java.util.Map;

public class TableauConnexions {

    private final Map<Character, Character> connexions;

    public TableauConnexions() {
        this.connexions = new HashMap<>();
    }

     //Format du tableau de connexions: "AB CD" ( ex : A est connecté à B)
    public TableauConnexions(String pairesDeConnexions) {
        this.connexions = new HashMap<>();
        
        if (pairesDeConnexions != null && !pairesDeConnexions.trim().isEmpty()) {
            String[] paires = pairesDeConnexions.split(" ");
            for (String paire : paires) {
                if (paire.length() == 2) {
                    ajouterConnexion(paire.charAt(0), paire.charAt(1));
                }
            }
        }
    }

    public void ajouterConnexion(char lettre1, char lettre2) {
        char lettre1Maj = Character.toUpperCase(lettre1);
        char lettre2Maj = Character.toUpperCase(lettre2);
        
        // Vérification connexion existante
        if (connexions.containsKey(lettre1Maj) || connexions.containsKey(lettre2Maj)) {
            throw new IllegalArgumentException(
                "Connexion existante : " + lettre1Maj + " ou " + lettre2Maj + " est deja connectee"
            );
        }
        connexions.put(lettre1Maj, lettre2Maj);
        connexions.put(lettre2Maj, lettre1Maj);
    }

    public int appliquerSubstitution(int index) {
        char lettre = (char) ('A' + index);
        char lettreMaj = Character.toUpperCase(lettre);
        char lettreSubstituee = connexions.getOrDefault(lettreMaj, lettreMaj);
        return lettreSubstituee - 'A';
    }
    
    public String getTableauConnexions() {
        if (connexions.isEmpty()) {
            return "Aucune connexion";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Character> connexion : connexions.entrySet()) {
            char lettre1 = connexion.getKey();
            char lettre2 = connexion.getValue();
            
            if (lettre1 < lettre2) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(lettre1).append(lettre2);
            }
        }
        
        return sb.toString();
    }
}
