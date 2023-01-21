package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    int spriteNum = 1;
    int spriteCounter = 0;
    BufferedImage image = stay1;


    private int x = 100;
    private int y = 100;
    private int speed = 4;


    public Player(GamePanel gp,KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        getImage();
    }
    public void update(){

        if (keyH.upPressed){
            direction = "up";
            y -= speed;
        }else if (keyH.downPressed){
            direction = "down";
            y += speed;
        }else if (keyH.rightPressed){
            direction = "right";
            x += speed;
        }else if(keyH.leftPressed){
            direction = "left";
            x -= speed;
        }
//        System.out.println(x+"  "+y);

    }
    private void getImage(){

        try {
            stay1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/negr/stay.png")));
            stay2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/negr/stay2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/negr/step1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/negr/step2.png")));

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){

//        g2.setColor(Color.white);
//
//        g2.fillRect(x,y,gp.tileSize, gp.tileSize);
        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = stay1;
                }else if (spriteNum == 2){
                    image = stay2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }else if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        spriteCounter++;
        if (spriteCounter > gp.FPS/10){
            if (spriteNum == 1){
                image = stay1;
                spriteNum++;
            }else if (spriteNum == 2){
                image = stay2;
                spriteNum++;
            }
            if (spriteNum > 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }



        g2.drawImage(image,gp.screenWidth/2, gp.screenHeight/2, gp.tileSize, gp.tileSize, null);

        g2.dispose();
    }

}
