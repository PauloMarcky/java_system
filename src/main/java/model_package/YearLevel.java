/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model_package;

/**
 *
 * @author paulo
 */
public enum YearLevel {
    FIRST_YEAR(1), SECOND_YEAR(2), THIRD_YEAR(3), FOURTH_YEAR(4);

    private final int level;
    YearLevel(int level) { this.level = level; }
    public int getLevel() { return level; }

    public static YearLevel fromInt(int i) {
        switch (i) {
            case 1: return FIRST_YEAR;
            case 2: return SECOND_YEAR;
            case 3: return THIRD_YEAR;
            default: return FOURTH_YEAR;
        }
    }
}
