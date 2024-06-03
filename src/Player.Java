public class Player extends Entity {
    public Player(String name,int health, int atk, int dmg, int energy){
        super(name,health, energy, atk, dmg);
        
    }

    @Override
    public void specialAttack(Entity enemy){
        Random rnd = new Random();
        int min = 1;
        int max = 15;

        int attackRandom = rnd.nextInt((max-min)+ 1);
        int dmg = this.getAtk() + attackRandom;
        int defenderDefense = enemy.getDef();
        
        dmg = (dmg -defenderDefense) * 2;

        if (dmg <= 0){
            dmg = 1;
        }


    }
}