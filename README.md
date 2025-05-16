Rat Defender
A JavaFX-based tower defense game inspired by Plants vs Zombies, where players defend against waves of rats using strategic unit placement.

🎮 Game Overview

🚩 Objective  

  Prevent rats from reaching the left side of the screen by deploying units which cost souls.
  
🔫 Unit Types

    Producer
      Generates souls (currency) cost: 50 souls ability: +10 souls every 2.3s
    Shooter	Ranged              
      attacker cost: 50 souls ability: Deals 1 damage (far-ranged)
    Melee Attacker	      
      Close-combat fighter cost: 50 souls	ability: Deals 2 damage (close-ranged)
    
🐀 Enemies

    Basic Rat: Moves at speed 3, deals 1 damage to units.

⚔️ Game Mechanics

    Lanes: Rats move in 5 horizontal rows; units only attack rats in their lane.

    Soul Economy: Spend souls to place units (earned via Producers).

    Lose Condition: A rat reaches the left boundary.

🛠 Technologies

    JavaFX (UI, animations, event handling)

    MVC Architecture (Separation of game logic, UI, and controllers)

    Multithreading (Concurrent enemy movement, attacks, and soul production)

🎮 How to Play

    Start Menu: Click "Play" to begin.

    Deploy Units:

        Select a unit type (Producer/Shooter/Melee).

        Click on a grid cell to place it (costs souls).

    Defend:

        Producers generate souls.

        Attackers automatically target rats in their lane.

    Win/Lose:

        Win: Eliminate all rats.

        Lose: A rat breaches your defense.

📂 Project Structure (UML file provided)

📜 Credits

    Developer: Inthawut Vongjiivuttikrai @VitItw

    Art Designer: Nataset Limlerdpolbun

    Course: 2110215 Programming Methodology, Chulalongkorn University (2023)

📄 License

MIT License. Free for educational use.

🐀 Ready to defend? Run the game and stop the rat invasion!
