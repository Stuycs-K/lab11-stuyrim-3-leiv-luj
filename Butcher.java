public class Butcher extends Adventurer{
  int meatScraps, meatScrapsMax;
  String preferredLanguage;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Butcher(String name, int hp, String language){
    super(name,hp);
    meatScrapsMax = 12;
    meatScraps = meatScrapsMax/2;
    preferredLanguage = language;
  }

  public Butcher(String name, int hp){
    this(name,hp,"c++");
  }

  public Butcher(String name){
    this(name,24);
  }

  public Butcher(){
    this("Carmack");
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
    return this + " sliced "+ other + " with a butcher knife and dealt "+ damage +
    " points of damage. Replenished 2 meat scraps.";
  }

  /*Deal 9-12 damage to opponent if meatScraps is high enough (6).
  *Reduces meatScraps by 5. Does 3 damage to self.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 6){
      setSpecial(getSpecial()-5);
      int damage = (int)(Math.random()*4)+9;
      other.applyDamage(damage);
      return this + " used their "+preferredLanguage+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough meatScraps to use the ultimate attack. Instead "+attack(other);
    }

  }
  /*makes team do +3 damage*/
  public String support(Adventurer other){
    return "Served up a meat feast for "+other+". " + other + "'s attacks now do +3 more damage.";
  }
  /*makes self do +3 damage*/
  public String support(){
    return "";
  //  return this+" dines on a steak and "+restoreSpecial(6)+" "
  //  + getSpecialName()+ " and "+hp+" HP";
  }
}
