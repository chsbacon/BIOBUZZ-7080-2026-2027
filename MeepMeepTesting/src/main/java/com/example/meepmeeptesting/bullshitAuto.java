package com.example.meepmeeptesting;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class bullshitAuto {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(1, 0.01, Math.toRadians(1800000), Math.toRadians(5*1800), 18)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-61, -9, Math.toRadians(0)))
                .splineToLinearHeading(new Pose2d(-50,-55,Math.toRadians(90)),Math.toRadians(-90))

                .turn(Math.toRadians(360000))
                .strafeTo(new Vector2d(100,-55))
                .build());

        Image customImg = null;
        try {
            customImg = ImageIO.read(new File("FIELD_BIOBUZZ_OFFICAL.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        meepMeep.setBackground(customImg)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}