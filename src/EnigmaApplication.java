import java.util.Scanner;

public class EnigmaApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  
        System.out.println("\n \n=== Machine Enigma ===");
        MachineEnigma machine = new MachineEnigma();
        boolean continuer = true;
        while (continuer) {
            System.out.println("\nMenu principal :");
            System.out.println("1. Configurer la machine Enigma");
            System.out.println("2. Chiffrer un message");
            System.out.println("3. Dechiffrer un message");
            System.out.println("4. Afficher la configuration actuelle");
            System.out.println("0. Quitter");
            
            System.out.print("\nVotre choix : ");
            int choix = lireEntier(scanner, 0, 4);
            
            switch (choix) {
                case 1:
                    configurerMachineEnigma(scanner, machine);
                    break;
                case 2:
                    chiffrerMessage(scanner, machine, false);
                    break;
                case 3:
                    chiffrerMessage(scanner, machine, true);
                    break;
                case 4:
                    afficherMenuInfosConfig(machine);
                    break;
                case 0:
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;
            }
        }
        
        scanner.close();
    }

    private static void configurerMachineEnigma(Scanner scanner, MachineEnigma machine) {
        boolean retourMenuPrincipal = false;
        
        while (!retourMenuPrincipal) {
            System.out.println("\n=== Configuration de la machine Enigma ===");
            System.out.println("1. Configurer les positions initiales des rotors");
            System.out.println("2. Choix du reflecteur");
            System.out.println("3. Configurer le tableau de connexions");
            System.out.println("0. Retour au menu principal");
            System.out.print("\nVotre choix : ");
            int choix = lireEntier(scanner, 0, 3);
            
            switch (choix) {
                case 1:
                    configurerRotors(scanner, machine);
                    break;
                case 2:
                    choisirReflecteur(scanner, machine);
                    break;
                case 3:
                    configurerTableauConnexions(scanner, machine);
                    break;
                case 0:
                    retourMenuPrincipal = true;
                    break;
            }
        }
    }
        
    private static void configurerRotors(Scanner scanner, MachineEnigma machine) {
        System.out.println("\n--- Configuration des positions initiales des rotors ---");
        System.out.println("Positions actuelles : " + machine.getPositionsRotors());
        
        System.out.print("Nouvelle position du rotor I (A-Z) : ");
        char pos1 = lireLettre(scanner);
        System.out.print("Nouvelle position du rotor II (A-Z) : ");
        char pos2 = lireLettre(scanner);
        System.out.print("Nouvelle position du rotor III (A-Z) : ");
        char pos3 = lireLettre(scanner);

        machine.configurerPositionsRotors(pos1, pos2, pos3);
        System.out.println("Positions des rotors configurees sur : " + machine.getPositionsRotors());
    }
    
    private static void choisirReflecteur(Scanner scanner, MachineEnigma machine) {
        System.out.println("\n--- Choix du reflecteur ---");
        System.out.println("Reflecteur actuel : " + machine.getChoixReflecteur());
        System.out.println("1. Reflecteur UKW-B (par defaut)");
        System.out.println("2. Reflecteur UKW-C");
        System.out.print("\nVotre choix : ");
        int choix = lireEntier(scanner, 1, 2);
        
        Reflecteur nouveauReflecteur;
        if (choix == 1) {
            nouveauReflecteur = new Reflecteur(MachineEnigma.REFLECTEUR_UKW_B);
            System.out.println("Reflecteur UKW-B selectionne");
        } else {
            nouveauReflecteur = new Reflecteur(MachineEnigma.REFLECTEUR_UKW_C);
            System.out.println("Reflecteur UKW-C selectionne");
        }
        machine.setReflecteur(nouveauReflecteur);
    }
    
    private static void configurerTableauConnexions(Scanner scanner, MachineEnigma machine) {
        System.out.println("\n--- Configuration du tableau de connexions ---");
        System.out.println("Configuration actuelle : " + machine.getTableauConnexions());
        System.out.println("Format: AB CD EF... (par exemple, A est connecte a B)");
        System.out.print("Entrez les nouvelles connexions (ou laissez vide pour conserver les actuelles) : ");
        String nouvellesConnexions = lireConnexions(scanner);
    
        try {
            TableauConnexions nouveauTableauConnexions = new TableauConnexions(nouvellesConnexions);
            machine.setTableauConnexions(nouveauTableauConnexions);
            System.out.println("Nouvelle configuration du tableau de connexions : " + machine.getTableauConnexions());
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
            System.out.println("Le tableau de connexions n'a pas ete modifie");
        }
    }

    private static void afficherConfigurationMachine(MachineEnigma machine) {
        System.out.println("Rotors : I, II, III");
        System.out.println("Positions actuelles des rotors : " + machine.getPositionsRotors());
        System.out.println("Reflecteur actuel : " + machine.getChoixReflecteur());
        System.out.println("Tableau de connexions actuel : " + machine.getTableauConnexions());
    }
    
    // Chiffrement ou déchiffrement
    private static void chiffrerMessage(Scanner scanner, MachineEnigma machine, boolean estDechiffrement) {
        System.out.println("\n=== " + (estDechiffrement ? "Dechiffrement" : "Chiffrement") + " de message ===");
        afficherConfigurationMachine(machine);
        
        System.out.print("Entrez le message a " + (estDechiffrement ? "dechiffrer" : "chiffrer") + " : ");
        String message = scanner.nextLine();
        
        if (message.trim().isEmpty()) {
            System.out.println("Aucune action effectuee car le message est vide");
            return;
        }
        String positionsInitiales = machine.getPositionsRotors();
        String resultat = machine.chiffrerMessage(message.toUpperCase());
        System.out.println("Message " + (estDechiffrement ? "dechiffre" : "chiffre") + " : " + resultat);

        machine.configurerPositionsRotors(
            positionsInitiales.charAt(0),
            positionsInitiales.charAt(1),
            positionsInitiales.charAt(2)
        );
    }
    
    private static void afficherMenuInfosConfig(MachineEnigma machine) {
        System.out.println("\n=== Configuration actuelle ===");
        afficherConfigurationMachine(machine);
    }
    
    private static int lireEntier(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int valeur = Integer.parseInt(scanner.nextLine());
                if (valeur >= min && valeur <= max) {
                    return valeur;
                }
                System.out.print("Veuillez entrer un nombre entre " + min + " et " + max + " : ");
            } catch (NumberFormatException e) {
                System.out.print("Veuillez entrer un nombre valide : ");
            }
        }
    }

    private static char lireLettre(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().toUpperCase();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return input.charAt(0);
            }
            System.out.print("Veuillez entrer une lettre (A-Z) : ");
        }
    }

    private static String lireConnexions(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().toUpperCase().trim();

            if (input.isEmpty()) {
                return input;
            }
            
            boolean formatValide = true;
            for (char c : input.toCharArray()) {
                if (c != ' ' && !Character.isLetter(c)) {
                    formatValide = false;
                    break;
                }
            }
            
            if (!formatValide) {
                System.out.println("Format invalide, veuillez entrer uniquement des lettres");
                System.out.print("Entrez les nouvelles connexions (ou laissez vide pour conserver les actuelles) : ");
                continue;
            }
        
            String[] paires = input.split(" +");
            boolean unePaireValide = false;
            
            for (String paire : paires) {
                if (paire.length() == 2) {
                    unePaireValide = true;
                    break;
                }
            }
            
            if (unePaireValide) {
                return input;
            }
            
            System.out.println("Aucune paire valide detectee, veuillez reessayer");
            System.out.print("Entrez les nouvelles connexions (ou laissez vide pour conserver les actuelles) : ");
        }
    }
}
