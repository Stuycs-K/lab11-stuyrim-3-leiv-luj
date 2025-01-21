public class Chef extends Adventurer{
  int seasoning, seasoningMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Chef(String name, int hp){
    super(name, hp);
    seasoningMax = 12;
    seasoning = seasoningMax/2;
  }

public Chef(String name){
    this(name,25);
  }

  public Chef(){
    this("Chef");
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
    other.applyDamage(1);
    restoreSpecial(2);
    return this + " sprayed chili sauce in "+ other + "'s eyes and dealt "+ damage +
    " points of damage. " + this + " replenishes 2 seasoning.";
  }

  /*Deal 5-6 damage to opponent, only if seasoning is high enough.
  *Reduces seasoning by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*2)+5;
      other.applyDamage(damage);
      return this + " threw raw meat at "+ other + "." + " This dealt " + damage +" points of damage to " + other + ".";
    }else{
      return "Not enough seasoning to use the raw meat. Instead, "+attack(other);
    }

  }
  /*Restores 6 hp to other*/
  public String support(Adventurer other){
    setSpecial(getSpecial()-2);
    int hp = 6;
    other.setHP(other.getHP() + hp);
    return this + " used 2 seasoning to cook delicious food for " +other+" and restored " + hp + " HP.";
  }
  /*Restores 6 hp to self.*/
  public String support(){
    setSpecial(getSpecial()-2);
    int hp = 6;
    setHP(getHP()+hp);
    return this+" used 2 seasoning and ate delicious food and gained " +hp+" HP.";
  }
}
