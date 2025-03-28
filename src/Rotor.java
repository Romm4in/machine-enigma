public class Rotor {
    // Cablage du rotor
    private final String alphabetRotor;
    // Position du rotor
    private int position;

    public Rotor(String alphabetRotor) {
        if (alphabetRotor == null || alphabetRotor.length() != 26) {
            throw new IllegalArgumentException("L'alphabet du rotor doit contenir 26 caracteres");
        }
        if (!alphabetRotor.chars().allMatch(Character::isLetter)) {
            throw new IllegalArgumentException("Seules les lettres sont autorisees");
        }
        this.alphabetRotor = alphabetRotor;
        this.position = 0;
    }

    public void setPosition(char caractere) {
        this.position = caractere - 'A';
    }

    public int getPosition() {
        return position;
    }

    //On tourne le rotor d'une position + on retourne true si le rotor a fait un tour complet
    public boolean tournerRotor() {
        position = (position + 1) % 26;    
        if (position == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Passage du rotor en direction entrée -> sortie
    public int passageRotorAller (int indiceEntree) {
        // On ajuste l'indice d'entrée selon la position du rotor
        int indicePositionRotor = (indiceEntree + position) % 26;
        char lettreSubstituee = alphabetRotor.charAt(indicePositionRotor);
        // On convertit la lettre en indice et ajuste selon la position du rotor
        return Math.floorMod(lettreSubstituee - 'A' - position, 26);  
    }

    //Passage du rotor en direction sortie -> entrée
    public int passageRotorRetour(int indiceSortie) {
        int indicePositionRotor = (indiceSortie + position) % 26;
        
        // On cherche l'indice d'entrée qui correspond à la sortie
        for (int i = 0; i < 26; i++) {
            if (alphabetRotor.charAt(i) - 'A' == indicePositionRotor) {
                int indiceEntree = Math.floorMod(i - position, 26);
                return indiceEntree;
            }
        }
        return indiceSortie;
    }
}