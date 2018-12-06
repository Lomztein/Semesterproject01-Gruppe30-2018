/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

import depressionsspillet.worldofzuul.characters.HostileNPC;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Attacker;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.combat.DamageResistance;
import depressionsspillet.worldofzuul.combat.DamageType;
import depressionsspillet.worldofzuul.combat.Health;

/**
 *
 * @author Ryge
 */
public class RoomList {

    
    //This class was made to combat the amount of bloat in the game-class. 
    //It contains all the rooms, the items and entities inside the rooms, and exits.

    static Room start, magicForrest, vendor, animals, thaiHooker, sleepover, fridayBar, stripClub, kfc, shrek, allotment, movie, drugs, gate, boss, suprise;

    public static void listRooms() {

        // The individual room variables are populated with their appropiate Room objects.
        start = new Room("You leave Spilmester Martin, and enter the huge magical forest, with trees seemingly extending into the skies.", "start");
        magicForrest = new Room("You are now in the magic forest, who knows what will happen.", "magicForrest");
        vendor = new Room("You have visited the blackboard vendor, a replacement of blackboard is currently in the works, in the meantime however, \n please feel free to browse the vendor's wares.", "vendor");
        animals = new Room("You go deeper into the forest and find yourself in a completely white room filled with puppies and kittens.", "animals");
        thaiHooker = new Room("A beuatiful asian woman approaches you and asks what you are doing tonight. She seems interresting but a beautiful woman \n has never approached you before... Could it be a trap?", "thaiHooker");
        sleepover = new Room("You find yourself at your best friends house in your pajamas with icecream. Your best friends invites you inside for a sleepover.", "sleepover");
        fridayBar = new Room("Wuhuu it is friday on SDU and you suddenly feel your spirite soaring and you feel like getting smashed and so you do... You feel \n great.", "fridayBar");
        stripClub = new Room("As you continued through the forrest you notices a couple of flikering light as you move closer you see a sign saying: \n 'Gentleman's club. Free tonight' You enter, look around, start smiling slyly and have a great time. Your mum would be disappointed", "stripClub");
        kfc = new Room("Suddenly in your path you see a familiar red sign with tree white letters. It reads: KFC, and you are overjoyed. You enter and when \n you tell the cashier about your amazing \n journey. She decides to give you free food for your trip and warns you about continuing \n east becuase a dangerous and mysterious creature lurks in the swamp.", "kfc");
        shrek = new Room("You defied the warnings of the nice KFC lady and walked onwards to the east. The forest soon ends and a dank swamp emerges. \n Carefully you explore the area and come across \n a small wooden shack. As you are about to enter, a rumbling voice appears behinds you \n ' ' After turning around you realise you have entered the domain of the one and only Shrek. In an adrenaline induced panic you try \n to escape, but you are easily caught, and as you are dragged inside the raggedy shack, Shreks whispers softly 'it's all ogre now'. The rest of this encounter is best described as a deep scar on your soul, and should never again be spoken off.", "shrek");
        allotment = new Room("As you continue walking the forest gets brighter as more and more light slips through the treetops. Flowers start to appear and as \n you follow them you find a small cosycabin. When you are just about to knock on the door to ask for directions, when you \n a familiar eerie sound. *heavy forced inhaling* *heavy forced exhaling* you decide to scout out the house for inhabitants, before \n trying to enter. Walking around to the backyard, you spot a figure dressed in black armor, wearing a black mask and cape,  \n holding a watering can. It is the retired Darth Vader!", "allotment");
        movie = new Room("You discover a room with a big couch, floffy teddybears and a couple of friends to snuggle. So you dive into the pillows on the couch \n and rest for a while.", "movie");
        drugs = new Room("In a twisted turn of events, you stumble upon a bald russian man selling some sort of homemade white powder. You assume this is heroin, \n and it is verified by the man with a deep, emotionless 'Da. Krokodil'. You feel slightly eerie and disturbed. ", "drugs");
        gate = new Room("This is a big impenetrable, unavoidable, indomitable, completely daunting and locked gate. You will need some kind of key to get through.", "gate");
        boss = new Room("bossbattle", "boss");
        suprise = new Room("In a heroic and almost impossible turn of events you have defeated the despicable Erikthulu/Martin and entered through the last door, \n behind which all your friends have been watching your valiant fight with eagerness and solemn pride.  \n They all congratulate you on completeing such a feat of strength and cheer you name all the while continually mentioning how proud \n of you the are, in addition to how much they value your friendship", "suprise");

        // Exits for are declared.
        start.setExit("south", magicForrest);

        // Exits for magicForrest are declared.
        magicForrest.setExit("south", sleepover);
        magicForrest.setExit("east", vendor);
        magicForrest.setExit("west", thaiHooker);

        // Exits for vendor are declared.
        vendor.setExit("south", stripClub);
        vendor.setExit("east", animals);
        vendor.setExit("west", magicForrest);
        vendor.setHappiness(0);

        // You know the drill by now.
        animals.setExit("west", vendor);
        animals.setHappiness(15);

        thaiHooker.setExit("north", movie);
        thaiHooker.setExit("south", fridayBar);
        thaiHooker.setExit("east", magicForrest);
        thaiHooker.setExit("west", drugs);
        thaiHooker.setHappiness(15);

        sleepover.setExit("north", magicForrest);
        sleepover.setExit("south", gate);
        sleepover.setExit("east", stripClub);
        sleepover.setExit("west", fridayBar);
        sleepover.setHappiness(15);

        fridayBar.setExit("north", thaiHooker);
        fridayBar.setExit("east", sleepover);
        fridayBar.setHappiness(10);

        stripClub.setExit("north", vendor);
        stripClub.setExit("east", kfc);
        stripClub.setExit("west", sleepover);
        stripClub.setHappiness(10);

        kfc.setExit("east", shrek);
        kfc.setExit("west", stripClub);
        kfc.setHappiness(15);

        shrek.setExit("west", kfc);

        allotment.setExit("south", drugs);
        allotment.setExit("east", movie);
        allotment.setHappiness(10);

        movie.setExit("south", thaiHooker);
        movie.setExit("west", allotment);
        movie.setHappiness(10);

        drugs.setExit("north", allotment);
        drugs.setExit("east", thaiHooker);

        gate.setExit("north", sleepover);
        gate.setExit("south", boss, false);

        boss.setExit("south", suprise);

        //A new NPC, the boss Erikthulhu, is created. 
        HostileNPC erikthulhu = new HostileNPC("Erikthulhu", "Your final opponent. The physical manifistation of your depression, and the evil it brings to your life.", boss, true, new Health(666d).withResistances(
                new DamageResistance[]{
                    new DamageResistance(DamageType.BLUNT, "is impervious to blunt force trauma, he is simply too great.", 0),
                    new DamageResistance(DamageType.SLASH, "has too thick skin to penetrate, your slash simply glances off with a small papercut-like wound left, doing %.2f damage.", 0.1),
                    new DamageResistance(DamageType.DAB, "doesn't care. You cannot merely dab upon all your problems. Please actually try to solve your problems.", 0),
                    new DamageResistance(DamageType.MENTAL, "is distraught by your comfidence and self-worth, and so he takes an impressive %.2f damage.", 2),}),
                new Attack(DamageType.DAB, 10d, "Intense Dab", "Erikthulhu uses your own tactics against you. You cannot keep fighting yourself like this."),
                new Attack(DamageType.FIRE, 15d, "Firebreath", "A massive storm of fire. Bricks will be shat."),
                new Attack(DamageType.SUNONASTICK, 0, "Sun on a Stick", "Arguably the most useful of all weapons."),
                new Attack(DamageType.WATER, 2d, "Water Gun", "A soft, rather refreshing spray of water originating from a toy gun."),
                new Attack(DamageType.BLUNT, 10d, "Vigerous Punch", "An intense punch, using raw strength alone."),
                new Attack(DamageType.MENTAL, 10d, "Insult", "An insult upon your appearance, talents and skills all wowen together in a beautiful euphony of wordsmithing.")
        );
        erikthulhu.getHealth().onTakeDamage.add(x -> {
            if (x.getDamage().getDamageType() == DamageType.DAB) {
                System.out.println("Erikthulhu will not allow you to outdab him, he retaliates with a furious dab in addition to his regular counter-attack.");
                Damage retaliation = new Damage((Attacker) x.getDamage().getReciever(), (Damagable) x.getDamage().getAttacker(), DamageType.DAB, 100);
                retaliation.doDamage();
            }
        });
        boss.addEntityToRoom(erikthulhu);

        // the currentRoom, which represents the room our player is currently in, is assigned the "outside" room.
        // In other words, the game begins with us outside.
        
        //Items added to the different rooms:
        magicForrest.addItem(new ConsumableItem("apple", "An apple of particularly moist texture.", 100, 42, 5));
        magicForrest.addItem(new ConsumableItem("beer", "The nectar of God himself; The holiest of drinks.", 200, 10, -1));
    }

}
