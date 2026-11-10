package com.wust30405.ricp.detect.utils;

import com.sun.jna.Structure;

import java.util.ArrayList;
import java.util.List;


public class Box {


    //1.只要涉及到结构体的传递，必须使用ByReference或者ByValue中的一种
    //
    //2.指针和引用的传递使用ByReference
    //
    //2.拷贝参数传递使用ByValue
    public static class ByReference extends Box implements Structure.ByReference{
        ByReference(int x, int y, int w, int h, float prob, int obj_id, int track_id, int frames_counter, float x_3d, float y_3d, float z_3d) {
            super(x, y, w, h, prob, obj_id, track_id, frames_counter, x_3d, y_3d, z_3d);
        }
    }

    public static class ByValue extends Box implements Structure.ByValue{
        ByValue(int x, int y, int w, int h, float prob, int obj_id, int track_id, int frames_counter, float x_3d, float y_3d, float z_3d) {
            super(x, y, w, h, prob, obj_id, track_id, frames_counter, x_3d, y_3d, z_3d);
        }
    }
    public Box(){}
    public Box(int x, int y, int w, int h, float prob, int obj_id, int track_id, int frames_counter,
               float x_3d, float y_3d, float z_3d){
        this.x =x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.prob =prob;
        this.obj_id=obj_id;
        this.track_id=track_id;
        this.frames_counter=frames_counter;
        this.x_3d=x_3d;
        this.y_3d=y_3d;
        this.z_3d=z_3d;

    }
    public int x;
    public int y;
    public int w;
    public int h;
    public float prob;
    public int obj_id;
    public int track_id;
    public int frames_counter;
    public float x_3d;
    public float y_3d;
    public float z_3d;

    public void initBox(int x, int y, int w, int h, float prob, int obj_id, int track_id, int frames_counter,
                        float x_3d,float y_3d,float z_3d){
        this.x =x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.prob =prob;
        this.obj_id=obj_id;
        this.track_id=track_id;
        this.frames_counter=frames_counter;
        this.x_3d=x_3d;
        this.y_3d=y_3d;
        this.z_3d=z_3d;

    }

    protected List<String> getFieldOrder() {
        List<String> Field = new ArrayList<String>();
        Field.add("x");
        Field.add("y");
        Field.add("w");
        Field.add("h");
        Field.add("prob");
        Field.add("obj_id");
        Field.add("track_id");
        Field.add("frames_counter");
        Field.add("x_3d");
        Field.add("y_3d");
        Field.add("z_3d");

        return Field;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public float getProb() {
        return prob;
    }

    public void setProb(float prob) {
        this.prob = prob;
    }

    public int getObj_id() {
        return obj_id;
    }

    public void setObj_id(int obj_id) {
        this.obj_id = obj_id;
    }

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public int getFrames_counter() {
        return frames_counter;
    }

    public void setFrames_counter(int frames_counter) {
        this.frames_counter = frames_counter;
    }

    public float getX_3d() {
        return x_3d;
    }

    public void setX_3d(float x_3d) {
        this.x_3d = x_3d;
    }

    public float getY_3d() {
        return y_3d;
    }

    public void setY_3d(float y_3d) {
        this.y_3d = y_3d;
    }

    public float getZ_3d() {
        return z_3d;
    }

    public void setZ_3d(float z_3d) {
        this.z_3d = z_3d;
    }
}

