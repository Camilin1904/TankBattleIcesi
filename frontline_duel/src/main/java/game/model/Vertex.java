package game.model;

import java.util.*;

public class Vertex<I extends Comparable<I>, T> implements Comparable<Vertex<I,T>>{
    private I id;
    private T value;
    private Vertex<I, T> parent;
    private HashMap<I,Pair<Vertex<I, T>,Integer>> adyacentVertex;
    private int distance;
    private int color;
    private int initial;
    private int end;
    private int type = 1;
    private int oCol;
    private boolean hasKey;
    
    public Vertex(T value){
        this.value = value;
        adyacentVertex = new HashMap<>();
        hasKey = false;
    }

    public Vertex(I id, T value){
        this.id = id;
        this.value = value;
        adyacentVertex = new HashMap<>();
        hasKey = false;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertex<I, T> getParent() {
        return parent;
    }
    
    public void setParent(Vertex<I, T> parent) {
        this.parent = parent;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public HashMap<I,Pair<Vertex<I, T>,Integer>> getAdyacentVertex() {
        return adyacentVertex;
    }
    public void setAdyacentVertex(HashMap<I,Pair<Vertex<I, T>,Integer>> adyacentVertex) {
        this.adyacentVertex = adyacentVertex;
    }
    public void addAdyacentVertex(Pair<Vertex<I, T>,Integer> vertex){
        adyacentVertex.put(vertex.getA().getId(), vertex);
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public int getInitial() {
        return initial;
    }
    public void setInitial(int initial) {
        this.initial = initial;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public I getId() {
        return id;
    }
    public void setId(I id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int compareTo(Vertex<I, T> o) {
        return id.compareTo(o.getId());
    }

    public int getoCol() {
        return oCol;
    }
    public void setoCol(int oCol) {
        this.oCol = oCol;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
    public boolean getHasKey(){
        return hasKey;
    }

}
