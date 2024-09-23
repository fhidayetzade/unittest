package springdata.technest22.service;

public class Calculate {

    public int sum(int a, int b){
        return a+b;
    }

    public int div(int a, int b){
        if(b==0){
            throw new IllegalArgumentException("Division by zero prohibited");
        }
        return a/b;
    }
}
