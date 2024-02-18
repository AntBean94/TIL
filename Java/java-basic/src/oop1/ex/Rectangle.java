package oop1.ex;

public class Rectangle {
    int width = 5;
    int height = 8;

    int calculateArea() {
        int area = width * height;
        System.out.println("넓이 : " + area);
        return area;
    }

    int calculatePerimeter() {
        int perimeter = width * 2 + height * 2;
        System.out.println("둘레 길이 : " + perimeter);
        return perimeter;
    }

    boolean isSquare() {
        boolean result = width == height;
        System.out.println("정사각형 여부: " + result);
        return result;
    }
}
