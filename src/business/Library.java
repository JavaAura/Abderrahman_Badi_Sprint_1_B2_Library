package src.business;

import java.util.ArrayList;
import java.util.Map;
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
