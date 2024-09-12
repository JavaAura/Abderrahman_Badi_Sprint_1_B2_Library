package src.business.interfaces;


public interface Borrowable {
    void borrowDocument(long id);

    void returnDocument(long id);
}
