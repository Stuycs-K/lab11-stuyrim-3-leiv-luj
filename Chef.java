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
    //restoreSpecial(2);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. They then take a sip of their coffee.";
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
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}