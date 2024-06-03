import java.util.HashMap;
import java.util.Map;

public class Room {
    private String description;
    private String storyText;
    private boolean visited;
    private Map<String, Room> exits;
    private String eventText;
    private boolean hasEvent;
    private boolean hasEnemy;
    private Enemy enemy;

    public Room(String description, String storyText) {
        this.description = description;
        this.storyText = storyText;
        this.visited = false;
        this.exits = new HashMap<>();
        this.hasEvent = false;
        this.enemy = null;
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getDescription() {
        return description;
    }

    public String getStoryText() {
        return storyText;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    public String getExitsDescription() {
        return String.join(", ", exits.keySet());
    }


    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
        this.hasEnemy = true;
    }

    public boolean hasEvent(){
        return this.hasEvent;
    }

    public String getEventText(){
        return this.eventText;
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public boolean hasEnemy(){
        return enemy != null && enemy.isAlive();
    }


    public void setEvent(String typeEvent, int increase, String eventText, Player player){
        this.eventText = eventText;
        
        if (typeEvent.equals("increase atk")){
            Game.writeText("Your attack increased!");
            player.setAtk(player.getAtk()+increase);
        } else if (typeEvent.equals("increase def")){
            Game.writeText("Your defense has increased!");
            player.setDef(player.getDef() + increase);;
        } else if (typeEvent.equals("knife")){
            Game.writeText("You got a Knife!");
            Game.writeText("You learned Knife Dance.");
            player.setSatk("Knife Dance");
            player.setAtk(player.getAtk() + increase);
        } else if(typeEvent.equals("glock 18")){
            Game.writeText("You pick up a glock 18!");
            Game.writeText("Your attack increased!");
            player.setAtk(player.getAtk() - 5 + increase);
            Game.writeText("You learned Bullet Storm");
            player.setSatk("Bullet Storm");
        } 

        

    }
}