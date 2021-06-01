#!/bin/bash

CP=/home/pobj/pobj-ci.jar:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-core-1.3.jar

SRC=./src

echo "Date :"
date
echo

echo "Répertoire courant :"
pwd
echo

echo "Liste des fichiers :"
ls -lR
echo

echo "Lancement des tests"
echo java -cp $CP:. pobj.ci.Runner -c -cp $CP -sp $SRC "$@"
java -cp $CP:. pobj.ci.Runner -c -cp $CP -sp $SRC "$@"
