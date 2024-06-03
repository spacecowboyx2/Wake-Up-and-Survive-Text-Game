import java.util.Random;

public abstract class Entity{
    private String name;
    private int health;
    private int energy;
    private int atk;
    private int def;
    private String specialAtk;
    private boolean alive;
    

    

    public Entity(String name,int health, int atk, int def) {
        this.name = name;
        this.health = health;
        this.atk = atk;
        this.def = def;
        this.energy = 20;
        this.alive = true;
    }

    public String getSatk(){
        return this.specialAtk;
    }
    public void setSatk(String Satk){
        this.specialAtk = Satk;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if (this.getHealth() + health > 100){
            this.health = 100;
        } else {
            this.health = health;
        }
        
    }
    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        if (this.getEnergy() <= energy){
            this.energy = 0;
        } else if (energy < 0) {
    
        } else {
            this.energy = energy;
        }
    }
    public boolean hasEnergy(){
        if (this.getEnergy()> 0){
            return true;
        }
        return false;

    }
    public int getAtk() {
        return atk;
    }
    public void setAtk(int atk) {
        this.atk = atk;
    }
    public int getDef() {
        return def;
    }
    public void setDef(int def) {
        this.def = def;
    }
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean getAlive(){
        return this.alive;
    }

    public boolean isAlive(){
        if (this.health == 0){
        this.alive = false;  
        return false;    
    } else {
        return true;
    }
    
    }

   

    public void normalAttack(Entity enemy){
        Random rnd = new Random();
        int min = 1;
        int max = 10;

        int dmg = this.getAtk();
        int defenderDefense = enemy.getDef();
        int attackRandom = rnd.nextInt((max-min)+ 1);
        boolean critical = false;
        if (attackRandom >= 8){
            dmg = dmg + attackRandom;
            critical = true;
            
        }
        dmg = dmg -defenderDefense;
        if (dmg <= 0){
            dmg = 1;
        }
        if (critical){
            Game.writeText("Critical hit!");
        }
        if (dmg >= enemy.getHealth()){
            Game.writeText(this.getName().toUpperCase()+ " attacked "+ enemy.getName()+". "+ enemy.getName()+" has been defeated.");
            enemy.setHealth(0);
        } else {
            Game.writeText(this.getName()+ " attacked "+ enemy.getName()+".");
            enemy.setHealth(enemy.getHealth()-(dmg));
            Game.writeText(this.getName()+" did " + dmg + " damage.");
        }

        
    }
    public void heal(int heal){
        if (this.getHealth() == 100){
            Game.writeText("Your health is at maximum");
        } else {
            this.setHealth(heal);
        }
    }
    public void showStatus(){
        System.out.println("-----YOUR STATUS: -----");
        System.out.println("Health: "+ this.getHealth());
        System.out.println("Energy: "+this.getEnergy());
        System.out.println("Atk: "+ this.getAtk());
        System.out.println("Def: " + this.getDef());
    }
// TODO: implement special atk for the character's class (mage, warrior, etc) or special atks using weapoms

    public void specialAttack(Entity attacked){}

   
    
}