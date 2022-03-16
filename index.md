# ***Table of Contents***
- [To-Do List (plugin list)](https://skiftstar.github.io/curry-server-plugins/#to-do-list)
- [Admin Stuff](https://skiftstar.github.io/curry-server-plugins/#admin-stuff)
  - [Wie passe ich Nachrichte an?](https://skiftstar.github.io/curry-server-plugins/#nachrichten-anpassen)
  - [Wie füge ich neue Sprachen hinzu?](https://skiftstar.github.io/curry-server-plugins/#sprachen-adden) 
- [Plugins](https://skiftstar.github.io/curry-server-plugins/#plugins)
  - [LanguageHelper API](https://skiftstar.github.io/curry-server-plugins/#language-helper-api)
  - [CommandHelper API](https://skiftstar.github.io/curry-server-plugins/#command-helper-api)
  - ServerCore for Bungee
    - [Generelle Commands](https://skiftstar.github.io/curry-server-plugins/#bungee-generelle-commands)
    - [TeamChat](https://skiftstar.github.io/curry-server-plugins/#teamchat)
    - [BungeeDMs](https://skiftstar.github.io/curry-server-plugins/#bungeedms)
    - [GlobalChat](https://skiftstar.github.io/curry-server-plugins/#globalchat)
  - [LobbyCore](https://skiftstar.github.io/curry-server-plugins/#lobbycore)
  - [ServerCore](https://skiftstar.github.io/curry-server-plugins/#servercore)
- [Downloads](https://skiftstar.github.io/curry-server-plugins/#download-links)

# ***To-Do List***

> ## Done
- Bungee Teamchat
- Bungee DMs
- Global Chat
- Hub Plugin
- Language Support API (both Spigot and Bungee)
- Command Helper API (nur Spigot)
- Tab Plugin

> ## Working on
- Kleinere Commands (Gamemode, tp, etc.)

> ## To-Do
- LuckPerms Blocker
- Scoreboard

# ***Admin Stuff***

## **Nachrichten anpassen**
Zu jedem Plugin lässt sich im `Plugins folder` ein Ordner mit dem passenden Namen des Plugins finden. In diesem Ordner sind alle Sprachen im `locales` Ordner aufgelistet

Um eine Sprache zu editieren:
1. Datei mit TextEditor öffnen (am besten [Notepad++](https://notepad-plus-plus.org/downloads/) oder [VSCode](https://code.visualstudio.com/download))
2. In der Datei findest du die Nachrichten in Sektionen aufgeteilt. Jede Sektion besteht aus Nachrichten oder Beschreibungen
3. Editiere nur die Werte der Nachrichten Keys

Beispiel (# sind Kommentare):
```
Info: # Darfst du nicht ändern
  NoLangSet: '&aDu hast noch keine Sprache gesetzt, daher wird sie auf %default gesetzt'
  
  # NoLangSet darfst du NICHT ändern, das hinter dem Doppelpunkt DARFST du editieren, das ist die Nachricht ingame dann
  # Platzhalter (z.B. das %default hier) dürfen weggelassen werden. Wenn du sie benutzt, darfst du den Namen des Platzhalters aber nicht ändern
```

Prefixe von Nachrichten (z.B. das [LobbyCore] vor Nachrichten) sind meist in der Config der Plugins editierbar

## **Sprachen adden**
Zu jedem Plugin lässt sich im `Plugins folder` ein Ordner mit dem passenden Namen des Plugins finden. In diesem Ordner sind alle Sprachen im `locales` Ordner aufgelistet

Um eine Sprache hinzuzufügen:
1. Kopiere die Datei "de.yml", das ist die Standarddatei, indem du sie anklickst und Strg + c drückst
2. Drücke Strg + v
3. Bennene die neu erstellte Datei passend um (z.b. en.yml, es.yml), die Endung muss aber .yml sein! Der Name entscheidet wie die Sprache ingame angezeigt wird
4. Passe die Nachrichten in der Datei passend zur Sprache an (siehe [hier](https://skiftstar.github.io/curry-server-plugins/#nachrichten-anpassen) falls du nicht weisst wie)

# ***Plugins***

## **Language Helper API**

> ### Beschreibung
Diese kleine API ermöglicht es is mir ohne copy-pasting und sehr einfach Multi-Language Support in die Plugins zu bringen

> ### Source Code
Der Spigot Code kann [hier gefunden werden](https://github.com/Skiftstar/SpigotLangaugeHelper)
Der Bungee Code kann [hier gefunden werden](https://github.com/Skiftstar/WaterfallLanguageHelper)

## **Command Helper API**

> ### Beschreibung
Für kleine Commands verwende ich meine eigene Command Helper API, weshalb manche Commands im SourceCode vlt. anders aussehen, als man es gewohnt ist.

> ### Source Code und Documentation
Der Code kann [hier gefunden werden](https://github.com/Skiftstar/SpigotCommandHelper/blob/main/src/main/java/Kyu/SCommand.java)
Die Documentation kann [hier gefunden werden](https://github.com/Skiftstar/SpigotCommandHelper/blob/main/README.md)

## **Bungee Generelle Commands**
> ### Beschreibung
Hier sind einfach Commands aufgelistet, die nich wirklich als eigenes großes Feature gelten

> ### Source-Code
Kommt noch

> ### Known Issues
- None

> ### Nötige Permissions
- `bcore.reload` -> Erlaubt Benutzung des /breload Commands

> ### Commands
- `/breload` -> Relaoded the Config und die Language Files vom Plugin, wodurch man den Proxy nich neustarten muss

## **Teamchat**

> ### Beschreibung
Erlaubt es dem Team, unabhängig von wer auf welchem Server ist, zu kommunizieren, ohne dass normale Member diese Nachrichten sehen können

> ### Souce-Code
Klasse des Commands ist [hier zu finden](https://github.com/Skiftstar/curry-server-plugins/blob/gh-pages/ServerCore_Bungee/TeamchatCommand.java)

> ### Known Issues
- None

> ### Nötige Permissions
- `bcore.teamchat` -> Senden und Empfangen von TeamChat nachrichten

> ### Commands
- `/tc [Nachricht]` -> Senden einer Nachricht in den Teamchat

> ### Download
Plugin ist Teil von [ServerCore für Bungee](https://skiftstar.github.io/curry-server-plugins/#servercore-for-bungee)

## **BungeeDMs**

> ### Beschreibung
Erlaubt es Spielern einem anderen Spieler, der auch auf einem anderen Server innerhalb des Netzwerkes sein kann, eine private Nachricht zu schicken

> ### Source-Code
Klasse des Commands ist [hier zu finden](https://github.com/Skiftstar/curry-server-plugins/blob/gh-pages/ServerCore_Bungee/DMCommand.java)

> ### Known Issues
- TabCompleter funktioniert nur bei /dm, da /msg und /tell Minecraft/Spigot commands sind und der TabCompleter da nich überschrieben wird

> ### Nötige Permissions
- `bcore.dm` -> Erlaubt Benutzung des /dm Commands

> ### Commands
- `/dm [Spieler] [Nachricht]` -> Schickt eine Private Nachricht an den angegebenen Spieler
  - Aliases: `/tell` `/msg`

> ### Download
Plugin ist Teil von [ServerCore für Bungee](https://skiftstar.github.io/curry-server-plugins/#servercore-for-bungee)

## **GlobalChat**

> ### Beschreibung
Erlaubt es Spielern den Serverübergreifenden Chat zu sehen und auch darin zu schreiben

> ### Source-Code
Klasse des Commands ist [hier zu finden](https://github.com/Skiftstar/curry-server-plugins/blob/gh-pages/ServerCore_Bungee/GlobalChatCommand.java)

> ### Known Issues
- Spieler mit der Permission "*" können den Globat Chat nicht deaktivieren

> ### Nötige Permissions
- `bcore.global` -> Erlaubt es den /g Command zu verwenden
- `bcore.globalenabled` -> Bestimmt ob ein Spieler den Global Chat sehen kann/Darin schreiben kann
  - Permission muss *nicht* manuell gesetzt werden! Sie wird über den /g Command angepasst

> ### Commands
- `/g (Nachricht)` -> Schickt eine Nachricht in den Globalen Chat *ODER* aktiviert/deaktiviert den Globalen Chat, falls keine Nachricht eingegeben wird
  - Aliases: `/global`

> ### Download
Plugin ist Teil von [ServerCore für Bungee](https://skiftstar.github.io/curry-server-plugins/#servercore-for-bungee)


## **LobbyCore**

> ### Beschreibung
Core Plugin für die Lobby, enthält Features wie: LobbySpawn, Protection für ins Void fallen, Server Kompass, Deaktivierung von Schaden, Hunger, etc.

> ### Source-Code
Alle Klassen des Plugins sind [hier zu finden](https://github.com/Skiftstar/curry-server-plugins/tree/gh-pages/LobbyCore)

> ### Known Issues
- Wenn du das Einstellen der Config hart verkackts, kanns sein, dass se sich selber resetted -> Backup vor'm testen!

> ### Permissions
- `lobbycore.bypassGamemode` -> GameMode wird nicht auf den eingestellen Gamemode on Join gesetzt
- `lobbycore.bypassTeleport` -> Du wirst nicht on Join zum Lobby Spawn teleportier
- `lobbycore.bypassClear` -> Dein Inventar wird nicht on Join geleert (Du bekommst den Kompass mit der Permission dann aber auch nicht mehr!)
- `lobbycore.bypassCancelDropPickup` -> Du kannst wieder Items droppen/aufsammeln
- `lobbycore.toggleReset` -> Du kannst den /toggleReset Command verwenden
- `lobbycore.setSpawn` -> Du kannst den /setSpawn Command verwenden
- `lobbycore.spawn` -> Du kannst den /spawn Command verwenden
- `lobbycore.bypass` -> Gibt dir alle bypass permissions
- `lobbycore.reload` -> Du kannst den /lReload Command verwenden
- `lobbycore.servers` -> Du kannst den /servers Command verwenden
- `lobbycore.*` -> Gibt dir alle Permissions für das Plugin

> ### Commands
- `/toggleReset` -> Schaltet die Void Protection an/aus (Falls an werden Spieler sobald sie auf y=-66 sind wieder zum LobbySpawn teleportiert)
  - aliases: `/void`, `/tr`
- `/setSpawn` -> Setzt den LobbySpawn auf deine Position (Auch Blickrichtung!)
- `/spawn` -> Teleportiert dich zum LobbySpawn
- `/lReload` -> Reloaded die Plugin Config und die Language Files wodurch man nich den kompletten Server reloaden muss
- `/servers` -> Zeigt das ServerAuswahl GUI

> ### Config einstellen
Hier erklär ich kurz wie man die Config richtig einstellt
*Wichtig*: Vor Config änderungen: Server stopppen, dann config anpassen, dann Server neu starten!

> - `defaultGamemode` -> bestimmt in welchen GameMode der Spieler onJoin gesetzt wird
  - Mögliche Values: `ADVENTURE`, `CREATIVE`, `SURVIVAL`, `SPECTATOR`
- `resetOnVoid` -> Void Protection, *nicht über config file*, sondern über ingame Befehl ändern!
- `serverInterfaceTitle` -> Name vom Inventar, das der Kompass aufmacht
  - Color Codes sind möglich
- `spawnLoc` -> Position vom LobbySpawn, *nicht über config file anpassen!*
  - Wird erst gesetzt, sobald der Ingame Befehl 1mal ausgeführt wurde und der Server neugestartet wurde
- `servers` -> Die Liste an Servern, die in den Server Kompass gepackt wird

Beispiel für eine servers Sektion (# sind Kommentare):
```
servers:
  # Name des Servers auf dem Bungee Netzwerk (der Name hier entscheidet auf welchen Server der Spieler geschickt wird!)
  Survival: 
  
    # Name des Items im Inventar, Farbe is möglich
    name: "Survival Server"
    
    # Beschreibung des Items im Inventar, Farbe is mögloch
    description: 
    - Zeile 1
    - '&aKuck mal, Farbe!'
    
    # Art von Block im Inventar, mögliche Blöcke sind hier zu finden: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html
    block: GRASS_BLOCK 
    
    # Slot im Inventar, open links is Slot 0
    slot: 10 
  Lobby:
    name: "Tooollleeeeer Server"
    description:
    - '&dLilaaaaa'
    - '&bBLAUUUU'
    - ''
    - Ueber mir sollte eine leere Zeile sein
    block: NOTE_BLOCK
    slot: 20
```

> ### Download
Der download kann [hier gefunden werden](https://skiftstar.github.io/curry-server-plugins/#lobby-core)

## **PixEssentials**

Dieses Plugin ist wie ein Essentials Ersatz, der nicht komplett Feature bloated ist

> ### Source-Code
Kein Source-Code

> ### Known Issues
None

> ### Nötige Permissions
- `core.essentials.setSpawn` -> Erlaubt den /setSpawn Command zu verwenden
- `core.essentials.spawn` -> Erlaubt den /spawn Command zu verwenden
- `core.essentials.reload` -> Erlaubt den /eReload Command zu verwenden
- `core.essentials.sethome` -> Erlaubt den /setHome Command zu vewenden
- `core.essentials.home` -> Erlaubt den /home Command zu verwenden
- `core.essentials.warp` -> Erlaubt den /warp und /listwarps Command zu verwenden
- `core.essentials.warp.admin` -> Erlaubt den /delwarp und /setwarp Command zu verwenden
- `core.essentials.tpa` -> Erlaubt den /tpa Command zu verwenden
- `core.essentials.tpaccept` -> Erlaubt den /tpaccept Command zu verwenden
- `core.essentials.smite` -> Erlaubt den /smite Command zu verwenden
- `core.essentials.enchant` -> Erlaubt den /enchant Command zu verwenden


> ### Commands


## **ServerCore**

> ### Beschreibung
Dieses Plugin sollte auf jeden Server im Netzwerk kommen, es stellt die wichtigsten Commands und Features bereit

> ### Source-Code
Klasse des Commands ist [hier zu finden](https://github.com/Skiftstar/curry-server-plugins/tree/gh-pages/ServerCore)

> ### Known Issues
- /invsee zeigt Offhand nicht

> ### Nötige Permissions
- `core.gamemode` -> Erlaubt es den /gamemode Command zu verwenden
- `core.gamemode.other` -> Erlaubt es den Gamemode anderer Spieler zu ändern
    - Gibt dem Spieler automatisch auch die `core.gamemode` Permission
- `core.teleport` -> Erlaubt es den /teleport Command zu verwenden
- `core.teleport.others` -> Erlaubt es andere Spieler zu teleportier
    - Gibt dem Spieler automatisch auch die `core.teleport` Permission
- `core.colorchat` -> Erlaubt es dem Spieler im Lokalen Chat in Farbe zu schreiben
- `core.*` -> Gibt alle obigen Permissions
- `core.ignoreLuckDenial` -> Umgeht den LuckPerms Weight check
    - ist NICHT in `core.*` enthalten
- `core.reload` -> Erlaubt es den /sReload Command zu verwenden
- `core.essentials.vanish` -> Erlaubt den /vanish Command zu verwenden
- `core.essentials.invsee` -> Erlaubt den /invsee Command zu verwenden
- `core.essentials.ping` -> Erlaubt den /ping Command zu verwenden

> ### Commands
- `/gamemode [GAMEMODE] (Spieler)` -> Setzt den Spielmodus des Spielers zu dem angegebenen Modus
    - Aliases: `/gm`
    - Es sind sowohl Zahlen (0 - 3) oder Namen (creative, survival, spectator, adventure) möglich
- `/teleport [Spieler/Koordinaten] (Spieler/Koordinaten)` -> Teleportiert sich selbst (oder einen anderen Spieler) zu den angegebenen Koordinaten oder dem angegebenen Spieler
    - Aliases: `/tp`
- `/discord` -> Gibt den Discord Link aus
  - Aliases: `/dc`
- `/sReload` -> Reloaded die Plugin Config und die Language Files wodurch man nicht den kompletten Server reloaden muss
- `/vanish` -> Macht dich für andere Spieler unsichtbar, entfernt dich von TAB und sendet eine Fake-Leave Nachricht
- `/invsee [Spieler]` -> Erlaubt es dir das Inventar eines Spielers zu sehen und Items zu entfernen/hinzuzufügen
- `/ping` -> Zeigt dir deinen Ping zum Server

> ### Andere Features
- Custom Join/Leave Nachrichten
    - Können in den Sprachdateien für jede Sprache angepasst werden
- Chat Formatting
    - Template kann in den Sprachdateien für jede Sprache angepasst werden
- LuckPerms Weight Check
    - Spieler mit Zugriff auf LuckPerms können User/Gruppen mit einer höheren Weight als die Gruppe des Editierenden nicht editieren
        - Lässt sich mit Permissions `core.ignoreLuckDenial` bzw. `*` umgehen
- Scoreboard
  - Kann in den Sprachdateien für jede Sprache angepasst werden

> ### Config
Kommentare = #

```
#Der Prefix vor Nachrichten von dem Plugin
chatPrefix: "&6[PixelCore]"

#Der Name im Scoreboard Titel
serverName: "&aPixel&cWar"

#Nach wie vielen Sekunden das Scoreboard aktualisiert werden soll
ScoreboardRefreshDelay: 5

#Welche Zeichenfolge für die Ranganzeige im Scoreboard vom Gruppenprefix ignoriert werden soll
filterFromPrefixForScoreboard: "|"

#Der Discord link für /discord
discordLink: "https://discord.gg/Fce224eSzf"
```

> ### Download
Der download kann [hier gefunden werden](https://skiftstar.github.io/curry-server-plugins/#server-core)


# ***Download Links***

## **ServerCore for Bungee**

> ### Beschreibung
Beinhaltet [TeamChat](https://skiftstar.github.io/curry-server-plugins/#teamchat) [BungeeDMs](https://skiftstar.github.io/curry-server-plugins/#bungeedms) und [GlobalChat](https://skiftstar.github.io/curry-server-plugins/#globalchat)

> ### Source Code
Der Code aller Klassen des Plugins kann [hier gefunden werden](https://github.com/Skiftstar/curry-server-plugins/tree/gh-pages/ServerCore_Bungee)

> ### Link
Die .jar kann [hier heruntergeladen werden](https://github.com/Skiftstar/curry-server-plugins/releases/tag/BungeeCord)

## **Lobby Core**

> ### Beschreibung
siehe oben

> ### Source Code
siehe oben

> ### Link
die .jar kann [hier heruntergeladen werden](https://github.com/Skiftstar/curry-server-plugins/releases/tag/LobbyCore)

## **Server Core**

> ### Beschreibung
siehe oben

> ### Source Code
siehe oben

> ### Link
die .jar kann [hier heruntergeladen werden](https://github.com/Skiftstar/curry-server-plugins/releases/tag/ServerCore)
