public class Reflecteur {
    // Cablage du reflecteur 
    private final String alphabetReflecteur;

    public Reflecteur(String alphabetReflecteur) {         
        if (alphabetReflecteur == null || alphabetReflecteur.length() != 26) {
            throw new IllegalArgumentException("L'alphabet du reflecteur doit contenir 26 caracteres");
        }
        if (!alphabetReflecteur.chars().allMatch(Character::isLetter)) {
            throw new IllegalArgumentException("Seules les lettres sont autorisees");
        }
        this.alphabetReflecteur = alphabetReflecteur;
    }

    public int reflechir(int indiceEntree) {
        char lettreReflecteur = alphabetReflecteur.charAt(indiceEntree);
        int indiceSortieReflecteur = lettreReflecteur - 'A';
        return indiceSortieReflecteur;
    }
    
    public String getAlphabetReflecteur() {
        return alphabetReflecteur;
    }
}