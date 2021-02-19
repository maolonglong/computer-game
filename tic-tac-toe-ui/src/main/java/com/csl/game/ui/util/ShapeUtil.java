package com.csl.game.ui.util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

/**
 * @author MaoLongLong
 * @date 2021-02-19 20:12
 */
public class ShapeUtil {

    private ShapeUtil() {
    }

    public static Circle generateCircle() {
        Circle circle = new Circle();
        circle.setFill(null);
        circle.setStroke(Color.SKYBLUE);
        circle.setRadius(100);
        circle.setStrokeWidth(6);
        return circle;
    }

    public static Polyline generatePolyline() {
        Polyline polyline = new Polyline();
        polyline.setStrokeWidth(6);
        polyline.setStroke(Color.PINK);
        polyline.getPoints().addAll(0.0, 0.0, 200.0, 200.0,
                100.0, 100.0, 0.0, 200.0, 200.0, 0.0);
        return polyline;
    }
}
