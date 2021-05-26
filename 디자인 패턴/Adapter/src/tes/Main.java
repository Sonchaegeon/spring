package tes;

abstract class Figure {
    protected int dot;
    protected int area;

    public Figure(final int dot, final int area) {
        this.dot = dot;
        this.area = area;
    }

    public abstract void display();
}

class Triangle extends Figure {
    public Triangle(final int dot, final int area) {
        super(dot, area);
    }

    @Override
    public void display() {
        System.out.printf("넓이가 %d인 삼각형입니다.", area);
    }
}

public class Main {
    public static void main(String[] args) {
        Figure figure = new Triangle(3, 10);

        for(int i = 0; i < figure.dot; i++) {
            figure.display();
        }
    }
}