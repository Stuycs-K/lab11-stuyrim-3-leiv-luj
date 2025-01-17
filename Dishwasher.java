public class Dishwasher extends Adventurer{
  int soap, soapMax;
  String preferredLanguage;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Dishwasher(String name, int hp, String language){
    super(name,hp);
    soapMax = 12;
    soap = soapMax/2;
    preferredLanguage = language;
  }

  public Dishwasher(String name, int hp){
    this(name,hp,"c++");
  }

  public Dishwasher(String name){
    this(name,24);
  }

  public Dishwasher(){
    this("Carmack");
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
    return this + " threw dirty dishes at "+ other + " and dealt "+ damage +
    " points of damage. " + this + "replenishes 2 soap.";
  }

  /*Deal 3-12 damage to opponent, only if soap is high enough.
  *Reduces soap by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*5+Math.random()*5)+3;
      other.applyDamage(damage);
      return this + " used their "+preferredLanguage+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough soap to use the ultimate code. Instead "+attack(other);
    }

  }
  /* minus 3 damage taken by teammates */
  public String support(Adventurer other){
    return "Cleans weapons of "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /* minus 3 damage taken by self */
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" cleans knives "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
