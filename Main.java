import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Appearance {
    String appearanceType;

    public Appearance(String appearanceType) {
        this.appearanceType = appearanceType;
    }
}

class Ability {
    String abilityName;

    public Ability(String abilityName) {
        this.abilityName = abilityName;
    }
}

class Equipment {
    String equipmentType;

    public Equipment(String equipmentType) {
        this.equipmentType = equipmentType;
    }
}

class Attribute {
    int strength;
    int agility;
    int intelligence;

    public Attribute(int strength, int agility, int intelligence) {
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }
}

class Character {
    String name;
    Appearance appearance;
    List<Ability> abilities;
    List<Equipment> equipment;
    Attribute attributes;

    public Character(String name, Appearance appearance, List<Ability> abilities, List<Equipment> equipment, Attribute attributes) {
        this.name = name;
        this.appearance = appearance;
        this.abilities = abilities;
        this.equipment = equipment;
        this.attributes = attributes;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Appearance: " + appearance.appearanceType);
        System.out.println("Abilities:");
        for (Ability ability : abilities) {
            System.out.println("- " + ability.abilityName);
        }
        System.out.println("Equipment:");
        for (Equipment equipment : equipment) {
            System.out.println("- " + equipment.equipmentType);
        }
        System.out.println("Attributes:");
        System.out.println("- Strength: " + attributes.strength);
        System.out.println("- Agility: " + attributes.agility);
        System.out.println("- Intelligence: " + attributes.intelligence);
    }
}

abstract class CharacterFactory {
    abstract Character createCharacter(String name);
}

class WarriorFactory extends CharacterFactory {
    @Override
    Character createCharacter(String name) {
        List<Ability> warriorAbilities = new ArrayList<>();
        warriorAbilities.add(new Ability("Slash"));
        warriorAbilities.add(new Ability("Block"));

        List<Equipment> warriorEquipment = new ArrayList<>();
        warriorEquipment.add(new Equipment("Sword"));
        warriorEquipment.add(new Equipment("Shield"));

        Attribute warriorAttributes = new Attribute(10, 5, 3);

        return new Character(name, new Appearance("Warrior Appearance"), warriorAbilities, warriorEquipment, warriorAttributes);
    }
}

class MageFactory extends CharacterFactory {
    @Override
    Character createCharacter(String name) {
        List<Ability> mageAbilities = new ArrayList<>();
        mageAbilities.add(new Ability("Fireball"));
        mageAbilities.add(new Ability("Teleport"));

        List<Equipment> mageEquipment = new ArrayList<>();
        mageEquipment.add(new Equipment("Staff"));
        mageEquipment.add(new Equipment("Robe"));

        Attribute mageAttributes = new Attribute(3, 5, 10);

        return new Character(name, new Appearance("Mage Appearance"), mageAbilities, mageEquipment, mageAttributes);
    }
}

class ArcherFactory extends CharacterFactory {
    @Override
    Character createCharacter(String name) {
        List<Ability> archerAbilities = new ArrayList<>();
        archerAbilities.add(new Ability("Shoot"));
        archerAbilities.add(new Ability("Dodge"));

        List<Equipment> archerEquipment = new ArrayList<>();
        archerEquipment.add(new Equipment("Bow"));
        archerEquipment.add(new Equipment("Leather Armor"));

        Attribute archerAttributes = new Attribute(5, 10, 3);

        return new Character(name, new Appearance("Archer Appearance"), archerAbilities, archerEquipment, archerAttributes);
    }
}

class CharacterCreator {
    private CharacterFactory factory;

    void setFactory(CharacterFactory factory) {
        this.factory = factory;
    }

    Character createCharacter(String name) {
        return factory.createCharacter(name);
    }
}

public class Main {
    public static void main(String[] args) {
        CharacterCreator characterCreator = new CharacterCreator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Character Creator!");

        System.out.println("Choose your character class:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Archer");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        Character character = null;
        switch (choice) {
            case 1:
                characterCreator.setFactory(new WarriorFactory());
                break;
            case 2:
                characterCreator.setFactory(new MageFactory());
                break;
            case 3:
                characterCreator.setFactory(new ArcherFactory());
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                return;
        }

        System.out.print("Enter your character's name: ");
        String name = scanner.next();

        character = characterCreator.createCharacter(name);

        if (character != null) {
            System.out.println("Character Information:");
            character.displayInfo();
        } else {
            System.out.println("Failed to create character.");
        }
    }
}
