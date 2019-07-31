package ru.job4j.models;

import java.util.Objects;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.07.2019.
 */
public class Seat implements Comparable<Seat> {
    private final int id;
    private int idUser;
    private final int row;
    private final int seat;
    private String status;

    public Seat(int row, int seat, String status, int idUser) {
        this.id = 0;
        this.idUser = idUser;
        this.status = status;
        this.row = row;
        this.seat = seat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Seat(int id, int row, int seat, String status, int idUser) {
        this.id = id;
        this.idUser = idUser;
        this.row = row;
        this.seat = seat;
        this.status = status;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    //CHECKSTYLE:OFF
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat1 = (Seat) o;
        return idUser == seat1.idUser &&
                row == seat1.row &&
                seat == seat1.seat;
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return Objects.hash(idUser, row, seat);
    }

    @Override
    public int compareTo(Seat o) {
        int result;
        if (this.row == o.getRow()) {
            result = this.seat - o.getSeat();
        } else {
            result = this.row - o.getRow();
        }
        return result;
    }
}
