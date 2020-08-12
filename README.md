# MineTania.net Developer codestyle and workflow guide

## Format

### Indentation style

Use the 1TBS (https://en.wikipedia.org/wiki/Indentation_style#Variant:_1TBS_(OTBS)) style.

**Bad Example:**

```java
void doSomething(args) 
{
  if (expression) 
  {
    //execute code
  }
  else 
  {
    //throw exception
  }
}
```

**Good Example:**

```java
void doSomething(args) {
  if (expression) {
    //execute code;
  } else {
    //throw exception;
  }
}
```

### Curly Braces

Use Curly Braces (`{`, `}`) whenever you can, dont remove them event if its possible.

**Bad Example:**

```java
void doSomething(args) {
  if (expression) //execute onliner;
  for (var x : iterable) //execute oneliner;
}
```

**Good Example:**

```java
void doSomething(args) {
  if (expression) { 
    //execute onliner;
  }
  for (var x : iterable) {
    //execute oneliner;
  }
}
```

### Whitespaces

Please use whitespaces after structure keywords (`if`, `else`, `while`, `for`, `do`, `try`, etc...) or before curly braces, but *never* use two ore more whitespaces behind each other.

**Bad Example:**

```java
void doSomething ( args ){
  if( expression )  {
    //do something;
  }
}
```

**Good Example:**

```java
void doSomething(args) {
  if (expression) {
    //do something;
  }
}
```

## Naming

### At first, follow oracle's java naming conventions

Please follow oracle's java naming conventions, use *UpperCamelCase* for class names, use *lowercase* for package names, *camelCase* for variables & methods and *UPPERCASE* for constants.

### Do not use public fields

Please do not use public fields, instead use getters and setters. Many of our plugins depend on other plugins, when you decide that you want to check something when getting a field, you need to add getters and setters, and then every plugin using yours needs to be changed.

**Bad Example:**

```java
class AdventurePlayer {
  public UUID playerUuid;
}
```

**Good Example:**

```java
class AdventurePlayer {
  private UUID playerUuid;
  
  public UUID getPlayerUuid() {
    //check something;
    return playerUuid;
  }
}
```

### Do not use booleans

Please do not use booleans, instead, use enums.

**Bad Example:**

```java
class Guild {
  boolean needsInvitation;
}
```

**Good Example:**

```java
class Guild {
  
  enum State {
    OPEN_FOR_ALL,
    INVITATION_ONLY
  }
  
  private State state;
  
}
```

### Do not use magic numbers

Please do not use magic numbers, instead, create constants. Allowed numbers in code are `-1`, `0` and `1`.

**Bad example:**

```java
adventurePlayer.addMoney((todayOnlineTime / 60) * 20);
```

**Good example:**

```java
adventurePlayer.addMoney((todayOnlineTime / TIME_DIVISOR) * COINS_PER_HOUR);
```

### Do not use acronyms

Instead of using acronyms, use (a) descriptive word(s).

**Bad example:**

```java
if (sp < MAP) {
  p.sendMessage(PTL);
}
```

**Good example:**

```java
if (selectedPrice < MIN_AUCTION_PRICE) {
  player.sendMessage(PRICE_TOO_LOW);
}
```

### Do not use plaintext in your logical code

Instead of using plaintext, for example use enums or constants.

**Bad example:**

```java
if (expression) {
  player.sendMessage("You've won something in the case opening!");
}
```

**Good example:**

```java
if (expression) {
  player.sendMessage(CASE_OPENING_WON);
}
```

## Spigot

### Plugin YML

**Please** read https://www.spigotmc.org/wiki/plugin-yml/ to understand what you can do with spigot's plugin.yml. Many YouTube "minecraft plugin development
*tutorials*" dont dive into this topic, but please define at least command permissions, command aliases, command descriptions, and permission informations in every of your plugin(/bungee).yml's.

### Know what you can do with spigot

A simple example is spigot's command executor. Nearly every spigot plugin developer ignores spigot's functionality. If you didn't know, spigot's command executor behaves different when you return false / true, please check out the spigot wiki.

## Git

### Stay updated (and update)

Everytime you start¹ and end² a "programming session" please ¹pull, ²commit and push your changes. In case of anything prevents you from pushing the code in the future, that other developers can continue working at your project.

### Provide Information

Please provide information about your plugin for other developers, meaning, please add a readme and descripe how to use your plugin as an api.
