package Commonalities;

public class Commonality {
    public Short vertex;
    public Short successor;
    public Short predecessor;
    public int support;

    public Commonality(Short vertex, Short predecessor, Short successor) {
        this.vertex = vertex;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public Short getVertex() {
        return vertex;
    }

    public void setVertex(Short vertex) {
        this.vertex = vertex;
    }

    public Short getSuccessor() {
        return successor;
    }

    public void setSuccessor(Short successor) {
        this.successor = successor;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    public Short getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Short predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public boolean equals(Object obj) {
        Commonality commonality = (Commonality) obj;
        return this.vertex.hashCode() == commonality.getVertex().hashCode() &&
                this.predecessor.hashCode() == commonality.getPredecessor().hashCode() &&
                this.successor.hashCode() == commonality.getSuccessor().hashCode();
    }

    @Override
    public String toString() {
        return "Commonality{" +
                "vertex=" + vertex +
                ", successor=" + successor +
                ", predecessor=" + predecessor +
                ", support=" + support +
                '}';
    }
}
