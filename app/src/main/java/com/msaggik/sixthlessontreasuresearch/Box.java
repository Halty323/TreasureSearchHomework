package com.msaggik.sixthlessontreasuresearch;

public class Box {
    // поля
    private float coordinateX;
    public static final int dimensions = 50;
    private float coordinateY;
    private boolean found; // поле найден/не найден сундук сокровищ
    // конструктор
    public Box(float coordinateX, float coordinateY, boolean found) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.found = found;
    }
    public boolean intersects(Box other) {
        return (this.coordinateX < other.coordinateX + other.dimensions &&
                this.coordinateX + this.dimensions > other.coordinateX &&
                this.coordinateY < other.coordinateY + other.dimensions &&
                this.coordinateY + this.dimensions > other.coordinateY);
    }
    // геттеры
    public float getCoordinateX() {
        return coordinateX;
    }
    public float getCoordinateY() {
        return coordinateY;
    }
    public boolean isFound() {
        return found;
    }
    // сеттер
    public void setFound(boolean found) {
        this.found = found;
    }
}
