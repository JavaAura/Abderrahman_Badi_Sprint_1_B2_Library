package src.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.HashMap;
import java.util.List;

public class Library {
    private ArrayList<Document> documents;
    private Map<String, Document> documentMap;

    public Library() {
        this.documents = new ArrayList<>();
        this.documentMap = new HashMap<>();
    }

    public void addDocument(Document doc) {
        documents.add(doc); // Add to list
        documentMap.put(doc.getTitle(), doc); // Add to map with title as key
        System.out.println("Document added successfully!");
    }

    public void borrowDocument(Document doc) {
        doc.borrowDoc();
    }

    public void returnDocument(Document doc) {
        doc.returnDoc();
    }

    public void seedLibrary() {
        Random random = new Random();

        // Create 20 documents (10 Books and 10 Magazines)
        IntStream.rangeClosed(1, 20).forEach(i -> {
            String title = "Title " + i;
            String author = "Author " + i;
            LocalDate publicationDate = LocalDate.of(2000 + random.nextInt(24), 1 + random.nextInt(12),
                    1 + random.nextInt(28));
            int pageNumbers = 100 + random.nextInt(400);

            Document doc = (i % 2 == 0) ? new Book(title, author, publicationDate, pageNumbers, random.nextInt(10000))
                    : new Magazine(title, author, publicationDate, pageNumbers, random.nextInt(10000000));

            documents.add(doc);
            documentMap.put(title, doc);
        });

        // Randomly select 5 documents to be marked as borrowed
        random.ints(0, documents.size())
                .distinct()
                .limit(5)
                .forEach(i -> documents.get(i).setIsBorrowed(true));

        System.out.println("Library seeded with 20 documents successfully!");
    }

    // --------------- Getters / Setters -----------------------

    public List<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(ArrayList<Document> value) {
        this.documents = value;
    }

    public Map<String, Document> getDocumentMap() {
        return this.documentMap;
    }

    public void setDocument(Map<String, Document> value) {
        this.documentMap = value;
    }
}
