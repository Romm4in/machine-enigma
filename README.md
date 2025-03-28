# Projet d'implémentation de la Machine Enigma

Ce projet est une implémentation en Java de Enigma, une machine électromécanique portative servant au chiffrement et au déchiffrement de l'information. Elle a été principalement utilisée par l'Allemagne pendant la Seconde Guerre mondiale.

## Description du projet

Ce projet avait pour objectif de simuler le fonctionnement de la machine Enigma avec une configuration classique : 

- Simulation utilisant 3 rotors (I, II, III) de la machine Enigma 1 
- Implémentation des réflecteurs UKW-B et UKW-C pour plus de choix
- Implémentation du tableau de connexions 
- Rotation du prochain rotor après un tour complet du rotor précédent
- Validation robuste des entrées utilisateur

## Structure du Projet

Le projet est structuré de manière à ce qu'une classe représente un élément de la machine Enigma :

- `Rotor.java` : Représente le rotor
- `Reflecteur.java` : Représente le réflecteur
- `TableauConnexions.java` : Représente le tableau de connexions
- `MachineEnigma.java` : Classe qui intègre tous les composants de la machine Enigma
- `EnigmaApplication.java` : Interface console pour utiliser la machine


## Lancement de l'application
**Pour lancer l'application, il est nécessaire que Java soit installé sur votre système.**

#### Option 1 : Script de lancement (recommandé)
Double-cliquez simplement sur le fichier `lancer_enigma.bat` inclus dans le projet. Ce script permet de :
- Créer un répertoire bin pour les fichiers compilés
- Compiler automatiquement les fichiers source
- Lancer l'application Enigma

#### Option 2 : Utiliser un IDE
Le projet peut être facilement ouvert dans n'importe quel IDE Java. Il suffit d'importer le projet et d'exécuter la classe `EnigmaApplication` qui contient la méthode `main`.

## Utilisation

L'application propose un menu avec les options suivantes :

1. **Configurer la machine Enigma**
   - Configurer les positions initiales des 3 rotors (A-Z)
   - Choisir le réflecteur (UKW-B (par défaut) ou UKW-C)
   - Configurer le tableau de connexions (format: AB CD...)

2. **Chiffrer un message**
   - Entrer un message à chiffrer
   - Afficher le message chiffré

3. **Déchiffrer un message**
   - Entrer un message à déchiffrer
   - Afficher le message déchiffré

4. **Afficher la configuration actuelle**

0. **Quitter l'application**