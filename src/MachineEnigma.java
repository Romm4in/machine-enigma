public class MachineEnigma {
    // Rotors de la machine Enigma modèle I
    public static final String ROTOR_I = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    public static final String ROTOR_II = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    public static final String ROTOR_III = "BDFHJLCPRTXVZNYEIWGAKMUSQO"; 
    public static final String REFLECTEUR_UKW_B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    public static final String REFLECTEUR_UKW_C = "FVPJIAOYEDRZXWGCTKUQSBNMHL";

    // Composants de la machine
    private final Rotor rotorI;
    private final Rotor rotorII;
    private final Rotor rotorIII;
    private Reflecteur reflecteur;
    private TableauConnexions tableauConnexions;


    public MachineEnigma(Rotor rotorI, Rotor rotorII, Rotor rotorIII, Reflecteur reflecteur, TableauConnexions tableauConnexions) {
        this.rotorI = rotorI;
        this.rotorII = rotorII;
        this.rotorIII = rotorIII;
        this.reflecteur = reflecteur;
        this.tableauConnexions = tableauConnexions;
    }
    
    public MachineEnigma() {
        this(new Rotor(ROTOR_I), new Rotor(ROTOR_II), new Rotor(ROTOR_III),
             new Reflecteur(REFLECTEUR_UKW_B), new TableauConnexions());
    }
    
    public void configurerPositionsRotors(char pos1, char pos2, char pos3) {
        rotorI.setPosition(Character.toUpperCase(pos1));
        rotorII.setPosition(Character.toUpperCase(pos2));
        rotorIII.setPosition(Character.toUpperCase(pos3));
    }

    public String getPositionsRotors() {
        return "" + (char)('A' + rotorI.getPosition())
                 + (char)('A' + rotorII.getPosition())
                 + (char)('A' + rotorIII.getPosition());
    }

    private void tournerRotors() {
        boolean tourCompletRotor3 = rotorIII.tournerRotor();
    
        if (tourCompletRotor3) {
            boolean tourCompletRotor2 = rotorII.tournerRotor();
            if (tourCompletRotor2) {
                rotorI.tournerRotor();
            }
        }
    }

    public char chiffrerLettre(char lettre) {
        char upperLettre = Character.toUpperCase(lettre);

        // On ignore les caractères hors alphabet
        if (upperLettre < 'A' || upperLettre > 'Z') {
            return lettre;
        }
        
        // Rotation des rotors à chaque frappe
        tournerRotors();
        
        // On convertit la lettre en index
        int index = upperLettre - 'A';
        
        // On passe dans le tableau de connexions
        index = tableauConnexions.appliquerSubstitution(index);
        
        // Passage aller dans les rotors (ordre : III, II, I)
        index = rotorIII.passageRotorAller(index);
        index = rotorII.passageRotorAller(index);
        index = rotorI.passageRotorAller(index);
        
        // On passe dans le réflecteur
        index = reflecteur.reflechir(index);
        
        // Passage retour dans les rotors
        index = rotorI.passageRotorRetour(index);
        index = rotorII.passageRotorRetour(index);
        index = rotorIII.passageRotorRetour(index);
        
        // On passe une nouvelle fois par le tableau de connexions
        index = tableauConnexions.appliquerSubstitution(index);

        // On convertit l'index en la lettre chiffrée
        char lettreChiffree = (char) ('A' + index);
        return lettreChiffree;
    }

    public String chiffrerMessage(String message) {
        if (message == null) {
            throw new IllegalArgumentException("Le message ne peut pas etre null");
        }
        if (message.trim().isEmpty()) {
            return message;
        }
        StringBuilder sb = new StringBuilder();
        for (char lettre : message.toCharArray()) {
            sb.append(chiffrerLettre(lettre));    
        }
        return sb.toString();
    }

    public MachineEnigma setReflecteur(Reflecteur nouveauReflecteur) {
        this.reflecteur = nouveauReflecteur;
        return this;
    }
    public String getChoixReflecteur() {
        String alphabetReflecteur = reflecteur.getAlphabetReflecteur();
        if (alphabetReflecteur.equals(REFLECTEUR_UKW_B)) {
            return "UKW-B";
        } else {
            return "UKW-C";
        }
    }
    
    public MachineEnigma setTableauConnexions(TableauConnexions nouveauTableau) {
        this.tableauConnexions = nouveauTableau;
        return this;
    }
    public String getTableauConnexions() {
        return tableauConnexions.getTableauConnexions();
    } 
}