# ***Table of Contents***
- [To-Do List (plugin list)](https://skiftstar.github.io/curry-server-plugins/#to-do-list)
- [Plugins](https://skiftstar.github.io/curry-server-plugins/#plugins)
  - [TeamChat](https://skiftstar.github.io/curry-server-plugins/#teamchat)
  - [BungeeDMs](https://skiftstar.github.io/curry-server-plugins/#bungeedms)
  - [GlobalChat](https://skiftstar.github.io/curry-server-plugins/#globalchat)
- [Downloads](https://skiftstar.github.io/curry-server-plugins/#downloads)

# ***To-Do List***

> ## Done
- Bungee Teamchat
- Bungee DMs

> ## Working on
- Global Chat

> ## To-Do
- Tab Plugin
- Hub Plugin


# ***Plugins***

## **Teamchat**

> ### Beschreibung
Erlaubt es dem Team, unabhängig von wer auf welchem Server ist, zu kommunizieren, ohne dass normale Member diese Nachrichten sehen können

> ### Souce-Code
Klasse des Commands ist [hier zu finden](https://github.com/Skiftstar/curry-server-plugins/blob/gh-pages/ServerCore_Bungee/TeamchatCommand.java)

> ### Known Issues
- None

> ### Nötige Permissions
- core.teamchat -> Senden und Empfangen von TeamChat nachrichten

> ### Commands
- /tc [Nachricht] -> Senden einer Nachricht in den Teamchat

## **BungeeDMs**

> ### Beschreibung
Erlaubt es Spielern einem anderen Spieler, der auch auf einem anderen Server innerhalb des Netzwerkes sein kann, eine private Nachricht zu schicken

> ### Source-Code
Klasse des Commands ist [hier zu finden](https://github.com/Skiftstar/curry-server-plugins/blob/gh-pages/ServerCore_Bungee/DMCommand.java)

> ### Known Issues
- TabCompleter funktioniert nur bei /dm, da /msg und /tell Minecraft/Spigot commands sind und der TabCompleter da nich überschrieben wird

> ### Nötige Permissions
- core.dm -> Erlaubt Benutzung des /dm Commands

> ### Commands
- /dm [Spieler] [Nachricht] -> Schickt eine Private Nachricht an den angegebenen Spieler
  - Aliases: /tell /msg

## **GlobalChat**

> ### Beschreibung
Erlaubt es Spielern den Serverübergreifenden Chat zu sehen und auch darin zu schreiben

> ### Source-Code
Klasse des Commands ist [hier zu finden](https://github.com/Skiftstar/curry-server-plugins/blob/gh-pages/ServerCore_Bungee/GlobalChatCommand.java)

> ### Known Issues
- Spieler mit der Permission "*" können den Globat Chat nicht deaktivieren

> ### Nötige Permissions
- core.global -> Erlaubt es den /g Command zu verwenden
- core.globalenabled -> Bestimmt ob ein Spieler den Global Chat sehen kann/Darin schreiben kann
  - Permission muss *nicht* manuell gesetzt werden! Sie wird über den /g Command angepasst

> ### Commands
- /g (Nachricht) -> Schickt eine Nachricht in den Globalen Chat *ODER* aktiviert/deaktiviert den Globalen Chat, falls keine Nachricht eingegeben wird
  - Aliases: /global

# ***Downloads***

kommt noch
