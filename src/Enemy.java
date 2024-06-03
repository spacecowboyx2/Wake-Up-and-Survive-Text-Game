import java.util.Random;
public class Enemy extends Entity{
    public Enemy(String name,int health, int atk, int dmg){
        super(name, health, atk, dmg);   
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
        
    
                dmg = (dmg - enemyDef) * 2;
                this.setEnergy(this.getEnergy()-2);


                if (dmg <= 0){
                    dmg = 1;
                }
                if (dmg >= enemy.getHealth()){
                    Game.writeText(this.getName()+ "used "+ this.getSatk()+" and "+ enemy.getName()+" has been defeated.");
                    enemy.setHealth(0);   

                } else {
                    Game.writeText(this.getName()+ " used " + this.getSatk() +" in "+ enemy.getName());
                    enemy.setHealth(enemy.getHealth()-(dmg));
                    Game.writeText(this.getName()+" did " + dmg + " damage.");
                    enemy.isAlive();
                }
            } else {
                Game.writeText("do not have energy!");
            }
        } else {
            Game.writeText(this.getName()+ " missed the attack.");
        }
    }
}
