package a3.GameObjects;

/**
 * Created by Max on 10/5/2014.
 */
public abstract class FixedGameObject extends GameObject {

    private String className = "FixedGameObject";
    private int age = 0;

    public void incrementAge(){
        age++;
    }

    public int getAge() {
        return age;
    }

    public String toString(){
        return super.toString() + "   Age: "+age;
    }
}
