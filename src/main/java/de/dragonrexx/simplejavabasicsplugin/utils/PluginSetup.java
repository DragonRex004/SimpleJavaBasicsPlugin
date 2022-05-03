package de.dragonrexx.simplejavabasicsplugin.utils;

public interface PluginSetup {

    //in dieser Methode werden alle Commands Registriert
    void commandRegistry();

    //in dieser Methode werden alle Listener Registriert
    void eventRegistry();
}
