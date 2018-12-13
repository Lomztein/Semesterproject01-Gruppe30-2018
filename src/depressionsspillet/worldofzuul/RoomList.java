package depressionsspillet.worldofzuul;

import depressionsspillet.worldofzuul.characters.HostileNPC;
import depressionsspillet.worldofzuul.characters.InteractableNPC;
import depressionsspillet.worldofzuul.characters.NPC;
import depressionsspillet.worldofzuul.characters.Player;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.DamageResistance;
import depressionsspillet.worldofzuul.combat.DamageType;
import depressionsspillet.worldofzuul.combat.DamagedEvent;
import depressionsspillet.worldofzuul.combat.Health;
import depressionsspillet.worldofzuul.interaction.InteractableObject;
import depressionsspillet.worldofzuul.interaction.Interaction;
import depressionsspillet.worldofzuul.observables.Observer;

public class RoomList {

    //This class was made to combat the amount of bloat in the game-class. 
    //It contains all the rooms, the items and entities inside the rooms, and exits.
    static Room start, magicForrest, vendor, animals, thaiHooker, campfire, fridayBar, stripClub, kfc, shrek, allotment, movie, drugs, gate, boss, suprise;

    public static void listRooms() {

        // The individual room variables are populated with their appropiate Room objects.
        start = new Room("Spilmester Martin:\nHaha, what an ugly name, and btw the listed character types were all sold out, so you got a pathetic smurf.\nNow please you must defeat Erikthulu. Walk around using WASD and SPACE to enter a new room. \nRemember you must acquire enough happiness from the world, before you can win against Erikthulu!", "start");
        magicForrest = new Room("You are now in the magic forest, who knows what will happen.", "magicForrest");
        vendor = new Room("You have visited the blackboard vendor, a replacement of blackboard is currently in the works, in the meantime however, please feel free to browse the vendor's wares.", "vendor");
        animals = new Room("You go deeper into the forest and find yourself in a completely white room filled with puppies and kittens. \n and just as you start to relax you see a big dangerous direwolf standing in the middle", "animals");
        thaiHooker = new Room("A beuatiful asian woman approaches you and asks what you are doing tonight. \nShe seems interresting but a beautiful woman has never approached you before... Could it be a trap?", "thaiHooker");
        campfire = new Room("As you walk through the forest an opening suddenly appears. Here you find three of your best friends, sitting around a campfire, waiting for you...", "campfire");
        fridayBar = new Room("Wuhuu it is friday at Nedenunder and you suddenly feel your spirite soaring and you feel like getting smashed and so you do... You feel great.", "fridayBar");
        stripClub = new Room("As you continued through the forrest you notices a couple of flikering light as you move closer you see a sign saying: 'Gentleman's club. Free tonight' \nYou enter, look around, start smiling slyly and have a great time. Your mum would be disappointed", "stripClub");
        kfc = new Room("Suddenly in your path you see a familiar red sign: KFC, and you are overjoyed. \nYou go nearer and when you tell the cashier about your amazing journey. \nShe decides to give you free food for your trip and warns you about continuing east becuase a dangerous and mysterious creature lurks in the swamp.", "kfc");
        shrek = new Room("You defied the warnings of the nice KFC lady and walked onwards to the east. The forest soon ends and a dank swamp emerges. As you go nearer, a rumbling voice appears behinds you 'This is my swamp!'. \nAfter turning around you realise you have entered the domain of the one and only Shrek. \nIn an adrenaline induced panic you try to escape, but you are easily caught, and as you are dragged inside the swamp, Shreks whispers softly 'it's all ogre now'. \nThe rest of this encounter is best described as a deep scar on your soul, and should never again be spoken off.", "shrek");
        allotment = new Room("As you continue walking the forest gets brighter as more and more light slips through the treetops. \nFlowers start to appear and as you follow them you find a small cosycabin. You are just about to knock on the door to ask for directions, when you hear a familiar eerie sound. \n*heavy forced inhaling* *heavy forced exhaling* \nyou decide to scout out the house for inhabitants, before trying to enter. \nWalking around to the backyard, you spot a figure dressed in black armor, wearing a black mask and cape, holding a watering can. It is the retired Darth Vader!", "allotment");
        movie = new Room("You discover a place in the forest with a large TV, comfortable chairs and snacks ready for you to get cozy and immerse yourself in a good movie.", "movie");
        drugs = new Room("In a twisted turn of events, you stumble upon a bald russian man selling some sort of homemade white powder. \nYou assume this is heroin and it is verified by the man with a deep, emotionless 'Da. Krokodil'. You feel slightly eerie and disturbed. ", "drugs");
        gate = new Room("This is a big impenetrable, unavoidable, indomitable, completely daunting and locked gate. Could Erikthulu be behind this gate? \n You think back at what Spilmester Martin told you 'Remember you must acquire enough happiness from the world, before you can win against Erikthulu!'.", "gate");
        boss = new Room("You go through the big gate and find an opening. \n Suddenly you are surrounded big a thick mist and in the middle of the opening you see to your horror the dangerous Erikthulu.", "boss");
        suprise = new Room("In a heroic and almost impossible turn of events you have defeated the despicable Erikthulu!.\n You find that all your friends have been watching your valiant fight with eagerness and solemn pride.  \n They all congratulate you on completeing such a feat of strength and cheer you name all the while continually mentioning how proud of you the are, in addition to how much they value your friendship", "suprise");

        // Exits for are declared.
        start.setExit("south", magicForrest);

        // Exits for magicForrest are declared.
        magicForrest.setExit("south", campfire);
        magicForrest.setExit("east", vendor);
        magicForrest.setExit("west", thaiHooker);

        magicForrest.addItem(new ConsumableItem("apple", "An apple of particularly moist texture.", 100, 4, 0));

        // Exits for vendor are declared.
        vendor.setExit("south", stripClub);
        vendor.setExit("east", animals);
        vendor.setExit("west", magicForrest);
        vendor.setHappiness(5);

        NPC Vendorboi = new InteractableNPC("Vendorboi", "The friendly purveyor of various liquid substances, that may or may not be of use", vendor,
                new Interaction("Bargain", "Restores Health at the cost of Happiness", x -> {
                    x.addHealth(100d - x.getHealth().getCurrentHealth());
                    x.addHappiness(-5);
                    return "Your health is restored to 100";
                })
        );
        vendor.addEntityToRoom(Vendorboi);

        // You know the drill by now.
        animals.setExit("west", vendor);
        animals.setHappiness(15);

        HostileNPC direwolf = new HostileNPC("Direwolf", "A big wolf looking menacingly at you, yet you cannot tell the nature of its desire.", animals, true, new Health(20).withResistances(new DamageResistance(DamageType.DAB, "is a wild, ferocious animal, it does not care for your unchallenged dankocity.", 0f)),
                new Attack(DamageType.SLASH, 5, "Claw slash", "a violent slash of the wolfs claws."),
                new Attack(DamageType.MENTAL, 2, "Loud bark", "a frightening bark. Though scary, it doesn't do much."),
                new Attack(DamageType.BLUNT, 1, "Headbutt", "a vicious headbutt, however it is in fact more adorable than scary.")
        );
        direwolf.getHealth().onDeath.add(x -> {
            ((Player) x.getDamage().getAttacker()).addAttack(new Attack(DamageType.SLASH, 25, "golden-sword", "a blood-covered golden sword found in the abdominal cavity of a wolf."));
        });

        animals.addEntityToRoom(direwolf);

        thaiHooker.setExit("north", movie);
        thaiHooker.setExit("south", fridayBar);
        thaiHooker.setExit("east", magicForrest);
        thaiHooker.setExit("west", drugs);
        thaiHooker.setHappiness(15);

        thaiHooker.addItem(new ConsumableItem("Lube", "Label says 'Dashing Strawberry flavor'. You feel curious.", 100, 3, 3));

        thaiHooker.addEntityToRoom(new InteractableNPC("Thai-Hooker", "A prostetute of questionable age and gender, yet you are still attracted to them.", thaiHooker,
                new Interaction("Impregnate", "Give in to your carnal lust.", x -> {
                    x.addHappiness(20);
                    x.addAttack(new Attack(DamageType.FIRE, 10, "chlamydia", "a rash in unpleasent locations"));
                    return "You engage in adultery with the hooker, recieving an itchy contagion.";
                }
                )));

        campfire.setExit("north", magicForrest);
        campfire.setExit("south", gate);
        campfire.setExit("east", stripClub);
        campfire.setExit("west", fridayBar);
        campfire.setHappiness(15);

        campfire.addEntityToRoom(new InteractableNPC("Dan", "A childhood buddy, always cheery and positive", campfire,
                new Interaction("Friendly-gesture", "Slap his ass", x -> {
                    x.addHappiness(5);
                    return "Dan is surprised by the slap but laughs and tries to slap you back meanwhile he makes rude but friendlyminded remarks about your appearance and actions. +5 happiness";
                })
        ));

        campfire.addEntityToRoom(new InteractableNPC("Mark", "A friend from school, smart, handsome and probably gay, but everloving by heart", campfire,
                new Interaction("Friendly-gesture", "Slap his ass", x -> {
                    x.addHappiness(-5);
                    return "Mark is not amused by your blatant sexual discrimination - you recieve a light slap, but can sense Mark's deep disappointment. -5 happiness";
                }),
                new Interaction("Compliment", "Compliment Mark's eyebrows and his choice of fashion", x -> {
                    x.addHappiness(5);
                    return "Mark replies with a compliment about how you are polite and sweet and gives you a light pat on the shoulder. +5 happiness";
                })
        ));

        campfire.addEntityToRoom(new InteractableNPC("Mia", "A childhood girl friend with whom you've never really been apart", campfire,
                new Interaction("Harass", "touch tiddies", x -> {
                    x.addHappiness(1);
                    return "Mia is startled by this sudden sexual movement and retaliates with a decisive knock in your bollocks. +1 happiness cuz you got to touch dem tiddies";
                }),
                new Interaction("Question", "Ask why you were never together", x -> {
                    x.addHappiness(10);
                    return "Mia explains that she never thought she were good enough for you, and besides it would be wierd to mess around with someone you've known almost since birth. +10 happiness";
                }),
                new Interaction("Propose", "Ask Mia if she wants to marry you", x -> {
                    x.addHappiness(-10);
                    return "Your being thickskulled and too sudden and blunt has provoked an anxious refusal from Mia. She tells you to grow up and get over your desperation, as she looks away in resentment. -10 happiness";
                })
        ));

        fridayBar.setExit("north", thaiHooker);
        fridayBar.setExit("east", campfire);
        fridayBar.setHappiness(10);

        fridayBar.addEntityToRoom(new InteractableNPC("Dennis", "Bartender", fridayBar,
                new Interaction("Beer", "Sit down at the bar, greet the bartender and order a drink",
                        x -> {
                            x.addHappiness(5);
                            x.addHealth(10);
                            x.addAttack(new Attack(DamageType.MENTAL, 5, "alcohol-barf", "while abhorrantly disgusting, it does not really hurt all that much, except for the pride of course."));
                            return "As you gulp down the cold beer and Dennis asks how you are feeling, you let out a bit of the feelings you've bottled up over the past few years. "
                            + "It feels as though a burden has been lifted from your chest. +5 happiness";
                        })
        ));

        fridayBar.addItem(new ConsumableItem("beer", "The nectar of God himself; The holiest of drinks.", 100, 5, 10));
        fridayBar.addItem(new ConsumableItem("abandoned-beer", "This is exactly what you need.", 100, 5, 0));

        stripClub.setExit("north", vendor);
        stripClub.setExit("east", kfc);
        stripClub.setExit("west", campfire);
        stripClub.setHappiness(10);

        HostileNPC destiny = new HostileNPC("Destiny", "Very skilled in barfights resulted from a long career as an exotic dancer. She dislikes you because you are broke.", stripClub, true, new Health(35).withResistances(new DamageResistance(DamageType.BLUNT, "'s massive rock-hard tits takes the blunt of the force, reducing her damage taken to %.2f damage.", .5)),
                new Attack(DamageType.BLUNT, 3, "Boob Bash", "smacks you with a hardened fake titty."),
                new Attack(DamageType.FIRE, 10, "Molotov Cocktail", "grabbing a bottle from the bar, she lights in on fire and throws it at you."),
                new Attack(DamageType.SLASH, 5, "Stiletto Stab", "using her pumps, she impales one of your limbs."),
                new Attack(DamageType.MENTAL, 4, "Berate", "she ruthlessly yells slurs about your worthlessness."));
        stripClub.addEntityToRoom(destiny);

        destiny.getHealth().onDeath.add(x
                -> stripClub.addItem(new ConsumableItem("Mystery-meat", "Some meat of strange and unknown origins, tastes mildly like pig.", 100, 20, -5))
        );

        stripClub.addItem(new ConsumableItem("Money", "A bunch of one-dollar bills covered by strange fluids. Moist.", 10, 15, 25));

        kfc.setExit("east", shrek);
        kfc.setExit("west", stripClub);
        kfc.setHappiness(15);

        kfc.addEntityToRoom(new InteractableNPC("Katie", "Seems like one of those fast food employees who give people extra nuggets.", kfc,
                new Interaction("Order", "Place an order for whatever you feel like having", x -> {
                    x.addHappiness(7);
                    return "The cashier happily guides you through the order and predicts exactly what you wanted as if she read your mind. "
                            + "A bit creepy, but it makes you happy that someone would understand you so. +7 happiness";
                })
        ));

        shrek.setExit("west", kfc);

        HostileNPC shrekNPC = new HostileNPC("Shrek", "Memelord Alpha-Omega", shrek, true, new Health(42).withResistances(new DamageResistance(DamageType.FIRE, "'s outer layer roasts, caramelizing the sugars within and doing an increased %.2", 1.5f)),
                new Attack(DamageType.DAB, 5, "Shrek'd", "a complete shrek."),
                new Attack(DamageType.BLUNT, 10, "Fat rip", "a different form of blunt damage, causing you to pass out for five hours. Any damage done is due to hunger."),
                new Attack(DamageType.FIRE, 8, "Onions", "the multiple layers of his soul."),
                new Attack(DamageType.MENTAL, 20, "Love", "his love."),
                new Attack(DamageType.MENTAL, 20, "Life", "his life.")
        );

        shrek.addEntityToRoom(shrekNPC);

        shrekNPC.getHealth().onDeath.add(x -> {
            ((Player) x.getDamage().getAttacker()).addAttack(new Attack(DamageType.BLUNT, 25, "onion", "thrown at the eyes for maximum crytitude."));
            ((Player) x.getDamage().getAttacker()).addAttack(new Attack(DamageType.WATER, 25, "swampwater", "teeming with microscopic life, yet smells like death."));
        });

        allotment.setExit("south", drugs);
        allotment.setExit("east", movie);
        allotment.setHappiness(10);

        allotment.addEntityToRoom(new InteractableNPC("Vader", "Darth Vader the Elderly - Passionate owner of a beautiful allotment", allotment,
                new Interaction("Storytime", "Let Vader tell you about his succeses and failures in life.", x -> {
                    x.addHappiness(5);
                    return "As you listen to the tales of an old wise man, you feel an ember of purpose flicker inside. +20 happiness";
                }),
                new Interaction("Father?", "This question is not thoroughly thought through, and the answer may not please you.", x -> {
                    x.addHappiness(-15);
                    return "He responds with a stern and slightly disgusted refusal. This makes you a bit sad. -15 happiness";
                }),
                new Interaction("Flora", "Ask Vader to elaborate on the flora of his allotment", x -> {
                    x.addHappiness(10);
                    return "While guiding you around the garden he suddenly stumbles upon a particular plant which, as he explains, is tremendous as an ingredient in pastrymaking";
                })
        ));

        allotment.addItem(new ConsumableItem("Plant", "A strange plant with distinctly shaped leaves. Something tells you this can be smoked.", 100, 0, 10));

        movie.setExit("south", thaiHooker);
        movie.setExit("west", allotment);
        movie.setHappiness(10);

        movie.addEntityToRoom(new InteractableNPC("tv", "A big television seemingly for free use.", movie,
                new Interaction("Watch", "You turn on the tv and apparently a movie is just starting.", x -> {
                    x.addHappiness(5);
                    x.addHealth(5);
                    return "After the movie is finished you feel rejuvenated as the movie was good and you had a chance to relax. +5 happiness, +5 health.";
                })
        ));

        movie.addEntityToRoom(new ConsumableItem("Snack", "Buttered popcorn", 100, 5, 5));
        movie.addEntityToRoom(new ConsumableItem("Drink", "Seems to change in taste according the drink you're thinking about", 100, 5, 5));

        drugs.setExit("north", allotment);
        drugs.setExit("east", thaiHooker);

        HostileNPC bigolboi = new HostileNPC("Bigol'boi", "A mean looking fellow who seemingly wants to nick your stuff. You are having none of this and decides to fight him", drugs, true, new Health(50),
                new Attack(DamageType.BLUNT, 5, "Clobber", "bludgeoning you on the head with an improvised mace."),
                new Attack(DamageType.FIRE, 7, "Ignite", "dousing you with gasoline and flicking a lit match at you."),
                new Attack(DamageType.MENTAL, 8, "Flash", "ripping off his trenchcoat and exposing himself"),
                new Attack(DamageType.SLASH, 4, "Stab", "flailing his pocketknife around uncontrollably")
        );

        drugs.addEntityToRoom(bigolboi);

        bigolboi.getHealth().onDeath.add(x -> {
            drugs.addItem(new ConsumableItem("vitamin-d", "Low vitamin-D can lead to depression, treat yourself some pulverized sunlight!", 100, 15, 35));
        });

        drugs.addEntityToRoom(new InteractableObject("Line-of-coke", "An inviting line of cocain lying on a iredescent mirror surface.", new Interaction("dewit", "Do the line.", (Player x) -> {
            x.addHappiness(-25); // The player is told that they first gain 100 and then lose 125, but in fact we just remove 25. #leekhacksaw.
            return "You feel great, your happiness increases by +100. However an hour pass and you crash hard. -125 happiness";
        })));

        gate.setExit("north", campfire);
        gate.setExit("south", boss, true, "This door is locked by foul, eldritch magic. You must have at least 95 happiness to enter it.");

        gate.addEntityToRoom(new InteractableObject("Gate", "A massive gate of untold size, yet you feel within a potiential to open it.",
                new Interaction("Admire", "You look at the gate with awe and curiosity.", x -> {
                    x.addHealth(1);
                    return "As you stare at the gate for a good few minutes you feel a bit of strength return to your body. +1 health.";
                }),
                new Interaction("Unlock", "Call upon the wholesome force within, as your force of will itself shall open this door.", x -> {
                    if (x.getHappiness() > 95) {
                        gate.unlockExit("south");
                        return "You feel a chill go down your spine, as the door creeks thunderously.";
                    } else {
                        return "You do not yet possess the strength within to enter. Begone!";
                    }
                }))
        );

        boss.setExit("south", suprise, true, "The way is blocked by Erikthulhus life force, yet from behind you hear muffled screams, it fills you with untold dread and misery.");

        //A new NPC, the boss Erikthulhu, is created. 
        HostileNPC erikthulhu = new HostileNPC("Erikthulhu", "Your final opponent. The physical manifistation of your depression, and the evil it brings to your life.", boss, true, new Health(666d).withResistances(
                new DamageResistance[]{
                    new DamageResistance(DamageType.BLUNT, "is impervious to blunt force trauma, he is simply too great.", 0),
                    new DamageResistance(DamageType.SLASH, "has too thick skin to penetrate, your slash simply glances off with a small papercut-like wound left, doing %.2f damage.", 0.1),
                    new DamageResistance(DamageType.DAB, "cannot handle your pure dank memery and suffers a hefty stroke, doing an impressive %.2f damage.", 10),
                    new DamageResistance(DamageType.MENTAL, "is distraught by your comfidence and self-worth, and so he takes an impressive %.2f damage.", 2),}),
                new Attack(DamageType.DAB, 10d, "Intense Dab", "your own tactics against you. You cannot keep fighting yourself like this."),
                new Attack(DamageType.FIRE, 15d, "Firebreath", "a massive storm of fire. Bricks will be shat."),
                new Attack(DamageType.SUNONASTICK, 0, "Sun on a Stick", "arguably the most useful of all weapons."),
                new Attack(DamageType.WATER, 2d, "Water Gun", "a soft, glistering, rather refreshing spray of water originating from a toy gun."),
                new Attack(DamageType.BLUNT, 10d, "Vigerous Punch", "an intense punch of eldritch might."),
                new Attack(DamageType.MENTAL, 10d, "Insult", "an insult upon your appearance, talents and skills all wowen together in a beautiful euphony of wordsmithing.")
        );

        erikthulhu.getHealth().onDeath.add(x -> {
            ((Player) x.getDamage().getAttacker()).addAttack(new Attack(DamageType.MENTAL, 2500, "perfect-grade", "the physical result of this entire adventure."));
        });

        erikthulhu.getHealth().onDeath.add(x -> boss.unlockExit("south"));

        boss.addEntityToRoom(erikthulhu);

        //Items added to the different rooms:
    }

}
