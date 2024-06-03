import java.util.Random;
public class Player extends Entity {
    public Player(String name,int health, int atk, int def){
        super(name, health, atk, def);
        
    }

    @Override
    public void specialAttack(Entity enemy){
        Random rnd = new Random();
        int hit = rnd.nextInt(10);


        if ( hit >= 6){
            if (hasEnergy()){
                int min = 1;
                int max = 15;

                int attackRandom = rnd.nextInt((max-min)+ 1);
                int dmg = this.getAtk() + attackRandom;
                int enemyDef = enemy.getDef();
        
                if (this.getSatk().equalsIgnoreCase("Knife Dance")){
                    dmg = (dmg - enemyDef) * 2;
                    this.setEnergy(this.getEnergy()-2);
            
                } else if (this.getSatk().equals("Arrowman's Fury")){
                    dmg = (dmg-enemyDef) * 3;
                    this.setEnergy(this.getEnergy()-4);
                }

                if (dmg <= 0){
                    dmg = 1;
                }
                if (dmg >= enemy.getHealth()){
                    Game.writeText(this.getName()+ "used "+ this.getSatk()+" and "+ enemy.getName()+" has been defeated.");
                    enemy.setHealth(0);   

                } else {
                    Game.writeText(this.getName()+ " used " + this.getSatk() +"in "+ enemy.getName());
                    enemy.setHealth(enemy.getHealth()-(dmg));
                    Game.writeText(this.getName()+" did " + dmg + " damage.");
                    enemy.isAlive();
                }
            } else {
                Game.writeText("do not have energy!");
            }
        } else {
            Game.writeText("You missed the attack.");
        }
    }
}