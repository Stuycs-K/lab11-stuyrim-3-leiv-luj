public class Dishwasher extends Adventurer{
  int soap, soapMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Dishwasher(String name, int hp){
    super(name,hp);
    soapMax = 12;
    soap = soapMax/2;
  }

  public Dishwasher(String name){
    this(name,25);
  }

  public Dishwasher(){
    this("Dishwasher");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "soap";
  }

  public int getSpecial(){
    return soap;
  }

  public void setSpecial(int n){
    soap = n;
  }

  public int getSpecialMax(){
    return soapMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 soap*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*6)+2;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " threw dirty dishes at "+ other + " and dealt "+ damage + " points of damage. " + this + " replenishes 2 soap.";
  }

  /*Deal 3-12 damage to opponent, only if soap is high enough.
  *Reduces soap by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*5+Math.random()*5)+3;
      other.applyDamage(damage);
      return this + " threw a bacteria infested soap sponge at "+ other +"." + " This dealt "+ damage +" points of damage to " + other + ".";
    }else{
      return "Not enough soap to use the special attack. Instead, "+attack(other);
    }

  }
  /* +4 special of teammates */
  public String support(Adventurer other){
    setSpecial(getSpecial()-2);
    return this + " used 2 soap to clean the knives of " + other + " and restored "
    + other.restoreSpecial(4)+" "+other.getSpecialName();
  }
  /* heals 2 damage taken by self */
  public String support(){
    setSpecial(getSpecial()-2);
    int hp = 2;
    setHP(getHP()+hp);
    return this+" used 2 soap to clean knives and restored "+ hp+" HP.";
  }
}
