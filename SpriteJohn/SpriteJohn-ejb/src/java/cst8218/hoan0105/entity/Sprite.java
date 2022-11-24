/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.hoan0105.entity;
import cst8218.hoan0105.adapter.ColorAdapter;
import cst8218.hoan0105.adapter.JsonColorDeserializer;
import cst8218.hoan0105.adapter.JsonColorSerializer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Constraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;


/**
 * Sprite java bean
 * @author tgk
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sprite implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static Random random = new Random();

    final static int SIZE = 10;
    final static int MAX_SPEED = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    //add minium constraint and initialized
    @Min(value=500,message="panelWidth must be greater than {value}")
    @Column
    private int panelWidth=500;
    
    //add minium constraint and initialized
    @Min(value=500,message="panelHeight must be greater than {value}")
    @Column
    private int panelHeight=500;
    
    //contraints set in jsf
    @Column
    private int x=random.nextInt(panelWidth);
    
    //constraints set in jsf
    @Column
    private int y=random.nextInt(panelHeight);
    
    //constraint on moving in the x direction
    @Min(value=-5,message="y must be greater than {value}")
    @Max(value=5,message="y must be greater than {value}")
    @Column
    private int dx=random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
    
    //constraint on moving on the y directiom
    @Min(value=-5,message="y must be greater than {value}")
    @Max(value=5,message="y must be greater than {value}")
    @Column
    private int dy=random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
    
    //annotation to deserialized and serialized json and XML - for postman get and post to display audible text
    @XmlElement
    @XmlJavaTypeAdapter(ColorAdapter.class)
    @JsonbTypeDeserializer(JsonColorDeserializer.class)
    @JsonbTypeSerializer(JsonColorSerializer.class)
    @Column
    private Color color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));

    public Sprite() {
    }

    public Sprite(int height, int width) {
        this.panelWidth = width;
        this.panelHeight = height;
        x = random.nextInt(width-x);
        y = random.nextInt(height-y);
        dx = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
        dy = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
    }

    public Sprite(int height, int width, Color color) {
        this(height, width);
        this.color = color;
    }

    public int getPanelWidth() {
        return panelWidth;
    }

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
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

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void move() {

        // check for bounce and make the ball bounce if necessary
        //
        if (x < 0 && dx < 0) {
            //bounce off the left wall 
            x = 0;
            dx = -dx;
        }
        if (y < 0 && dy < 0) {
            //bounce off the top wall
            y = 0;
            dy = -dy;
        }
        if (x > panelWidth - SIZE && dx > 0) {
            //bounce off the right wall
            x = panelWidth - SIZE;
            dx = -dx;
        }
        if (y > panelHeight - SIZE && dy > 0) {
            //bounce off the bottom wall
            y = panelHeight - SIZE;
            dy = -dy;
        }

        //make the ball move
        x += dx;
        y += dy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprite)) {
            return false;
        }
        Sprite other = (Sprite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sprite[ id=" + id + " ]";
    }
    
        /**
     * Update existing sprite in RESTful web services unless all values are not null.
     * @param old an existing sprite
     * @return an updated sprite
     */
    public Sprite updates(Sprite old){
        if(this.panelWidth>=0)
            old.panelWidth = this.panelWidth;

        if(this.panelHeight>=0)
            old.panelHeight = this.panelHeight;

        if(this.x>=0)
           old.x = this.x;
        if(this.y>=0)
            old.y = this.y;

        old.dx = this.dx;
        old.dy = this.dy;

        old.color = this.color;

        return old;
    }
    
    
    /**
     * Check for null to auto generate sprite in RESTful web services.
     * If there are null values in a sprite, change with default values.
     */
    public void checkNull() {
        panelHeight = 500;
        panelWidth = 500;
        x = 0;
        y = 0;
        dx = 0;
        dy = 0;
        color = Color.BLUE;
    }

}
