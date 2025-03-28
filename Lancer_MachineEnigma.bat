@echo off
echo ===================================
echo   Machine Enigma - Demarrage
echo ===================================
echo.

REM Création du répertoire bin s'il n'existe pas
if not exist bin mkdir bin

REM Compilation des fichiers sources dans le bin
echo Compilation des fichiers sources...
javac -d bin src\*.java
echo.

REM Lancement de l'application depuis le bin
echo Demarrage de l'application Enigma...
echo.
java -cp bin EnigmaApplication

pause