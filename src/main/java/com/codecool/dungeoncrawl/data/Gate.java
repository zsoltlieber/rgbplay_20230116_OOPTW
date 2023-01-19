package com.codecool.dungeoncrawl.data;

public class Gate {

    private Position gatePosition;
    private Position targetPosition;
    private final int mapNumber;

    public Gate(int gateX,int gateY, int targetX,int targetY, int mapNumber) {
        this.gatePosition = new Position(gateX,gateY);
        this.targetPosition = new Position(targetX,targetY);
        this.mapNumber = mapNumber;
    }

    public Position getGatePosition() {
        return gatePosition;
    }


    public Position getTargetPosition() {
        return targetPosition;
    }

    public int getMapNumber() {
        return mapNumber;
    }

}
