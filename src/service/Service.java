package service;

import domain.Genes;
import repository.Repository;

import java.util.Vector;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public void createSchema() { repo.createSchema(); }

    public void openConnection() { repo.openConnection(); }

    public void addInSchema() { repo.addInSchema(); }

    public Vector<Genes> getAll() { return repo.getAll(); }

    public void updateSchema(String name, String newFunction, String newSequence) {
        repo.updateSchema(name, newFunction, newSequence);
    }
}
