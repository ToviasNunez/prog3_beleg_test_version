package observerPatternNoGoodEnought;

public class BeobachterTags implements Beobachter{

   public  BeobachterTags(ConcreteSubjectTags concreteSubjectTags){
        concreteSubjectTags.meldeAn(this);
    }

    @Override
    public void update(Object obj) {
        System.out.println("nwe tag was add" + obj);

    }

}
