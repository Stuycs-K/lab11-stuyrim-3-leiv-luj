public class Chef extends Adventurer{
  int seasoning, seasoningMax;
  String preferredLanguage;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Chef(String name, int hp, String language){
    super(name,hp);
    seasoningMax = 12;
    seasoning = seasoningMax/2;
    preferredLanguage = language;
  }

  public Chef(String name, int hp){
    this(name,hp,"c++");
  }

  public Chef(String name){
    this(name,24);
  }

  public Chef(){
    this("Carmack");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "seasoning";
  }

  public int getSpecial(){
    return seasoning;
  }

  public void setSpecial(int n){
    seasoning = n;
  }

  public int getSpecialMax(){
    return seasoningMax;
  }

  /*Deal 1-3 damage to opponent*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+1;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " served raw chicken to "+ other + " and dealt "+ damage +
    " points of damage. It gives them food poisoning, making them fall asleep. " + this + " replenishes 2 seasoning.";
  }

  /*Deal 5-6 damage to opponent, only if seasoning is high enough.
  *Reduces seasoning by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*2)+5;
      other.applyDamage(damage);
      return this + " used their "+preferredLanguage+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough seasoning to use the ultimate code. Instead "+attack(other);
    }

  }
  /*Restores 6 hp to other*/
  public String support(Adventurer other){
    int hp = 6;
    setHP(getHP()+hp);
    return "Cooked delicious food for " +other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 hp to self.*/
  public String support(){
    int hp = 6;
    setHP(getHP()+hp);
    return this+" ate delicious food and gained "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
