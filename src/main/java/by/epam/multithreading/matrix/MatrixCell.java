/**
 * Created by Alexander Lomat on 31.05.19
 * Version 0.0.2
 */

package by.epam.multithreading.matrix;

public class MatrixCell {

    private int cellValue;
    private boolean wasChanged;

    public MatrixCell(int cellValue) {
        this.cellValue = cellValue;
    }

    public int getCellValue() {
        return cellValue;
    }

    public void setCellValue(int cellValue) {
        this.cellValue = cellValue;
    }

    public boolean wasChanged() {
        return wasChanged;
    }

    public void setWasChanged(boolean wasChanged) {
        this.wasChanged = wasChanged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatrixCell cell = (MatrixCell) o;
        return cellValue == cell.cellValue;

    }

    @Override
    public int hashCode() {
        int result = cellValue;
        result = 33 * result;
        return result;
    }

    @Override
    public String toString() {
        return "[" + cellValue + "]";
    }
}
