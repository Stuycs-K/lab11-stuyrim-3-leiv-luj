public class Butcher extends Adventurer{
  int meatScraps, meatScrapsMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Butcher(String name, int hp){
    super(name,hp);
    meatScrapsMax = 12;
    meatScraps = meatScrapsMax/2;
  }

  public Butcher(String name){
    this(name, 25);
  }

  public Butcher(){
    this("Butcher");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "meatScraps";
  }

  public int getSpecial(){
    return meatScraps;
  }

  public void setSpecial(int n){
    meatScraps = n;
  }

  public int getSpecialMax(){
    return meatScrapsMax;
  }

  /*Deal 4-6 damage to opponent*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+4;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " sliced "+ other + " with a butcher knife and dealt " + damage + " points of damage. Replenished 2 meat scraps.";
  }

  /*Deal 9-12 damage to opponent if meatScraps is high enough (8).
  *Reduces meatScraps by 6. Does 3 damage to self.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-6);
      int damage = (int)(Math.random()*4)+9;
      other.applyDamage(damage);
      this.applyDamage(3);
      return this + " attacked with their meat cleaver, dealing " + damage + " points of damage to " + other + ", but took 3 points of recoil damage.";
    }else{
      return "Not enough meatScraps to use the meat cleaver. Instead, "+attack(other);
    }
  }
  /*heals team +3 hp*/
  public String support(Adventurer other) {
    setSpecial(getSpecial()-2);
    int hp = 3;
    other.setHP(other.getHP() + hp);
    return this + " used 2 meat scraps and served a meat feast to " + other + ", restoring " + hp + " HP.";
  }
  /*restores 4 hp and 2 meat scraps*/
  public String support(){
    int hp = 4;
    setHP(getHP() + hp);
    int scraps = 2;
    setSpecial(getSpecial() + 2);
    return this + " dined on a steak, restoring " + hp + " HP and " + scraps + " " + getSpecialName() + ".";
  }
}
