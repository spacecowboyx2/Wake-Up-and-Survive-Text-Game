import java.util.Scanner;
import java.util.Random;
public class Game{
    public Player player;
    public Scanner input;
    private Room currentRoom;
    private Room previousRoom;

    public Game(){
        input = new Scanner(System.in);
    }

    public static void writeText(String text){
        for (char letra : text.toCharArray()) {
            System.out.print(letra);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
    }

    private void createRooms() {
        Enemy[] enemies = new Enemy[7];
        enemies[0] = new Enemy("Huge Rat", 30, 6, 6);
        enemies[0].setSatk("Leptospirosis");


        //create and set rooms and enemys
        Room room1 = new Room("You are in a small room with a single door to the north.", ("You wake up in a small,"+
        "dimly lit room. The air is thick with dust."+ " You look around and see a knife. For some reason, you think that you knows how to use it."+
        " You look at the door and check if it is open. Oh, it's open."));
        Room room2 = new Room("You are in a street of a big city. There are ways to west and east.", 
        ("This City is strange. There's no people, no cars, just buildings and vegetation covering them. You see a supermarket at west."));
        Room supermarket = new Room("You are in Extra's supermarket.", ("You feel hungry, and decide to search for food (if still exists). "+
        "Suddenly, you hear a strange noise. You get closer to this strange noise. You see a huge rat as big as a Saint Bernard. The rat looks at you " +
        "and moves forward to attack you."));
        Room room2East = new Room("description", "story");
        // Define exits for each room
        // rooms 1
        room1.setExit("north", room2);
        room1.setEvent("knife", 8, "This is a Tactical Knife, its seems useful.", player);
        room2.setExit("south", room1);
        room2.setExit("west", supermarket);
        room2.setExit("east", room2East);
        supermarket.setExit("east", room2);
        supermarket.setEnemy(enemies[0]);
        room2East.setExit("west", room2);

        // Start game in room1
        this.currentRoom = room1;
        this.previousRoom = room1;
    }

    public void play() {
        while (true) {
            printRoomDetails();
            processCombat();
            String command = input.nextLine().toLowerCase().trim();
            if (command.startsWith("go ")) {
                processMovementCommand(command);
            } else {
                System.out.println("Invalid command. Try 'go north' or 'go south' or 'go east' or 'go west'.");
            }
        }
    }

    private void processCombat(){
        if (currentRoom.hasEnemy()){
            Enemy enemy = currentRoom.getEnemy();
            combat(player, enemy);
        }
    }
    private void processMovementCommand(String command){    
        String direction = command.substring(3);
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom != null) {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
        } else {
            writeText("You can't go that way. Available exits are: " + currentRoom.getExitsDescription());
    }
}
    
    private void printRoomDetails() {
        if (!currentRoom.isVisited()) {
            writeText(currentRoom.getDescription());
            writeText(currentRoom.getStoryText());
            currentRoom.visit();
        } else {
            writeText(currentRoom.getDescription());
        }
    }



    public void start(){
        writeText("Welcome! You have been sleep for 300 Years and now you woke up to rediscover the world!");
        writeText("Unfortunately, big threats exist and you will have to deal with them. Are you prepared? y/n");

        String op = input.nextLine();
        op.toLowerCase();
        while (!op.equals("y") ){
            switch (op.toLowerCase()) {
                case "n":
                    writeText("Hm... Okay. Good Bye");
                    System.exit(0);
                    break;
            
                default:
                    writeText("i don't get it. Say Y or N");
                    op = input.nextLine();
                break;
            }   
        }
        writeText("Great! Let's Go!!");
        writeText("What's your name? ");
        String name = input.nextLine();
        this.player = new Player(name, 100, 5, 2);

        createRooms();
        play();

    }

    public void combat(Player player, Enemy enemy){
        Random rnd = new Random();
        writeText("You got into a fight against "+ enemy.getName());
        while (player.getAlive() && enemy.getAlive()){
            writeText("What will you do?");
            System.out.println("1.Attack    2.Special Attack    3. run");
            int op = input.nextInt();
            input.nextLine();
            switch (op) {
                case 1:
                    player.normalAttack(enemy);
                    enemy.isAlive();
                    break;
                case 2:
                    player.specialAttack(enemy);
                    enemy.isAlive();
                    break;
                case 3:
                    int sucess = rnd.nextInt(10);
                    if (sucess >= 7){
                        writeText("You ran away.");
                    } else {
                        writeText("you failed to escape!");
                        writeText(enemy.getName()+" attacks you.");
                    }
                default:
                    writeText("Invalid choice. " + enemy.getName() + "attacks you while you're indecisive.");
                break;
            }
            int enemyAttack = rnd.nextInt(100);
            
            if (enemy.isAlive()){
                if (enemyAttack < 80){
                    enemy.normalAttack(player);
                } else{
                    enemy.specialAttack(player);
                }
            }
            player.isAlive();
            
        }
        if (!player.getAlive()){
            writeText("You died. GAME OVER");
            System.exit(0);

        } else {
            writeText("You won!");
            currentRoom.setEnemy(null);
        }

    }
}