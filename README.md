# MineTania.net Developer codestyle and workflow guide

## Codestyle

### Format

#### Indentation style

Use the 1TBS ( https://en.wikipedia.org/wiki/Indentation_style#Variant:_1TBS_(OTBS) ) style.

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

#### Curly Braces

Use Curly Braces ({, }) whenever you can, dont remove them event if its possible.

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

#### Whitespaces

Please use whitespaces after structure keywords or before curly braces, but *never* use more than one whitespace.

**Bad Example:**

```java
void doSomething ( args ){
  if( expression ){
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

### Fields

#### Do not use public fields

Please do not use public fields, instead use getters and setters. Many of our plugins depend on other plugins, when you decide that you dont want to check something when getting a field, you need to add getters and setters, then every plugin using yours needs to be changed.

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

#### Follow oracle java naming conventions

Please follow oracle's java naming conventions, e.g. use *UpperCamelCase* for class names, use *lowercase* for package names, *camelCase* for non-constants and *UPPERCASE* for constants.

### Spigot

#### Plugin YML

**Please** read https://www.spigotmc.org/wiki/plugin-yml/ to understand what you can do with spigot's plugin.yml. Many YouTube "minecraft plugin development" tutorials dont dive into this topic, but please at least define command permissions, command aliases, command descriptions, and permission informations in every of your plugin(/bungee).yml's.

#### Know what you are doing

A simple example is spigot's command executor. Nearly every spigot plugin developer ignores spigot's functionality. If you didn't know, spigot's command executor behaves different when you return false / true, please check this out.
