package com.msaggik.sixthlessontreasuresearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // поля
    private TextView output, field;
    private float x, y; // координаты касания TextView field
    ArrayList<Box> boxes = new ArrayList<>(10); // массив для объектов сундуков сокровищ
    private int count = 0; // счётчик найденных сундуков
    private final int fieldWidth = 960;
    private final int fieldHeight = 1450;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // создание и инициализация массива объектов сундуков сокровищ
        Random rand = new Random(); // объект класса для генерации псевдослучайного значения
        int created = 0;
        while (created != 10) {
            int coordinateX = rand.nextInt(fieldWidth - Box.dimensions);
            int coordinateY = rand.nextInt(fieldHeight - Box.dimensions);
            Box newBox = new Box(coordinateX, coordinateY, false);
            boolean intersected = false;
            for (Box box : boxes) {
                if (newBox.intersects(box)) {
                    intersected = true;
                }
            }
            if (!intersected) {
                boxes.add(newBox);
                created++;
            }
        }
        System.out.println(boxes);

        // привязка разметки к полям
        output = findViewById(R.id.output);
        field = findViewById(R.id.field);

        // обработка касания TextView field
        field.setOnTouchListener(listener);
    }

    // создание слушателя
    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            // определение координат касания
            x = motionEvent.getX();
            y = motionEvent.getY();

            // определение типа касания
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: // касание TextView field
                    field.setText("Касание " + x + ", " + y);
                    break;
                case MotionEvent.ACTION_MOVE: // движение по TextView field
                    field.setText("Движение " + x + ", " + y);
                    // поиск сундуков сокровищ
                    for(Box box: boxes) {
                        // если удалось провести пальцем по сундуку сокровищ и он не найден
                        if(!box.isFound() && x >= (box.getCoordinateX() - Box.dimensions) && x <= (box.getCoordinateX() + Box.dimensions) &&
                                y >= (box.getCoordinateY() - Box.dimensions) && y <= (box.getCoordinateY() + Box.dimensions)) {
                            box.setFound(true); // установка сундука как найденного
                            count++; // повышение счётчика поиска сундуков
                            output.setText("Найдено сундуков " + count);
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP: // отпускание TextView field
                    field.setText("Отпускание " + x + ", " + y);
                    break;
            }
            return true;
        }
    };
}