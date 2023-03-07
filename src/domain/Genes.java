package domain;

public class Genes {

    protected String name;
    protected String organism;
    protected String function;
    protected String associatedSequence;

    @Override
    public String toString() {
        return name + " / " + organism + " / " +
                function + " / " + associatedSequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getAssociatedSequence() {
        return associatedSequence;
    }

    public void setAssociatedSequence(String associatedSequence) {
        this.associatedSequence = associatedSequence;
    }

    public Genes(String name, String organism, String function, String associatedSequence) {
        this.name = name;
        this.organism = organism;
        this.function = function;
        this.associatedSequence = associatedSequence;
    }
}
