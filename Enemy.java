public class Enemy extends Adventurer {
  int reviews, reviewsMax;

  public Boss() {
    super("Food Critic", 20);
    reviewsMax = 10;
    reviews = reviewsMax;
  }

  public String getSpecialName(){
    return "reviews";
  }

  public int getSpecial(){
    return reviews;
  }

  public void setSpecial(int n){
    reviews = n;
  }

  public int getSpecialMax(){
    return reviewsMax;
  }

  public String attack(Adventurer other){
    int damage = (int) (Math.random() * 3) + 2;
    other.applyDamage(damage);
    setSpecial(getSpecial() - 2);
    return this + " left a one star review for "+ other + ", dealing "+ damage + " points of damage. " + this + " has " + getSpecial() + " reviews left.";
  }

  public String specialAttack(Adventurer other){
    if(getSpecial() >= 6){
      setSpecial(getSpecial()-6);
      int damage = (int)(Math.random()*3)+4;
      other.applyDamage(damage);
      return this + " asked " + other + " to speak to the manager." + " This dealt "+ damage +" points of damage to " + other + "." + this + " has " + getSpecial() + " reviews left.";
    }else{
      return "Not enough reviews to speak to the manager. Instead, "+attack(other);
    }
  }

  public String support() {
    int hp = 7;
    setHP(getHP() + hp);
    setSpecial(getSpecial() + 2);
    if (getSpecial() > getSpecialMax()) {
        setSpecial(getSpecialMax());
    }
    return this + " tried delicious food, and left a five star review, restoring " + hp + " HP and restoring 2 reviews.";
}
}
