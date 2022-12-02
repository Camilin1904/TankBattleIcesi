package game.model;

import java.util.*;


public class Controller {
    private Player player1;

    private Player player2;

    private ListGraph<String, Moveable> stage;

    private int[][] temp;

    public Controller(Player p1, Player p2){
        stage = new ListGraph<>();
        player1 = p1;
        player2 = p2;
        p1.setMap(stage);
        p2.setMap(stage);
        for(int i=0; i<14; i++){
            for(int j=0; j<14; j++){
                stage.addVertex(i + "," + j, null);
            }
        }
    }


    public int[][] createScenario(int[][] template, String posP1, String posP2, String posEnemy){
        for(int i=0; i<template.length; i++){
            for(int j=0; j<template.length; j++){
                Vertex<String, Moveable> m = stage.searchVertex(i + "," + j);
                if(i>0) {
                    stage.addConnection(i + "," + j, (i-1) + "," + j, template[i-1][j]!=0? template[i-1][j]: Integer.MAX_VALUE);
                }
                if(i<template.length-1) {
                    stage.addConnection(i + "," + j, (i+1) + "," + j, template[i+1][j]!=0? template[i+1][j]: Integer.MAX_VALUE);
                }
                if(j>0) {
                    stage.addConnection(i + "," + j, i + "," + (j-1), template[i][j-1]!=0? template[i][j-1]: Integer.MAX_VALUE);
                }
                if(j<template.length-1) {
                    stage.addConnection(i + "," + j, i + "," + (j+1), template[i][j+1]!=0? template[i][j+1]: Integer.MAX_VALUE);}

                if(j<template.length-1&&i<template.length-1) {
                    stage.addConnection(i + "," + j, (i+1) + "," + (j+1), template[i+1][j+1]!=0? template[i+1][j+1]: Integer.MAX_VALUE);
                }
                if(j<template.length-1&&i>0) {
                    stage.addConnection(i + "," + j, (i-1) + "," + (j+1), template[i-1][j+1]!=0? template[i-1][j+1]: Integer.MAX_VALUE);
                }
                if(j>0&&i<template.length-1) {
                    stage.addConnection(i + "," + j, (i+1) + "," + (j-1), template[i+1][j-1]!=0? template[i+1][j-1]: Integer.MAX_VALUE);
                }
                if(j>0&&i>0 ) {
                    stage.addConnection(i + "," + j, (i-1) + "," + (j-1), template[i-1][j-1]!=0? template[i-1][j-1]: Integer.MAX_VALUE);
                }
                if(posP1.equals(i + "," + j)) stage.searchVertex(i + "," + j).setValue(player1);
                else if(posP2.equals(i + "," + j)) stage.searchVertex(i + "," + j).setValue(player2);
                else if(posEnemy.equals(i + "," + j)) stage.searchVertex(i + "," + j).setValue(Enemy.getInstance());
            }
        }

        return template;

    }

    public void connect(String id){
        stage.setAll1(id);
    }

    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public ListGraph<String, Moveable> getStage() {
        return stage;
    }
    
}
